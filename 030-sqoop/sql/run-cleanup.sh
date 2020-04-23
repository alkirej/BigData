hdfs dfs -rm -r /user/maria_dev/data
hdfs dfs -rm -r /user/maria_dev/usr
hdfs dfs -rm -r /user/maria_dev/occupation
hdfs dfs -rm -r /user/maria_dev/genre
hdfs dfs -rm -r /user/maria_dev/movie

hive -f cleanup.hql
