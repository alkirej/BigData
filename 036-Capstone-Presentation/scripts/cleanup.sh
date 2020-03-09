#!/bin/bash

hdfs dfs -rm /data/backup/game_data/*
hdfs dfs -rm /data/backup/rss_data/*
hdfs dfs -rm /data/game_data/*
hdfs dfs -rm /data/rss_data/*
hdfs dfs -rm -r /user/hive/warehouse/capstone.db

stop-dfs.sh
stop-yarn.sh
killall java
sleep 1
killall -9 java

cp /home/jeff/main/git/BigData/036-Capstone-Presentation/move_data/backup/data/* /home/jeff/main/git/BigData/036-Capstone-Presentation/move_data/data
cp /home/jeff/main/git/BigData/036-Capstone-Presentation/move_data/backup/rss_data/* /home/jeff/main/git/BigData/036-Capstone-Presentation/move_data/rss_data

# drop mysql database 
mysql -u jeff -pjeff < dropMeta.sql

/home/jeff/main/git/BigData/036-Capstone-Presentation/scripts/startup.sh

schematool -dbType mysql -initSchema

rm /home/jeff/main/git/BigData/036-Capstone-Presentation/move_data/data/*
rm /home/jeff/main/git/BigData/036-Capstone-Presentation/move_data/rss_data/*

cp /home/jeff/main/git/BigData/036-Capstone-Presentation/move_data/backup/data/* /home/jeff/main/git/BigData/036-Capstone-Presentation/move_data/data/
cp /home/jeff/main/git/BigData/036-Capstone-Presentation/move_data/backup/rss_data/* /home/jeff/main/git/BigData/036-Capstone-Presentation/move_data/rss_data/

cd ../createdb
./create_db.sh
