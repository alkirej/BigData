
# cp /home/jeff/BigData/011-FlumeExecSeq/exec.conf /opt/flume/conf

/opt/flume/bin/flume-ng agent -c /opt/flume/ -f /home/jeff/BigData/011-FlumeExecSeq/exec.conf --name ExecAgent -Dflume.root.looger=INFO,console

