#!/bin/bash

echo "Y
" > yes.txt

sudo apt install openssh-client
sudo apt install openssh-server < yes.txt
rm yes.txt

echo "


" > enter.txt
ssh-keygen  < enter.txt
#ssh-copy-id localhost 
cp ~/.ssh/id_rsa.pub ~/.ssh/authorized_keys
rm enter.txt

