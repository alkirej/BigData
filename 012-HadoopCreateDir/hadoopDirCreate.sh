echo START HADOOP
start-dfs.sh
start-yarn.sh

echo CREATE /tmp  /usr and /data  directories in hdfs
hdfs dfs -mkdir /tmp
hdfs dfs -mkdir /usr
hdfs dfs -mkdir /data

echo LIST ROOT DIRECTORY TO LOOK FOR SUCCESSFUL CREATE OF NEW DIRECTORIES
hdfs dfs -ls /

