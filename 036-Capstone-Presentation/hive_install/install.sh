#!/bin/sh

USER=jeff
PASSWORD=jeff

# BEFORE INSTALLING HIVE, JAVA AND HADOOP MUST BE INSTALLED
##################################################################
# HADOOP MUST BE RUNNING BEFORE RUNNING THIS INSTALL SCRIPT !!!! #
##################################################################
# DOWNLOAD INSTALL FILES FOR HIVE
cd /opt
sudo wget http://apache.claz.org/hive/hive-3.1.2/apache-hive-3.1.2-bin.tar.gz
sudo tar xvf apache-hive-3.1.2-bin.tar.gz
sudo chown -R `whoami` apache-hive-3.1.2-bin
sudo ln -s apache-hive-3.1.2-bin hive
sudo rm apache-hive-3.1.2-bin.tar.gz

# SETUP HIVE ENVIRONMENT
cd ~
export HIVE_HOME=/opt/hive
export PATH=$PATH:$HIVE_HOME/bin
export CLASSPATH=.:$HIVE_HOME/Hadoop/lib/*:$HIVE_HOME/hive/lib/*

cd $HIVE_HOME/conf
cp hive-env.sh.template hive-env.sh
echo "
export HADOOP_HOME=$HADOOP_HOME
" >> hive-env.sh


# DOWNLOAD MYSQL AND SETUP FILES
sudo apt-get update
sudo apt-get install mysql-server

# START MYSQL (NOW & AND DURING BOOT SEQUENCE)
sudo systemctl start mysql
sudo systemctl enable mysql

# SETUP MYSQL ENVIRONMENT
sudo echo "
bind-address = 0.0.0.0
" >> /etc/mysql/mysql.conf.d/mysqld.cnf

sudo systemctl restart mysql

sudo <<EOF
  mysql <<SQL
    UPDATE mysql.user SET authentication_string = PASSWORD('root') WHERE User = 'root';
    FLUSH PRIVILEGES;
    CREATE DATABASE task36; 
    INSERT INTO mysql.user (User,Host,authentication_string,ssl_cipher,x509_issuer,x509_subject)
        VALUES('${USER}','localhost',PASSWORD('${PASSWORD}'),'','','');
    GRANT ALL PRIVILEGES ON task36.* to jeff@localhost;
    FLUSH PRIVILEGES;
  SQL
EOF

# STOPPED UPDATES HERE --- 
echo "
export HIVE_HOME=$HIVE_HOME
export DERBY_HOME=/opt/derby
export PATH=$HIVE_HOME/bin:$DERBY_HOME/bin:\$PATH
export CLASSPATH=.:$HADOOP_HOME/lib/*:$HIVE_HOME/lib/*:$DERBY_HOME/lib/derby.jar:$DERBY_HOME/lib/derbytools.jar
" >> ~/.bash_profile


mkdir -p $DERBY_HOME/data
cd $HIVE_HOME/conf

echo "
javax.jdo.PersistenceManagerFactoryClass =

org.jpox.PersistenceManagerFactoryImpl
org.jpox.autoCreateSchema = false
org.jpox.validateTables = false
org.jpox.validateColumns = false
org.jpox.validateConstraints = false
org.jpox.storeManagerType = rdbms
org.jpox.autoCreateSchema = true
org.jpox.autoStartMechanismMode = checked
org.jpox.transactionIsolation = read_committed
javax.jdo.option.DetachAllOnCommit = true
javax.jdo.option.NontransactionalRead = true
javax.jdo.option.ConnectionDriverName = org.apache.derby.jdbc.ClientDriver
javax.jdo.option.ConnectionURL = jdbc:mysql://localhost:3306/hive?createDatabaseIfNotExist=true
javax.jdo.option.ConnectionUserName = app
javax.jdo.option.ConnectionPassword = derby
" > jpox.properties

echo "
<configuration>
   <property>
      <name>javax.jdo.option.ConnectionURL</name>
      <value>jdbc:mysql://localhost:3306/hive?createDatabaseIfNotExist=true</value>
      <description>metadata is stored in a Derby server</description>
   </property>
   <property>
      <name>javax.jdo.option.ConnectionDriverName</name>
      <value>com.mysql.jdbc.Driver</value>
      <description>Derby driver class</description>
   </property>
   <property>
      <name>javax.jdo.option.ConnectionUserName</name>
      <value>app</value>
      <description>user name for connecting to db</description>
   </property>
   <property>
      <name>javax.jdo.option.ConnectionPassword</name>\
      <value>derby</value>
      <description>password for connecting to db</description>
   </property>
</configuration>
" > hive-site.xml

# CREATE HIVE METASTORE
echo "Create hive metastore"
schematool -dbType mysql -initSchema

