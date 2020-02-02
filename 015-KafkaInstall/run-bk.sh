if [ "x$1" = "x" ] 
then   
    echo "Starts Kafka broker:"
    echo "    Syntax:  run-bk.sh <broker-id>"
    echo "        where <broker-id> = (1, 2, 3, 4, or 5)"
    exit 1
fi

/opt/kafka/bin/kafka-server-start.sh /home/jeff/BigData/015-KafkaInstall/server.properties.$1
