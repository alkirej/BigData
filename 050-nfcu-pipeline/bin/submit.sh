#!/bin/bash 
JARS="--jars /home/jeff/git/BigData/100-NFCU-pipeline/jdbc-driver/*.jar"
EXEC_JAR=/home/jeff/git/BigData/100-NFCU-pipeline/target/scala-2.11/100-nfcu-pipeline_2.11-0.1.jar
CLASS="--class net.alkire.pipeline_nfcu.LoadData"
MASTER="--master local[*]"
MEMORY="--executor-memory 10G"
CORES="--total-executor-cores 4"

spark-submit ${JARS} ${CLASS} ${MASTER} ${MEMORY} ${CORES} ${EXEC_JAR}

