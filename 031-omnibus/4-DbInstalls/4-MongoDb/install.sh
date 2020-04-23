#!/bin/bash
# AFTER INSTALL, execute the following command to connect to mongoDb server:
# mongo

# SETUP NECESSARY KEYS TO INSTALL APPS
sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 4B7C549A058F8B6B
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv EA312927

# DOWNLOAD AND INSTALL FILES
echo "deb http://repo.mongodb.org/apt/ubuntu "$(lsb_release -sc)"/mongodb-org/4.2 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-4.2.list
sudo apt-get update
sudo apt-get install -y mongodb-org

sudo systemctl daemon-reload
sudo systemctl start mongod
sudo systemctl enable mongod


