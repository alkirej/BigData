#!/bin/bash
cd /opt

# download python
sudo wget https://www.python.org/ftp/python/3.6.9/Python-3.6.9.tgz
sudo chmod 755 /opt/

# untar file
sudo tar -xf Python-3.6.9.tgz
sudo rm Python-3.6.9.tgz

# set permissions
sudo chmod 755 /opt/

# set a symbolic link from python to the just installed python
sudo ln -s /usr/bin/python3 /usr/bin/python

