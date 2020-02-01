# IMPORT LIBRARIES
from io import StringIO
import requests
import json

# CONSTNATS 
PAGE_SIZE = 20

# SETUP DATA BUFFER
evol_output = StringIO()

# GET THE COUNT OF EVOLUTION OBJECTS
link = "https://pokeapi.co/api/v2/evolution-chain"
count_response = requests.get(url=link).json()
count = count_response.get("count")

# READ THE OBJECTS ONE PAGE AT A SIZE
for evol in range( 1, count, PAGE_SIZE ):
        this_evol_link = link + "/?limit=25" + str(evol) + "&offset=" + str(PAGE_SIZE)
        evol_output.write(  requests.get(this_evol_link).text  )

contents = evol_output.getvalue()

# WRITE RESULTS TO OUTPUT FILE
local_path  = "/home/jeff/output/pokemon-site.output"
output_file = open( local_path, "w+" )
output_file.write( contents )
output_file.close()

# CLOSE DATA BUFFER
evol_output.close()

# WRITE RESULTS TO HDFS FILE
#   SETUP
from hdfs3 import HDFileSystem
hdfs_host = "localhost"
hdfs_port = 50700
hdfs_path = "/user/pokemon-site.output"

#   FILE MOVE
hdfs_sys = HDFileSystem( host=hdfs_host, port=hdfs_port )
#hdfs_sys.put( local_path, hdfs_path )

with hdfs_sys.open( hdfs_path, 'wb' ) as output_file:
    output_file.write(contents)
