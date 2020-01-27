
weekdays=['sun','mon','tue',
          'sun','mon','tue','wed','thu','fri',
          'wed','thu','fri','sat'
          ]

result=[]

for day in weekdays:
    if day not in result:
        result.append(day)

print( "Unique List:" )
print(result)
