import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class FermatF {

	private static final int PR = 20 ;
	/*		Psuedo Code for Legacy old prime factorization algorithm found on wikipedia
	 *      Implemented in java but any major langauge is pretty much the same way
	 *      Around the same efficiency as Trial and Error , Brute Force methods
	 * 
	 * 		FermatFactor(N): // N should be odd
	 
			    a ← ceiling(sqrt(N))
			    b2 ← a*a - N
			    repeat until b2 is a square:
			        a ← a + 1
			        b2 ← a*a - N 
			     // equivalently: 
			     // b2 ← b2 + 2*a + 1 
			     // a ← a + 1
			    return a - sqrt(b2) // or a + sqrt(b2)
			
		}
		
		*****/
	
	
	public static BigInteger FermatFactor(BigInteger N)
	{
		if( N.mod(BigInteger.TWO).equals(BigInteger.ONE) == true )
		{
		   BigDecimal n = new BigDecimal(N) ;
		   BigInteger a =  (n.sqrt( new MathContext(PR, RoundingMode.CEILING) )).toBigInteger()   ;
		   BigInteger b2 = a.multiply(a)  ; 
		   b2 = b2.subtract(N) ;
		   BigDecimal bb2 = new BigDecimal(b2) ; 
		   
		   boolean notasquare = false ;
		try {
		   bb2.sqrt( new MathContext(PR, RoundingMode.CEILING) ).toBigIntegerExact() ;
		   }
		   catch(Exception e)
		   {
			   notasquare  = true ;
		   }
		   
		   
		   
		 while( notasquare == true )
		 {
			   a = a.add(BigInteger.ONE) ;
			   b2 = (a.multiply(a)).subtract(N) ;
			   bb2 = new BigDecimal(b2) ; 
			   try {
			   bb2.sqrt( new MathContext(PR, RoundingMode.CEILING) ).toBigIntegerExact() ;
			   notasquare = false ;
			   }
			   catch(Exception e)
			   {
				   notasquare = true ;
			   }
			   System.out.println(a) ;
		 }
		   
		   
		   return a.subtract(b2.sqrt())	;
			
		}
		
		return BigInteger.TWO ;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BigInteger res = FermatFactor(new BigInteger("2345678917")) ;
		System.out.println( "Factor is =" + res) ;
        System.exit(0);
		
	}

}
