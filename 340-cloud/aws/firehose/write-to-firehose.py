import boto3
from faker import Faker
import random
import time
import json

fake = Faker()

DeliveryStreamName = 'firehose-test-stream'
client = boto3.client('firehose', region_name='us-west-1')

record = {}

# create 100 random records.
for i in range(100):
    record['user'] = fake.name()
    record['file-size'] = random.randint(33000, 72000)
    record['timestamp'] = time.time()
    response = client.put_record(
        DeliveryStreamName=DeliveryStreamName,
        Record={
            'Data': json.dumps(record)
        }
    )

print('Write to kinesis (via put_record method): \n' + str(record))

