cd /opt

# Download spark
#sudo wget http://apache.mirrors.tds.net/spark/spark-2.4.4/spark-2.4.4-bin-hadoop2.7.tgz 
sudo wget https://www-us.apache.org/dist/spark/spark-2.4.5/spark-2.4.5-bin-hadoop2.7.tgz
# unzip spark
sudo tar -xf spark-2.4.5-bin-hadoop2.7.tgz
sudo chmod 755 /opt/

sudo ln -s /opt/spark-2.4.5-bin-hadoop2.7 /opt/spark

