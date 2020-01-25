from random import *

def printIdAndType( outputObject ):
    print(   id( outputObject )   )
    print(   type( outputObject )   )

q1List = []

for i in range(30):
    q1List.append( randint(1,100) )

print( q1List )
print( )
printIdAndType( q1List)
