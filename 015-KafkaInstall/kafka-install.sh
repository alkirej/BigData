# KAFKA INSTALLATION SCRIPT

# DOWNLOAD KAFKA INTO /OPT DIRECTORY
cd /opt
sudo apt-get install zookeeperd

# TO VERIFY INSTALLATION,
# telnet localhost 2181
# type: ruok  and look for response imok

# DOWNLOAD AND UNTAR FILE
wget "http://mirrors.advancedhosters.com/apache/kafka/2.3.1/kafka_2.12-2.3.1.tgz"
tar xvf kafka_2.12-2.3.1.tgz

# DELETE TAR FILE
rm  kafka_2.12-2.3.1.tgz
ln -s kafka_2.12-2.3.1 kafka

# ----- CONFIGURATION -----

# UPDATE THE SERVER PROPERTIES

echo "
delete.topic.enable = true" >> /opt/kafka/config/server.properties

echo "
export PATH=/opt/kafka/bin:$PATH
" >> /home/kafka/.bash_profile

export PATH=/opt/kafka/bin:$PATH
kafka-server-start.sh /opt/kafka/config/server.properties