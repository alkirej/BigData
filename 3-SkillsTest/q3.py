'''
The difference between return and yield is that yield is required
to keep the functions state (variable values and execution location)
so that the next time the function is called it can pick up right
where it left off.  It also may throw a StopIteration exception
potentially requiring more work from the calling function.

Return does not need to remember its state.
'''

def fibonacci():
    firstNum  = 0
    secondNum = 1
    for fib in range(100):
        yield secondNum
        firstNumCopy = firstNum
        firstNum = secondNum
        secondNum = firstNumCopy + secondNum

fibObject = fibonacci()

print( "First 25 numbers in the Fibonacci sequence:" )
for i in range(25):
    print( next(fibObject) )

print()
print( "The next 5 numbers in the Fibonacci sequence:" )
for i in range(5):
    print( next(fibObject) )

print()
