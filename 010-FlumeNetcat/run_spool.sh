#!/bin/bash
/opt/flume/bin/flume-ng agent -c /opt/flume/ -f /home/jeff/git/BigData/010-FlumeNetcat/spool.conf --name SpoolAgent -Dflume.root.looger=INFO,console

