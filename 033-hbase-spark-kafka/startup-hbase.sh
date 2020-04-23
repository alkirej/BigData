#!/bin/bash
# NOTE: After hbase starts, you can use web browser on port 16010
# (http://localhost:16010) to connect to hbase

# To open an hbase shell, execute this command:
# hbase shell

start-hbase.sh
local-master-backup.sh start 2
local-regionservers.sh start 3
