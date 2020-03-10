"""
Remove duplicates from the following list
weekdays = [‘sun’,’mon’,’tue’, 
            ‘sun’,’mon’,’tue’,
            ’wed’,’thu’,’fri’,
            ’wed’,’thu’,’fri’,
            ’sat’
           ]
"""
weekdays=['sun','mon','tue',
          'sun','mon','tue',
          'wed','thu','fri',
          'wed','thu','fri',
          'sat'
          ]

result=[]

# loop through and add each element to the 
# final list (if it isn't already in there)
for day in weekdays:
    if day not in result:
        result.append(day)

print( "Unique List:" )
print(result)
