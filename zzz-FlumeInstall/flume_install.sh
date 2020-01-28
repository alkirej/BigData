#!/bin/bash
# FLUME INSTALL

# create flume directory
sudo mkdir -p /usr/local/flume
sudo chown -R hadoop /usr/local/flume
sudo chmod -R 755 /usr/local/flume

# download flume
cd /usr/local/flume
# download flume
wget http://archive.apache.org/dist/flume/stable/apache-flume-1.9.0-bin.tar.gz

# extract the tar file
tar xzvf apache-flume-1.9.0-bin.tar.gz

# update the .bashrc file to include environment for flume
echo "

## FLUME ENVIRONMENT VARS
export FLUME_HOME=/usr/local/flume
export FLUME_CONF_DIR=$FLUME_HOME/conf
export FLUME_CLASSPATH=$FLUME_CONF_DIR
export PATH=$PATH:$FLUME_HOME/bin" >> ~/.bashrc

export FLUME_HOME=/usr/local/flume

cd /usr/local/flume/apache-flume-1.9.0-bin
mv * ..
cd ..
rmdir apache-flume-1.9.0-bin
rm apache-flume-1.9.0-bin.tar.gz

cd $FLUME_HOME/conf
cp flume-env.sh.template flume-env.sh

echo "
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64" >> flume-env.sh

cp flume-conf.properties.template flume-conf.properties

# cd to /usr/local/flume/bin 
cd $FLUME_HOME/bin

./flume-ng version
