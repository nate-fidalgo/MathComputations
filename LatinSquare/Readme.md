
### Latin Squares Program

<br>
This program can be used to generate a latin square of a certain nxn size or generate all latin squares if any is small and tells you how many exist if any is small. The number of latin square big really really quick so you may need to allocate more java heap memory to go higher or wait longer to get a result for higher n! You can only do so much with this program as enumerating latin squares or generating large latin square is an NP complete/hard type of problem. It has very similar connections in generating all finite groups multiplication/addition tables.

<br>
Here is output from the program to generate a 16x16 latin square you for example
<pre>
<code>
nate@nate-DX4870:~/Desktop/Latinsquare$ java Backtrack

4 1 2 3 5 6 7 8 9 10 11 12 13 14 15 16 
1 2 3 4 6 5 8 7 10 9 12 11 14 13 16 15 
2 3 1 5 4 7 6 9 8 11 10 13 15 16 12 14 
3 4 5 1 2 8 9 6 7 12 13 14 16 15 10 11 
5 6 4 2 1 3 10 11 12 7 15 16 8 9 14 13 
6 5 7 8 3 1 2 4 13 14 16 15 9 10 11 12 
7 8 6 9 10 2 1 14 15 16 3 4 11 12 13 5 
8 7 9 6 11 4 3 15 16 13 14 1 12 2 5 10 
9 10 8 7 12 11 13 16 14 15 1 2 3 5 4 6 
10 9 11 12 14 15 16 13 1 2 4 3 5 6 7 8 
11 12 10 13 15 16 14 1 2 3 5 6 4 7 8 9 
12 11 13 10 16 14 15 2 3 1 6 5 7 8 9 4 
13 14 15 16 7 9 4 12 5 6 2 8 10 11 3 1 
14 13 16 15 8 10 12 3 11 5 9 7 1 4 6 2 
15 16 12 14 9 13 11 5 4 8 7 10 6 1 2 3 
16 15 14 11 13 12 5 10 6 4 8 9 2 3 1 7 


</code>
</pre>
<br>

TODO: make this readme file more user friendly i havent yet explained how to build , run , and modify to your liking to achieve what you want to search for in latin squares yet... its to come.

<br>
<br> 
But just want to get it post up in the repo first. Then all explain the connection between latin square > magic squares > suduko. It takes a backtracking algorithm to compute latin square there is no good way or close formula known to count the exact number of latin square. There are upper bounds but even so the numbers get huge and a computer soon wont beable to enumerate n > 11. n = 12 just for the reduce latin square size is approximate to be order of magnitude 
1.62 × 10^44 which is ridiculous for a computer to compute. So enumeration is not going to happen but generating a few of a large n size or enumerating all of them when n is small like 3x3 , 4x4, 5x5 ... is doable with this program. Consider your self luck if you can do a 7x7 or god forbid an 8x8 everything higher would require special computer aka supercomputers or huge distributive networks of computer. I myself can get to 6x6 but that is only by increasing the java heap and waiting a while. I could improve on this program to make it less memory intensive in the backtrack sure but your still at the mercy of speed of counting ridiculous size like 11x11 boards have reduce latin square total count  5,363,937,773,277,371,298,119,673,540,771,840  and  total count of all latin square 776,966,836,171,770,144,107,444,346,734,230,682,311,065,600,000. Not happening with home computer hardware :)

<br>
None the less its still a great program and definitely important to know how to do backtracking algorithms like DFS to solve some hard problems. 
Or gain insight into new problems
