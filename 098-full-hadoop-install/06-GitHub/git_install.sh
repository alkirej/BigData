#!/bin/bash
echo "Y
" > yes.txt
sudo apt update
sudo apt install git < yes.txt

rm yes.txt

