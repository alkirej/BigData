unsortedList = [ 1, 2, 0, -8, 64, 3, 10, 5, 8, 1, 4, 9, 123 ]

done = False
checkingFor = 0

while (not done):
    checkingFor = checkingFor + 1
    if not checkingFor in unsortedList:
        print( checkingFor )
        done=True
