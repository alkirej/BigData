#!/bin/sh 
# BEFORE INSTALLING HBASE, JAVA AND HADOOP MUST BE INSTALLED
##################################################################
# HADOOP MUST BE RUNNING BEFORE RUNNING THIS INSTALL SCRIPT !!!! #
##################################################################
# DOWNLOAD INSTALL FILES
cd /opt
sudo wget http://apache.cs.utah.edu/hbase/2.2.4/hbase-2.2.4-bin.tar.gz
sudo tar xvzf hbase-2.2.4-bin.tar.gz
sudo chown -R `whoami` hbase-2.2.4
sudo ln -s hbase hbase-2.2.4

sudo rm hbase-2.2.4-bin.tar.gz

cd /opt/hbase/conf
echo "
export JAVA_HOME=$JAVA_HOME
" >> hbase-env.sh

sudo mv hbase-site.xml hbase-site.xml.orig
sudo cat hbase-site.xml.orig | sed -s "s/<\/configuration>//" > hbase-site.xml
echo "
   <property>
      <name>hbase.rootdir</name>
      <value>file:/home/hadoop/HBase/HFiles</value>
   </property>
   <property>
      <name>hbase.zookeeper.property.dataDir</name>
      <value>/home/hadoop/zookeeper</value>
   </property>
   <property>
      <name>hbase.cluster.distributed</name>
      <value>true</value>
   </property>
   <property>
      <name>hbase.rootdir</name>
      <value>hdfs://localhost:50502/hbase</value>
   </property>
</configuration>
" >> hbase-site.xml

rm hbase-site.xml.orig

sudo mkdir -p /home/hadoop/zookeeper
cd /home
sudo chown -R `whoami` hadoop
sudo chmod -R 774 hadoop

echo "
export PATH=/opt/hbase/bin:\$PATH
" >> ~/.bash_profile

