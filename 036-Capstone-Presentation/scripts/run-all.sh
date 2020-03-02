#!/bin/bash

# set Project Root Directory
PRD=/home/jeff/main/git/BigData/036-Capstone-Presentation/

echo
echo Step 1 - Ensure Hadoop is started
echo

cd $PRD
cd scripts
./startup.sh


echo
echo Step 2 - Start flume agents
echo

cd $PRD
cd move_data
./start-flume.sh


echo
echo Step 3 - Pull Data from NBA, ESPN, and FoxSports
echo

cd $PRD
cd data_pull
./data_pull.sh


echo
echo Step 4 - Move Data to Flume location to have it transferred to HDFS
echo

cd $PRD
cd data_pull
./move-data-to-spool.sh

echo
echo step 5 - Process new data and add it to the database
echo

cd $PRD
cd data_process
./submit.sh
