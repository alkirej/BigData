import scala.util.matching.Regex

val data = """ 
The Charge of the Light Brigade
By Alfred, Lord Tennyson

I
Half a league, half a league,
Half a league onward,
All in the valley of Death
   Rode the six hundred.
“Forward, the Light Brigade!
Charge for the guns!” he said.
Into the valley of Death
   Rode the six hundred.

II
“Forward, the Light Brigade!”
Was there a man dismayed?
Not though the soldier knew
   Someone had blundered.
   Theirs not to make reply,
   Theirs not to reason why,
   Theirs but to do and die.
   Into the valley of Death
   Rode the six hundred.

III
Cannon to right of them,
Cannon to left of them,
Cannon in front of them
   Volleyed and thundered;
Stormed at with shot and shell,
Boldly they rode and well,
Into the jaws of Death,
Into the mouth of hell
   Rode the six hundred.

IV
Flashed all their sabres bare,
Flashed as they turned in air
Sabring the gunners there,
Charging an army, while
   All the world wondered.
Plunged in the battery-smoke
Right through the line they broke;
Cossack and Russian
Reeled from the sabre stroke
   Shattered and sundered.
Then they rode back, but not
   Not the six hundred.

V
Cannon to right of them,
Cannon to left of them,
Cannon behind them
   Volleyed and thundered;
Stormed at with shot and shell,
While horse and hero fell.
They that had fought so well
Came through the jaws of Death,
Back from the mouth of hell,
All that was left of them,
   Left of six hundred.

VI
When can their glory fade?
O the wild charge they made!
   All the world wondered.
Honour the charge they made!
Honour the Light Brigade,
   Noble six hundred!
"""

val re = new Regex( "\\W+" )
val wordList = re.split( data.toLowerCase )

val wordOnlyRdd = sc.parallelize( wordList )
val wordWithCountRdd = wordOnlyRdd.map( ( _,1) )
val reducedRdd = wordWithCountRdd.reduceByKey( _+_ )
reducedRdd.foreach( println )

