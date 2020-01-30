
# cp /home/jeff/BigData/010-FlumeNetcat/spool.conf /opt/flume/conf

/opt/flume/bin/flume-ng agent -c /opt/flume/ -f /home/jeff/BigData/010-FlumeNetcat/spool.conf --name SpoolAgent -Dflume.root.looger=INFO,console

