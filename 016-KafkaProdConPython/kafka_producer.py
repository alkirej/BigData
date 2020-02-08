from time import sleep
from names import get_full_name
from kafka import KafkaProducer
from names import get_full_name

import KafkaServerInfo


MESSAGE_COUNT: int  = 10

producer: KafkaProducer = KafkaProducer( bootstrap_servers=[KafkaServerInfo.KAFKA_BROKER_ADDR]  )

for i in range(MESSAGE_COUNT):
    producer.send(KafkaServerInfo.TOPIC_NAME, get_full_name().encode(KafkaServerInfo.CHAR_ENCODING ))
    sleep(2)

producer.flush()
producer.close()