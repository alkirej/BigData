
cp /home/jeff/BigData/010-FlumeNetcat/netcat_spool.conf /opt/flume/conf

/opt/flume/bin/flume-ng agent -c /opt/flume/ -f /opt/flume/conf/netcat_spool.conf --name SpoolAgent -Dflume.root.looger=INFO,console

