#!/bin/bash

INSTALL_DIR=/opt
cd ${INSTALL_DIR}

#sudo wget https://downloads.apache.org/zookeeper/zookeeper-3.6.0/apache-zookeeper-3.6.0-bin.tar.gz
sudo tar xvf apache-zookeeper-3.6.0-bin.tar.gz
sudo ln -s apache-zookeeper-3.6.0-bin zookeeper
#sudo rm apache-zookeeper-3.6.0-bin.tar.gz

cd zookeeper/conf
sudo sed "s|^dataDir=.\*$|dataDir=${INSTALL_DIR}/zookeeper/data|" zoo_sample.cfg > zoo.cfg
cd ..
sudo mkdir data
sudo mkdir logs
sudo chown 777 logs
cd data
sudo touch myid
sudo chmod 666 myid
echo "1" > myid

