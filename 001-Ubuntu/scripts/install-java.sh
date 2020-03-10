#!/bin/sh
cd /opt
cd ~/

# Answer Yes to Are you sure type questions.
echo "Y
" > Yes.txt

# install Java
sudo apt update
sudo apt install openjdk-8-jdk-headless openjre-8-headless < Yes.txt
sudo apt install openjdk-8-jdk openjdk-8-jre < Yes.txt
#Add to bash profile
sudo ln -s /opt/jdk1.8.0_221/bin/java /usr/bin/java

rm Yes.txt
