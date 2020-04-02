#!/bin/bash

pushd /opt

# download and untar sqoop
sudo wget http://archive.apache.org/dist/sqoop/1.4.7/sqoop-1.4.7.bin__hadoop-2.6.0.tar.gz
sudo tar xvf sqoop-1.4.7.bin__hadoop-2.6.0.tar.gz

# remove install file
sudo rm sqoop-1.4.7.bin__hadoop-2.6.0.tar.gz

# link sqoop to fully named sqoop directory
sudo ln -s sqoop-1.4.7.bin__hadoop-2.6.0 sqoop

cd sqoop/conf
cp sqoop-env-template.sh sqoop-env.sh

# Update sqoop startup configuration
echo "
export HADOOP_COMMON_HOME=${HADOOP_HOME}
export HADOOP_MAPRED_HOME=${HADOOP_HOME}
" >> sqoop-env.sh

cd ~
cp .bash_profile bash_profile.backup

echo "
export SQOOP_HOME=/opt/sqoop
export PATH=\$PATH:\$SQOOP_HOME/bin
" >> .bash_profile

# restore original directory
popd

# note to installer
echo "=============================================================="
echo "=== Don't forget to add mysql-connector-java-x.x.x-bin.jar ==="
echo "=== to sqoop installation (/opt/sqoop/lib/*.jar)           ==="
echo "=============================================================="
