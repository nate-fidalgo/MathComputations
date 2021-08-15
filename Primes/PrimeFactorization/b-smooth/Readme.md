This code is to compute b-smooth numbers and factor bases. 
<br> Example below of code. Class used are just P class and FactorBases so copy them, compile them, and use to your liking
<pre>
<code>
  
  ...
	public static void main(String[] args) {

		FactorBases fb = new FactorBases() ;
		fb.generateFactorBase(7); //generate the 7-smooth factor bases
		System.out.println( "B-SMOOTH " + fb.isB_Smooth(33600 ) ) ; // test if 33600 is 7-smooth
		System.exit(0) ;
		
	}
  ...
</code>
</pre>
<br>

<pre>
<code>
Output for example 33600 gives:
B-SMOOTH true 

</pre>
</code>
<br>
One can see you can test any number for being B-smooth or run thru a list of numbers/canidates for being B-smooth.
Generate a B-smooth lookup table for fast factoring ...etc
