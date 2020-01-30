sudo mysqladmin create project003

sudo mysql <<!
    CREATE USER 'jeff'@'localhost' IDENTIFIED BY 'jeff';
    GRANT ALL PRIVILEGES ON * . * TO 'jeff'@'localhost';
    COMMIT;
!

# TO CONNECT TO MYSQL
# mysql -u jeff -pjeff
