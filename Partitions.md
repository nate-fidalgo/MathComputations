# Computing Partitions of a Number
This repo is meant for holding neat mathematical computer computations 

<br>
Partition.java is meant to be used to list all partitions for a given number to the screen or dump them to a file 
<br>
Like this 
<pre>
<code>
Partition p = new Partition( 7 ) ; // will print all the partitions of 7 with the permutations 

 6 + 1
 5 + 2
 5 + 1 + 1
 4 + 3
 4 + 2 + 1
 4 + 1 + 2
 4 + 1 + 1 + 1
 3 + 4
 3 + 3 + 1
 3 + 2 + 2
 3 + 2 + 1 + 1
 3 + 1 + 3
 3 + 1 + 2 + 1
 3 + 1 + 1 + 2
 3 + 1 + 1 + 1 + 1
 2 + 5
 2 + 4 + 1
 2 + 3 + 2
 2 + 3 + 1 + 1
 2 + 2 + 3
 2 + 2 + 2 + 1
 2 + 2 + 1 + 2
 2 + 2 + 1 + 1 + 1
 2 + 1 + 4
 2 + 1 + 3 + 1
 2 + 1 + 2 + 2
 2 + 1 + 2 + 1 + 1
 2 + 1 + 1 + 3
 2 + 1 + 1 + 2 + 1
 2 + 1 + 1 + 1 + 2
 2 + 1 + 1 + 1 + 1 + 1
 1 + 6
 1 + 5 + 1
 1 + 4 + 2
 1 + 4 + 1 + 1
 1 + 3 + 3
 1 + 3 + 2 + 1
 1 + 3 + 1 + 2
 1 + 3 + 1 + 1 + 1
 1 + 2 + 4
 1 + 2 + 3 + 1
 1 + 2 + 2 + 2
 1 + 2 + 2 + 1 + 1
 1 + 2 + 1 + 3
 1 + 2 + 1 + 2 + 1
 1 + 2 + 1 + 1 + 2
 1 + 2 + 1 + 1 + 1 + 1
 1 + 1 + 5
 1 + 1 + 4 + 1
 1 + 1 + 3 + 2
 1 + 1 + 3 + 1 + 1
 1 + 1 + 2 + 3
 1 + 1 + 2 + 2 + 1
 1 + 1 + 2 + 1 + 2
 1 + 1 + 2 + 1 + 1 + 1
 1 + 1 + 1 + 4
 1 + 1 + 1 + 3 + 1
 1 + 1 + 1 + 2 + 2
 1 + 1 + 1 + 2 + 1 + 1
 1 + 1 + 1 + 1 + 3
 1 + 1 + 1 + 1 + 2 + 1
 1 + 1 + 1 + 1 + 1 + 2
 1 + 1 + 1 + 1 + 1 + 1 + 1
 
 p.getNumberOfPartitions() ; // prints the total count in the case of this example 7 we have 63 partitions not including the number
 //Please note we are counting permutation normally  1+2+4 would the same as 1+4+2 or 4+2+1 ...etc and only counted once.
 
 If you want that affect dump all partitions to file partitions.dmp 
 Like this java Partition  > partitions.dmp
 then uses the AnalysisPartition.java class to filter what type of partitions you want to weed out and count  slight modification of the 
 AnalysisPartition.java file maybe required depending on what your filtering any questions or problems ask them by raising a issue of type Question on this 
 github repo
 
 Have fun with this one as partition generation / enumeration is a very difficult computationally intense process so done be let down if you can only go so high
 Shit gets big really fast.
 
 If you just want a count of how many partitions a given huge number has or a bound then this project is not the one. As you will have to wait on when i create the g-h hardy and ramanujan function that can compute these things without the computation difficults a little better
 Or if eulers pentagonal numbers is still the best way to get a count of the number or partitions for a given number its quick and uses pre-calculations and recursion.
 
 Yet this program was intended for actually seeing / visualizing all the partitions of a given number no matter ridiculous the amount of partitions is :)
 
 
</code>
</pre>
