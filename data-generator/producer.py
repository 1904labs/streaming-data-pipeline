from kafka import KafkaProducer
from kafka.errors import KafkaError
import csv
import signal
import sys
import time
from pathlib import Path
import json
import os.path

p = Path('/Users/willfarrell/Downloads/amazon_reviews_us_Toys_v1_00.tsv')
servers = ['35.239.241.212:9092', '35.239.230.132:9092', '34.69.66.216:9092']

producer = KafkaProducer(bootstrap_servers=servers)

# For each record
def on_send_success(record_metadata):
    x = "hello"
#     print(record_metadata.topic)
#     print(record_metadata.partition)
#     print(record_metadata.offset)


def on_send_error(excp):
    print('I am an errback', exc_info=excp)

def signal_handler(sig, frame):
    print('Shutting down, please wait...')
    sys.exit(0)

signal.signal(signal.SIGINT, signal_handler)
print('Sending data')

tsv_file = open(p)
read_tsv = csv.reader(tsv_file, delimiter="\t")
count = 0
for tsv_row in read_tsv:
    if count > 1:
        print(tsv_row)
        csv_row = ",".join(tsv_row)
        print(csv_row)
        data = csv_row.encode('utf-8')
        producer.send('reviews', key=None, value=data).add_callback(on_send_success).add_errback(on_send_error)
    count += 1
    if count > 1000000 :
        break

# block until all async messages are sent
producer.flush()