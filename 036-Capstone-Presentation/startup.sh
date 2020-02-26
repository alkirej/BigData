#!/bin/bash

echo
echo START-DFS.SH
echo
start-dfs.sh

echo
echo START-YARN.SH
echo
start-yarn.sh

if [ "cleanos" = `hostname` ]
then
    MAIN=/main
else
    MAIN=
fi

echo
echo STARTING KAFKA WITH TWO BROKERS
echo
./kafka-up.sh 2

