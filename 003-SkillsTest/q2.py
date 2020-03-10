"""
Given an unsorted integer array, find the smallest missing 
positive integer.

- Example 1: Input: [1,2,0] => 3
- Example 2: Input: [3,4,-1,1] => 2 
"""
# an example of an unsorted list
unsortedList = [ 1, 2, 0, -8, 64, 3, 10, 5, 8, 1, 4, 9, 123 ]

# set up preconditions for loop
done = False
checkingFor = 0

# loop until the desired number is not in the list
# and print it out
while (not done):
    checkingFor = checkingFor + 1
    if not checkingFor in unsortedList:
        print( checkingFor )
        done=True
