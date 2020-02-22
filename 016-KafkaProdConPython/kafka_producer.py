from time import sleep
from names import get_full_name
from kafka import KafkaProducer
from names import get_full_name

from KafkaServerInfo import KafkaServerInfo


MESSAGE_COUNT: int  = 30 

producer: KafkaProducer = KafkaProducer( bootstrap_servers=[KafkaServerInfo.KAFKA_BROKER_ADDR]  )

for i in range(MESSAGE_COUNT):
    name: str = get_full_name()
    producer.send(KafkaServerInfo.TOPIC_NAME, name.encode(KafkaServerInfo.CHAR_ENCODING ))
    print( name )
    sleep(0.5)

producer.flush()
producer.close()
