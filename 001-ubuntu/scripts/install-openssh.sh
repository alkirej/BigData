#!/bin/bash

# Answer yes to are you sure typ questions
echo "Y
" > yes.txt

# install OpenSSH
sudo apt install openssh-client
sudo apt install openssh-server < yes.txt
rm yes.txt

echo "


" > enter.txt

# Generate shared key for this machine
ssh-keygen  < enter.txt
#ssh-copy-id localhost 
cp ~/.ssh/id_rsa.pub ~/.ssh/authorized_keys
rm enter.txt

