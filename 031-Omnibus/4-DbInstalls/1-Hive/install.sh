#!/bin/sh
# BEFORE INSTALLING HIVE, JAVA AND HADOOP MUST BE INSTALLED
##################################################################
# HADOOP MUST BE RUNNING BEFORE RUNNING THIS INSTALL SCRIPT !!!! #
##################################################################
# DOWNLOAD INSTALL FILES
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


# DOWNLOAD DERBY AND SETUP FILES
cd /opt
sudo wget http://archive.apache.org/dist/db/derby/db-derby-10.4.2.0/db-derby-10.4.2.0-bin.tar.gz
sudo tar xvf db-derby-10.4.2.0-bin.tar.gz
sudo chown -R `whoami` db-derby-10.4.2.0-bin
sudo ln -s db-derby-10.4.2.0-bin derby
sudo rm db-derby-10.4.2.0-bin.tar.gz

# SETUP DERBY ENVIRONMENT
export DERBY_HOME=/opt/derby
export PATH=$PATH:$DERBY_HOME/bin
export CLASSPATH=$CLASSPATH:$DERBY_HOME/lib/derby.jar:$DERBY_HOME/lib/derbytools.jar


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
javax.jdo.option.ConnectionURL = jdbc:derby://localhost:1527/metastore_db;create=true;
javax.jdo.option.ConnectionUserName = app
javax.jdo.option.ConnectionPassword = derby
" > jpox.properties

echo "
<configuration>
   <property>
      <name>javax.jdo.option.ConnectionURL</name>
      <value>jdbc:derby://localhost:1527/metastore_db;create=true;</value>
      <description>metadata is stored in a Derby server</description>
   </property>
   <property>
      <name>javax.jdo.option.ConnectionDriverName</name>
      <value>org.apache.derby.jdbc.ClientDriver</value>
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
cd $JAVA_HOME/jre/lib/security
sudo chmod 666 java.policy
echo "
grant {
    permission java.net.SocketPermission \"localhost:1527\", \"listen,resolve\";
};
" >> java.policy
sudo chmod 644 java.policy

cd $DERBY_HOME/bin
./startNetworkServer &
sleep 5s

echo "Create hive metastore"
schematool -dbType derby -initSchema

