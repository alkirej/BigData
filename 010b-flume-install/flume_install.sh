#!/bin/bash
# FLUME INSTALL

# create flume directory
export INSTALL_DIR=/opt/flume
sudo mkdir -p $INSTALL_DIR
sudo chown -R `whoami` $INSTALL_DIR
sudo chmod -R 755 $INSTALL_DIR

# download flume
cd $INSTALL_DIR
# download flume
wget http://archive.apache.org/dist/flume/stable/apache-flume-1.9.0-bin.tar.gz

# extract the tar file
tar xzvf apache-flume-1.9.0-bin.tar.gz

# update the .bashrc file to include environment for flume
echo "

## FLUME ENVIRONMENT VARS
export FLUME_HOME=/opt/flume
export FLUME_CONF_DIR=\$FLUME_HOME/conf
export FLUME_CLASSPATH=\$FLUME_CONF_DIR
export PATH=\$FLUME_HOME/bin:\$PATH" >> ~/.bashrc

export FLUME_HOME=$INSTALL_DIR

cd $FLUME_HOME/apache-flume-1.9.0-bin
mv * ..
cd ..
rmdir apache-flume-1.9.0-bin
rm apache-flume-1.9.0-bin.tar.gz

cd $FLUME_HOME/conf
cp flume-env.sh.template flume-env.sh

echo "
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64" >> flume-env.sh

cp flume-conf.properties.template flume-conf.properties

# cd to flume bin dir
cd $FLUME_HOME/bin

./flume-ng version
