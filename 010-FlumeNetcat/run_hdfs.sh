
cp /home/jeff/BigData/010-FlumeNetcat/netcat_hdfs.conf /opt/flume/conf/netcat_hdfs.conf

/opt/flume/bin/flume-ng agent -c /opt/flume/ -f /opt/flume/conf/netcat_hdfs.conf --name NetcatAgentHdfs -Dflume.root.looger=INFO,console

