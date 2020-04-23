#!/bin/bash

if [ "$1" == "" ]
then
    FLUME="yes"
else
    FLUME="no"
fi

# set Project Root Directory
PRD=/home/jeff/main/git/BigData/036-Capstone-Presentation/
LOGFILE=/home/jeff/main/git/BigData/036-Capstone-Presentation/logs/output.log

echo | tee $LOGFILE
echo Step 1 - Ensure Hadoop is started | tee -a $LOGFILE
echo | tee -a $LOGFILE

cd $PRD
cd scripts
./startup.sh 2&>> $LOGFILE
sleep 10

echo | tee -a $LOGFILE
echo Step 2 - Start flume agents | tee -a $LOGFILE
echo | tee -a $LOGFILE

cd $PRD
cd move_data
if [ "${FLUME}" == "yes" ]
then
    ./start-flume.sh 2&>> $LOGFILE
fi
sleep 20


echo | tee -a $LOGFILE
echo Step 3 - Pull Data from NBA, ESPN, and FoxSports | tee -a $LOGFILE
echo | tee -a $LOGFILE

cd $PRD
cd data_pull
./data-pull.sh 2&>> $LOGFILE
sleep 5


echo | tee -a $LOGFILE
echo Step 4 - Move Data to Flume location to have it transferred to HDFS | tee -a $LOGFILE
echo | tee -a $LOGFILE

cd $PRD
cd data_pull
./move-data-to-spool.sh 2&>> $LOGFILE


echo | tee -a $LOGFILE
echo step 5 - Process new data and add it to the database | tee -a $LOGFILE
echo | tee -a $LOGFILE

cd $PRD
cd data_process
./submit.sh 2&>> $LOGFILE
