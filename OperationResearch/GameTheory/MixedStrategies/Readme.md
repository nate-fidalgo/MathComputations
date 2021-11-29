This is the missing piece to the pure strategy C# solver. I wrote it in java because i already had the Linear Algebra package i wrote in java long ago.
This program relies on Linear Algebra to compute mixed strategies and expected value for optimal play.

<br>
To run the test main make sure you add the GameTheory.jar file into your classpath.
You should see output like below the example was for that below payoff matrix. I am still working on how to enumerate all optimal mixed strategies.
Find one isnt so bad of complexity but enumerating all of them is essentially going thru all nonsingular submatrices of the payoff matrix.
Since the number of square submatrice that are nonsingular can grow very fast this can get computationally intense.
<br>
Most of the time people only care about find one optimal mixed strategy for each player but eventually all add in code to enumerate all.
By running thru all submatrices.
<br>
I am taking a little longer in thinking on how to enumerate all optimal strategies in the most efficient way. Like factoring in dominance stragies to
cut down on the number of nonsingular submatrice to have to run thru. However in general its going to be resource intensive!
<br>
<br>
Right now to use practically you still have to modify FindOptimalStrategy and FindALLOptimalStrategy is what i said a work in progress.
<br>
Isnt hard to finish just have to think for a little while on the best approach to finish these two method which one done. Anybody can just entire a payoff matrix and it give you the mixed strategies ...etc (as a special case it will do what the C# solver did for pure strategy payoff matrices )
<br>
<pre>
<code>
Payoff Matrix is 
3	5	3	
4	-3	2	
3	2	3	
Game Excepted Value For Optimal Play = 3
Row Player Optimal Mixed Strategy 
1
0
0
Column Player Optimal Mixed Strategy 
1/2
0
1/2

</code>
</pre>
