
cp /home/jeff/BigData/010-FlumeNetcat/netcat_log.conf /opt/flume/conf

/opt/flume/bin/flume-ng agent -c /opt/flume/ -f /opt/flume/conf/netcat_log.conf --name NetcatAgent -Dflume.root.looger=INFO,console

