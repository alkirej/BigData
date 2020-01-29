import requests
from io import StringIO

for  i  in range( 1, 10 ):
    link = "https://pokeapi.co/api/v2/language/"+i
    f = requests.get(link)
    print(f.text)
    print("\n")
    print(f.status_code)