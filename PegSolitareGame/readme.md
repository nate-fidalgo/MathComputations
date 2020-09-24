## Peg Solitare Program!!!
<br>

To Run/Build copy the 3 java files 
<br>
<pre>
<code>
PegSolitaireSolver.java
PegState.java
PegMove.java

Build: 
javac PegSolitaireSolver.java // to compile/build the 3 class
Run:
java PegSolitaireSolver

</code>
</pre>
<br>
Currently the output should look like this 
<br>
FOUND SOLUTION!!!
<br>
| (0,2)-->(2,2) | (2,3)-->(2,1) | (1,1)-->(3,1) | (4,1)-->(2,1) |
<br>
<pre>
This is because by default its working on the board below change pegbrd to any board you want to solve and recompile/rerun.
2 = wall, 1=peg , 0=empty peg hole
</pre>
<br> 
<pre>
<code>

       //Modify this according to what peg board you want to solve in PegSolitaireSolver.java
       //Later all probably provide an easy file that you can just pass to PegSolitaireSolver.java so you dont have to recompile/rerun
	private static int pegbrd[][] = {
	{2,2,1,2,2},
	{2,1,1,2,2},
	{2,0,0,1,2},
	{2,0,0,0,2},
	{2,1,0,0,2}
	} ;
	
</code>
</pre>

Go here for javadocs for peg solitare program and associated class if you want to dig a little deeper into how its made. :)
<br>
https://nate-fidalgo.github.io/MathComputations/pegdocs/package-summary.html
<br>
Really neat backtracking algorithm so cool some pegboard you think up dont have a solution other do but this program can give you the answer to all boards!
