#!/bin/bash

# CLEAN UP PREVIOUS DERBY DATA AND START DERBY NETWORK CLIENT 
sudo rm -r metastore_db

# START HADOOP
start-dfs.sh
start-yarn.sh

# START DERBY NETWORKING CLIENS 
cd $HIVE_HOME 
export CLASSPATH=
startNetworkServer &

# SETUP HIVE'S METASTORE
schematool -dbType derby -initSchema

# SETUP HIVE TABLES FOR THIS TASK 
hive < createCsvTable.hql
hive < createJsonTable.hql

# USE PYSPARK TO LOAD CSV AND JSON DATA
python /home/jeff/main/git/BigData/032-Hive-Spark

# MAIN DIR INCLUDED IN PATH FOR VMs. CHANGE TO THIS ONE IF NOT ON A VM SETUP 
# python /home/jeff/git/BigData/032-Hive-Spark

