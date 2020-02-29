#!/bin/sh

USER=jeff
PASSWORD=jeff

# BEFORE INSTALLING HIVE, JAVA AND HADOOP MUST BE INSTALLED
##################################################################
# HADOOP MUST BE RUNNING BEFORE RUNNING THIS INSTALL SCRIPT !!!! #
##################################################################
# DOWNLOAD INSTALL FILES FOR HIVE
cd /opt
sudo wget http://apache.mirrors.pair.com/hive/stable-2/apache-hive-2.3.6-bin.tar.gz
sudo tar xvf apache-hive-2.3.6-bin.tar.gz
sudo chown -R `whoami` apache-hive-2.3.6-bin
sudo ln -s apache-hive-2.3.6-bin hive
sudo rm apache-hive-2.3.6-bin.tar.gz

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

# DOWNLOAD AND INSTALL JDBS DRIVER FOR MYSQL
cd $HIVE_HOME/lib
sudo wget http://www.java2s.com/Code/JarDownload/mysql/mysql-connector-java-5.1.22-bin.jar.zip
sudo unzip mysql-connector-java-5.1.22-bin.jar.zip
sudo rm mysql-connector-java-5.1.22-bin.jar.zip

# DOWNLOAD MYSQL AND SETUP FILES
sudo apt-get update
sudo apt-get --yes install mysql-server mysql-client

# START MYSQL (NOW & AND DURING BOOT SEQUENCE)
sudo systemctl start mysql
sudo systemctl enable mysql
sudo systemctl restart mysql

echo "
    UPDATE mysql.user SET authentication_string = PASSWORD('root') WHERE User = 'root';
    FLUSH PRIVILEGES;
    -- CREATE DATABASE task36;
    INSERT INTO mysql.user (User,Host,authentication_string,ssl_cipher,x509_issuer,x509_subject)
        VALUES('${USER}','localhost',PASSWORD('${PASSWORD}'),'','','');
    GRANT ALL PRIVILEGES ON *.* to ${USER}@localhost;
    FLUSH PRIVILEGES;
" > ~/.temp.sql

sudo mysql < ~/.temp.sql

sudo rm ~/.temp.sql

# STOPPED UPDATES HERE --- 
echo "
export HIVE_HOME=$HIVE_HOME
export PATH=$HIVE_HOME/bin:$DERBY_HOME/bin:\$PATH
export CLASSPATH=.:$HADOOP_HOME/lib/*:$HIVE_HOME/lib/*
" >> ~/.bash_profile


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
javax.jdo.option.ConnectionDriverName = com.mysql.jdbc.Driver
javax.jdo.option.ConnectionURL = jdbc:mysql://localhost/hive?createDatabaseIfNotExist=true
javax.jdo.option.ConnectionUserName = ${USER}
javax.jdo.option.ConnectionPassword = ${PASSWORD}
" > jpox.properties

echo "
<configuration>
   <property>
      <name>javax.jdo.option.ConnectionURL</name>
      <value>jdbc:mysql://localhost/hive?createDatabaseIfNotExist=true</value>
      <description>metadata is stored in a mysql server</description>
   </property>
   <property>
      <name>javax.jdo.option.ConnectionDriverName</name>
      <value>com.mysql.jdbc.Driver</value>
      <description>mysql driver class</description>
   </property>
   <property>
      <name>javax.jdo.option.ConnectionUserName</name>
      <value>${USER}</value>
      <description>user name for connecting to db</description>
   </property>
   <property>
      <name>javax.jdo.option.ConnectionPassword</name>\
      <value>${PASSWORD}</value>
      <description>password for connecting to db</description>
   </property>
   <property>
      <name>javax.jdo.option.ConnectionPassword</name>\
      <value>${PASSWORD}</value>
      <description>password for connecting to db</description>
   </property>
   <property>
      <name>hive.metastore.warehouse.dir</name>      
      <value>hdfs://localhost:50501/user/hive/warehouse</value>
      <description></description>
   </property>
   <property>
      <name>spark.sql.warehouse.dir</name>      
      <value>hdfs://localhost:50501/user/hive/warehouse</value>
      <description></description>
   </property>
</configuration>
" > hive-site.xml

sudo ln -s /opt/hive/conf/hive-site.xml /opt/spark/conf/hive-site.xml
# CREATE HIVE METASTORE
echo "Create hive metastore"
schematool -dbType mysql -initSchema

