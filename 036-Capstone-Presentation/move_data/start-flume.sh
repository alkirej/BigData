#!/bin/bash
/opt/flume/bin/flume-ng agent -c /opt/flume/ -f task33-flume-spool.conf --name GameSpoolAgent -Dflume.root.looger=ERROR,console &
/opt/flume/bin/flume-ng agent -c /opt/flume/ -f task33-flume-spool.conf --name RssSpoolAgent  -Dflume.root.looger=ERROR,console &

