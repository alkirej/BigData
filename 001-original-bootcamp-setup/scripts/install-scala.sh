#!/bin/bash

# install in /opt directory
cd /opt

# download scala 
sudo wget www.scala-lang.org/files/archive/scala-2.11.8.deb

# install the package
sudo dpkg -i scala-2.11.8.deb

# add the following lines to the end of your .bash_profile
#   export SCALA_HOME=/usr/share/scala/bin
#   export PATH=$SCALA_HOME/bin:$PATH
