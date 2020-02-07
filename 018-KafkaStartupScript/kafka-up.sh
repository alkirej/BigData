#!/bin/bash

if [ -z $1 ]
then
    echo ""
else
    if [ $1 -ge 4 ] && [ $1 -le 7 ]
    then
        VALID=$1
    fi   
fi

if [ -z $VALID ]
then
    echo "kafka-up.sh:  start zookeeper and a specified number of broker daemons"
    echo ""
    echo "usage: kafka-up.sh <broker-count>"
    echo "    where <broker-count> is a number between 4 and 7"
    echo "" 
    exit 1    
else
    zookeeper-server-start.sh -daemon $KAFKA_HOME/config/zookeeper.properties
    for ((i=1; c<=$VALID; i++))
    do
      cat /opt/kafka/config/server.properties | \
         sed -e "s/log.dirs=\/tmp\/kafka-logs/log.dirs=\/tmp\/kafka-logs-$VALID	/" \
         > server.properties.$VALID 
      kafka-server-start.sh -daemon $KAFKA_HOME/config/server.properties.$VALID

echo -n "$c"
        sleep 1
    done
fi

exit 0

sleep 1
cd /opt/kafka/config

cat /opt/kafka/config/server.properties | sed -e "s/log.dirs=\/tmp\/kafka-logs/log.dirs=\/tmp\/kafka-logs-1/"> server.properties.1
echo "
listeners=PLAINTEXT://:19091
" >> server.properties.1

cat /opt/kafka/config/server.properties | sed -e "s/log.dirs=\/tmp\/kafka-logs/log.dirs=\/tmp\/kafka-logs-2/"> server.properties.2
echo "
listeners=PLAINTEXT://:19092
" >> server.properties.2


kafka-server-start.sh -daemon $KAFKA_HOME/config/server.properties.1
sleep 1
kafka-server-start.sh -daemon $KAFKA_HOME/config/server.properties.2
sleep 1

# create a sample topic
kafka-topics.sh --create --topic example --zookeeper localhost:2181 --partitions 1 --replication-factor 1

