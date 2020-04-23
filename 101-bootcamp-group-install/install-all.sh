#!/bin/bash
# get user name for filling paths later
USER_ID=`whoami`
CWD=`pwd`

if [ -z "$USER_ID" ]
then
    USER_ID='hadoop'
fi
export USER_ID

LF=~/hadoop_ecosystem_install.log
sudo echo

echo ... Installing Java ...
echo "... Installing Java ..." 2&> $LF
01-Java/installJava.sh 2&>> $LF 
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
echo

echo ... Installing Scala ...
echo "... Installing Scala ..." 2&>> $LF
03-Scala/install_scala.sh 2&>> $LF
export SCALA_HOME=/usr/share/scala/bin
export PATH=$SCALA_HOME/bin:$PATH
echo

echo ... Installing Sbt ...
echo "... Installing Sbt ..." 2&>> $LF
04-Sbt/install_sbt.sh 2&>> $LF
echo

echo ... Installing OpenSSH ...
echo "... Installing OpenSSH ..." 2&>> $LF
05-OpenSSH/openssh.sh 2&>> $LF
echo

echo ... Installing Spark ...
echo "... Installing Spark ..." 2&>> $LF
${CWD}/11-Spark/InstallSpark.sh 2&>> $LF
echo

export SPARK_HOME=/opt/spark
export PATH=$PATH:$SPARK_HOME/bin 


cd ~
echo "
export JAVA_HOME=$JAVA_HOME
export SPARK_HOME=/opt/spark
export SCALA_HOME=/usr/share/scala/bin

export PATH=/home/$USER_ID/.local/bin:$SCALA_HOME/bin:$JAVA_HOME/bin::$SPARK_HOME/bin:\$PATH
" > ~/.bash_profile

echo "
source ~/.bash_profile
" >> ~/.bashrc

echo All Done !!!
echo
echo Source .bash_profile and your are ready to go
echo
echo
