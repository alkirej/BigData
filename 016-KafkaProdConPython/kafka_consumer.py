from kafka import KafkaConsumer
import KafkaServerInfo

consumer:KafkaConsumer = KafkaConsumer(
     KafkaServerInfo.TOPIC_NAME,
     bootstrap_servers=[KafkaServerInfo.KAFKA_BROKER_ADDR],
     auto_offset_reset='earliest'
)

for message in consumer:
    print( message.value.decode(KafkaServerInfo.CHAR_ENCODING) )

consumer.close()