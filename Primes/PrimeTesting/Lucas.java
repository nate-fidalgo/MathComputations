import java.math.BigInteger;

public class Lucas {

	//This is a very powerful mersenne prime test
	//Its an if and only test and currently the best
	//known algorithm that we have
	/*
        Determine if Mp = 2p − 1 is prime for p > 2
		Lucas–Lehmer(p)
    	var s = 4
    	var M = 2^p − 1
    	repeat p − 2 times:
        s = ((s × s) − 2) mod M
    	if s == 0 return PRIME else return COMPOSITE

	 */
	
	
	public boolean LucasLehmer(int p)
	{
		BigInteger s = new BigInteger("4") ;
		BigInteger M  = new BigInteger("2") ;
		M = M.pow(p) ;
		BigInteger MM = M ;
		BigInteger r_m[] = null ;
		M = M.subtract(BigInteger.ONE) ;
		for( int i = 0 ; i < p-2 ; i++ )
		{System.out.println("gotttt0");
			s = s.multiply(s).mod(M) ;
			System.out.println("gotttt1");
			s = s.subtract(BigInteger.TWO) ;
			System.out.println("gotttt2");
			s = s.mod(M) ; //improve on this to by using the trick 
			System.out.println("gotttt3");
			//All failed attempts below to convert it to faster
			//r_m = s.divideAndRemainder(MM) ;
			//s = (r_m[0].add(r_m[1])).mod(M) ;		
		
			//	s = s.mod(MM) ;
			
		//	s = s.add(s.shiftRight(s.bitLength() - p)) ;
		//	s = s.mod(M) ;
			System.out.println("At p = " + (i+1) + " to " + (p-2)) ;
		}
		
		if( s.compareTo(BigInteger.ZERO) == 0 )
			return true ;
		
		return false ;
		
	}
	
	//Note this method isnt very useful
	//As it compute the first few lucas numbers
	//After the first few they just get to big to be 
	//useful for viewing anything intelligent out of it
	//Normally to keep them in managable sizes for a computer.
	//You mod by the Mennersene prime to keep it lower then that number of bits.
	//first input is the lucas number to run up to B out puts the 1 thru Bth lucas numbers
	//second input if set to null does no modding else it mod the lucas numbers by modnum 
	//returns lucas numbers or lucas numbers mod modnum
	public void dumpLucasNumbers( int B , BigInteger modnum )
	{
	
		BigInteger s = new BigInteger("4") ;

		if( modnum == null)
		{
			for( int i = 0 ; i < B-2 ; i++ )
			{
				s = s.multiply(s) ;
				s = s.subtract(BigInteger.TWO) ;
				System.out.println("Lucas numbers " + s) ;
			}
			
			return ;
		}
		
		
		for( int i = 0 ; i < B-2 ; i++ )
		{
			s = s.multiply(s) ;
			s = s.subtract(BigInteger.TWO) ;
			s = s.mod(modnum) ;
			System.out.println("Lucas numbers " + s) ;
		}
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Lucas L =  new Lucas();
		//Comment in for wishful thinking :)
		//System.out.println( L.LucasLehmer(105000000) ) ;
		//L.dumpLucasNumbers(10,new BigInteger("7"))  ;
		L.dumpLucasNumbers(10,null)  ;
		
	}

}
