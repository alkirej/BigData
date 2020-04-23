'''
The difference between return and yield is that yield is required
to keep the functions state (variable values and execution location)
so that the next time the function is called it can pick up right
where it left off.  It also may throw a StopIteration exception
potentially requiring more work from the calling function.

Return does not need to remember its state.
'''

def fibonacci():
    """
    routine to store the loop with the yield example.
    the loop will compute the fibonacci sequence one
    item at a time (and yield the result)
    """
    firstNum  = 0
    secondNum = 1
    for fib in range(100):
        yield secondNum
        firstNumCopy = firstNum
        firstNum = secondNum
        secondNum = firstNumCopy + secondNum

# Create a generator object to compute fibonacci numbers
fibObject = fibonacci()

# Compute fibonacci sequence. Each item is "yield"ed from
# the loop in fibonacci()
print( "First 25 numbers in the Fibonacci sequence:" )
for i in range(25):
    print( next(fibObject) )

# Compute more of the same fibonacci sequence.
print()
print( "The next 5 numbers in the Fibonacci sequence:" )
for i in range(5):
    print( next(fibObject) )

print()
