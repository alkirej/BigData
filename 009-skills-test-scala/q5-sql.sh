#!/bin/bash
#
# Shell script for Skills Test SQL question (question 5)
#
# Note: MySQL must be installed for this script to execute
# MySQL can be installed with the following command
# sudo apt install mysql-server

# After installing MySQL, create a user for your account
# sudo mysql
# CREATE USER 'jeff'@'localhost' IDENTIFIED BY 'jeff';
# GRANT ALL PRIVILEGES ON * . * TO 'jeff'@'localhost';
# FLUSH PRIVILEGES;

# Connect to the database and create a database
# mysql -u jeff -pjeff
# CREATE DATABASE skilltest

mysql -u jeff project003 < /home/jeff/git/BigData/009-SkillsTestInScala/q5.sql
