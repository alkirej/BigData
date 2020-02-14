import os
from kafka import KafkaConsumer
from KafkaServerInfo import KafkaServerInfo

FILE_NAME   = "kafka_input.txt"
HDFS_DIR    = "/data"
HADOOP_HOME = "/opt/hadoop"

consumer:KafkaConsumer = KafkaConsumer(
     KafkaServerInfo.TOPIC_NAME,
     bootstrap_servers=[KafkaServerInfo.KAFKA_BROKER_ADDR_2],
     auto_offset_reset='earliest'
)

m = ""
for message in consumer:
    m += message.value.decode(KafkaServerInfo.CHAR_ENCODING) + "\n"
    print( m )
    with open( FILE_NAME , 'wb' ) as f:
        f.write(m.encode("utf-8"))
    print( "hdfs dfs -put " + FILE_NAME + " " + HDFS_DIR + "/" + FILE_NAME )
    ec:int = os.system( 'sh -c "' + HADOOP_HOME + '/bin/hdfs dfs -put -f ' + FILE_NAME + ' ' + HDFS_DIR + '/' + FILE_NAME + '"' )
    print( "\nexit code: " + str(ec) + "\n" )
        
consumer.close()

