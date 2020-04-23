#!/bin/bash

cd /home/jeff/main/git/BigData/016-KafkaProdConPython

for X in 0 1 2 3 4 5 6 7 8 9 
do
    python3 kafka_producer.py
done
