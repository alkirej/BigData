#!/bin/bash
hive < db_setup.hql

hdfs dfs -mkdir -p /data
hdfs dfs -mkdir -p /data/game_data
hdfs dfs -mkdir -p /data/rss_data

hdfs dfs -mkdir -p /data/backup
hdfs dfs -mkdir -p /data/backup/game_data
hdfs dfs -mkdir -p /data/backup/rss_data


