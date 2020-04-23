# add airflow and its utilities to the path
export PATH=/home/hadoop/.local/bin:$PATH

cd ~
echo "
export PATH=/home/hadoop/.local/bin:PATH
" >> .bashrc

# install latest version of pip (Python package management tool)
sudo apt-get install python-setuptools
sudo apt-get install python-pip
# sudo pip install --upgrade pip

# install airflow
pip install apache-airflow

airflow initdb
