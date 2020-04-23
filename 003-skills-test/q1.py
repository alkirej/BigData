from random import *
"""
Create a list and fill it with 30 random numbers

Create a method that returns the name and id of the
   object (Note: a class with name and id fields
   will be necessary.)
"""
class Question_1:
    def __init__( self, id: int, name: str ):
        """
        Question_1 class constructor.  Takes
        integer id and string type and simply stores them
        in object level variables. 
        """
        self.id   = id
        self.name = name

    def __str__(self):
       """
       toString() method. Converts the current object
       into a human readable string.
       """
       return str(self.id) + " " + self.name

def printIdAndName():
    """
    Function to create a Question_1 object and print it out
    using the __str__ method (implicitly converting it to
    a string) 
    """ 
    sample: Question_1 = Question_1( 42, "Bald Monkey" )
    print( sample )

q1List = []

# create 30 random #s and create a list from them
for i in range(30):
    q1List.append( randint(1,100) )

# print the list and blank space and then the name and id of an object
print( q1List )
print( )
printIdAndName()
