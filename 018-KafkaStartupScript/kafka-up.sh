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

if [ -z $KAFKA_HOME ]

then
    echo
    echo Unable to comply: KAFKA_HOME environment variable is not set.
    echo
    exit 2
fi

if [ -r $KAFKA_HOME/config/server.properties ]
then
else
    echo 
    echo Unable to comply: $KAFKA_HOME/config/server.properties 
    echo does not exist or is unable to be read.
    echo 
    exit 3
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
    echo
    echo ... Starting Zookeeper ....
    echo
    zookeeper-server-start.sh -daemon $KAFKA_HOME/config/zookeeper.properties

    for ((i=1; i<=${VALID}; i++))
    do
        cat $KAFKA_HOME/config/server.properties | \
            sed -e "s/log.dirs=\/tmp\/kafka-logs/log.dirs=\/tmp\/kafka-logs-${i}/" | \
            sed -e "s/broker.id=0/kafka-logs-${i}/" \
                > $KAFKA_HOME/config/server.properties.${i}

    echo ... Starting Broker ${i} ....
    kafka-server-start.sh -daemon $KAFKA_HOME/config/server.properties.${i}
    done
fi

# create a sample topic
#kafka-topics.sh --create --topic example --zookeeper localhost:2181 --partitions 1 --replication-factor 1

