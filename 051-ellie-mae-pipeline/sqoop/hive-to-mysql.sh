/home/jeff/git/BigData/101-Ellie-Mae-pipeline/sql/setup.sh

export HADOOP_CLASSPATH=/opt/hive/lib/*

sqoop --options-file /home/jeff/git/BigData/101-Ellie-Mae-pipeline/sqoop/health-visit-from-hive.opt
