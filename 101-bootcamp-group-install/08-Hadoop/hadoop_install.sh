#!/bin/bash
# run as hadoop user
export HADOOP_HOME=/opt/hadoop
export PATH=$HADOOP_HOME/bin:$PATH
export HADOOP_CONF_DIR=$HADOOP_HOME/etc/hadoop

# update the hadoop user environment
cd ~

# change perms
cd /
sudo chmod 755 /opt
cd /opt

# download hadoop core
sudo wget http://archive.apache.org/dist/hadoop/core/hadoop-2.8.5/hadoop-2.8.5.tar.gz

# untar the file and remove it
sudo tar xvzf hadoop-2.8.5.tar.gz 
sudo rm hadoop-2.8.5.tar.gz 

# link hadoop dir to the hadoop-verion
sudo chmod 755 hadoop-2.8.5
sudo ln -s hadoop-2.8.5 hadoop 

cd /opt 
sudo chown `whoami` hadoop-2.8.5 -R 
sudo chmod 776 /opt/hadoop-2.8.5 -R 

# configure the hadoop enviornment
cd $HADOOP_HOME/etc/hadoop 
mv hadoop-env.sh hadoop-env.sh.orig 

# change the ${JAVA_HOME} place holder to the correct location
cat hadoop-env.sh.orig | sed -e "s/\${JAVA_HOME}/\/usr\/lib\/jvm\/java-8-openjdk-amd64/" > hadoop-env.sh
rm hadoop-env.sh.orig 

# add temp directory and file system environmental variable for hadooop
# update core-site.xml
cd /opt/hadoop/etc/hadoop
cp core-site.xml core-site.xml.orig 
cat core-site.xml.orig | sed -s "s/<\/configuration>//" > core-site.xml
rm core-site.xml.orig 

echo "
<property>
    <name>hadoop.tmp.dir</name>
    <value>/opt/hadoop/tmp</value>
    <description>Parent directory for other temporary directories.</description>
</property>
<property>
    <name>fs.defaultFS</name>
    <value>hdfs://localhost:50501</value>
    <description>The name of the default file system.</description>
</property>
</configuration>" >> core-site.xml

# update mapred-site.xml
sudo cat mapred-site.xml.template | sed -s "s/<\/configuration>//" > mapred-site.xml
sudo echo "
<property>
  <name>reduce.jobtracker.address</name>
  <value>localhost:50502</value>
  <description>MapReduce job tracker runs at this host and port.</description>
</property>
</configuration>
" >> mapred-site.xml

cd /opt/hadoop 
mkdir tmp 

# had a profile.d entry for hadoop
cd /etc/profile.d 
sudo chmod 777 /etc/profile.d 
sudo echo "export HADOOP_HOME=/opt/hadoop" > hadoop.sh
sudo chmod +x /etc/profile.d/hadoop.sh 
sudo chmod 755 /etc/profile.d 

cd /opt/hadoop/etc/hadoop 
cp hdfs-site.xml hdfs-site.xml.orig 
sudo cat hdfs-site.xml.orig | sed -s "s/<\/configuration>//" > hdfs-site.xml
rm hdfs-site.xml.orig 

# hdfs-site.xml
echo "<property>
  <name>dfs.replication</name>
  <value>1</value>
  <description>Default block replication.</description>
</property>
<property>
  <name>dfs.namenode.name.dir</name>
  <value>/opt/hadoop/namenode</value>
</property>
<property>
  <name>dfs.datanode.data.dir</name>
  <value>/opt/hadoop/hdfs</value>
</property>
<property>
  <name>dfs.namenode.servicerpc-address</name>
  <value>localhost:50502</value>
</property>
</configuration>
" >> hdfs-site.xml

sudo mkdir /opt/hadoop/hdfs 
sudo chown `whoami` /opt/hadoop/hdfs 
sudo chgrp `whoami` /opt/hadoop/hdfs 

sudo mkdir /opt/hadoop/namenode 
sudo chown `whoami` /opt/hadoop/namenode 
sudo chgrp `whoami` /opt/hadoop/namenode 

sudo mkdir -p /dfs/name/current 

cd /opt
sudo chmod 776 hadoop-2.8.5 -R 
sudo chown `whoami` hadoop-2.8.5 -R 

cd ~
echo "Y
" > ReplyYes.txt
hdfs namenode -format < ReplyYes.txt 
rm ReplyYes.txt
