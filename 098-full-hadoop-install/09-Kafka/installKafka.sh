#!/bin/sh

cd /opt
#Get Kafka
sudo wget http://apache.mirrors.hoobly.com/kafka/2.3.1/kafka_2.12-2.3.1.tgz
#Extract the file
sudo tar -xzvf kafka_2.12-2.3.1.tgz
#Remove zip file
sudo rm kafka_2.12-2.3.1.tgz
#Update the bash profile

sudo ln -s /opt/kafka_2.12-2.3.1 /opt/kafka

