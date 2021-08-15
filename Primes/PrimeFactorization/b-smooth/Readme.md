This code is to compute b-smooth numbers and factor bases. 
<br> Example below of code. Class use are just P class and FactorBases so copy, them compile them, and use to your liking
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
