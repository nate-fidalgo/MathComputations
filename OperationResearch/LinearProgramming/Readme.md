
The Simplex method is now implemented test output of a LP is given in the test main!
<br>
This code also implements the Dual Simplex algorithm so one can solve primal or dual problems whatever you want!
<br>
One should get this output after running the program.
<br>

<pre>
<code>
The Simplex Method for LPs!!! 
0 72 
1 96 
5 60 
-1 912 
The OptimalValue = 912
The coordinates for optimal value are 
x0 = 72 , x1 = 96 , x5 = 0 , 
The slack vars are at col indexes 
3 4 5 
All other col indexs in tableau are non slack variables 
</code>
</pre>
<br>
The orginal problem has the tableau of this for the constraints and objective function
<br>
<pre>
<code>
2 1 1 1 0 0 240 
1 3 2 0 1 0 360 
2 1 2 0 0 1 300 
-6 -5 -4 0 0 0 0 
</code>

</pre>
<br>
<pre>
<code>
the objective function is z = 6x0+5x1+4x2 to be maximized!
subjected to the constraints
2x0+x1+x2 <= 240
x0+3x1+2x2 <= 360
2x0+x1+2x2 <= 300
x0,x1,x2 >= 0 
</code>

</pre>
<br>
More explaination to come because it kind of hard to know how to properly use this code. Without some examples on how to use my functions and how the algorithms for linear programming work! <br>
A few performance improvement/revisions are being aftered in but other then this it solves the subject of linear programming problems completely
<br>
Probably a youtube video is to come on this wonderful subject from me.
