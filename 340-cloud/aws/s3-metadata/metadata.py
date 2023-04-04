#! /usr/bin/python3

import json
import boto3
from boto.s3.connection import S3Connection

ec2_key="AK--some--AWS--key--"
ec2_secret="--Your--AWS--secret--password-----------"

amazon_connection = S3Connection(ec2_key, ec2_secret)
bucket = amazon_connection.get_bucket("em-capsilon-poc")

"""
for key in bucket.list():
    akey = bucket.get_key(key.name)
    print( akey )
    if ( akey == "em-capsilon-poc,test.txt" ):
        print( akey )
    
    print( akey.get_metadata("Customer") )
    print( akey.get_metadata("RunDate") )
    print( akey.get_metadata("FolderId") )
    print( akey.get_metadata("DocumentTypeId") )
    print( akey.get_metadata("MailItemId") )
    print( akey.get_metadata("PageNumber") )
    print( akey.get_metadata("FileName") )
    print( akey.get_metadata("FilePath") )
"""

print ( " =============================== " )

s3 = boto3.client("s3")
response = s3.list_buckets()

print( "Existing buckets" )
for bucket_info in response["Buckets"]:
    amazon_connection = S3Connection(ec2_key, ec2_secret)
    bucket = amazon_connection.get_bucket( bucket_info["Name"] )
    
    print( f'    {bucket_info["Name"]}' )
    for item in bucket.list():
        key = bucket.get_key( item.name )
        # print( f'        {key.name}' )
        if ( key.name == 'test.txt' ):
            print( f'            meta-data: {key.metadata}' )
            print( f'            Customer:' )

            print( f'                {key.metadata["customer"]}' )

