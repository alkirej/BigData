#!/bin/bash
/opt/flume/bin/flume-ng agent -c /opt/flume/ -f /home/jeff/git/BigData/010-FlumeNetcat/netcat.conf --name NetcatAgent -Dflume.root.looger=INFO,console

