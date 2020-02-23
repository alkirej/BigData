#!/bin/bash
# BEFORE INSTALLING HBASE, JAVA MUST BE INSTALLED

# AFTER INSTALL, execute the following command to connect to cassandra server:
# cqlsh

sudo apt install curl

sudo apt-key adv --keyserver pool.sks-keyservers.net --recv-key A278B781FE4B2BDA
sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys E91335D77E3E87CB

echo "deb http://www.apache.org/dist/cassandra/debian 311x main" | sudo tee -a /etc/apt/sources.list.d/cassandra.sources.list
sudo wget https://www.apache.org/dist/cassandra/KEYS | sudo apt-key add -

sudo apt-get update
sudo apt-get install cassandra



