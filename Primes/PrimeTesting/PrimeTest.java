import java.math.BigInteger;
import java.util.Random;

public class PrimeTest {

	//Pépin's test for primality of fermat numbers.
	public boolean Pepins_test( int n )
	{
		BigInteger three = new BigInteger("3") ;
		BigInteger Fn = getFermatNumber(n) ;
		BigInteger Fn_pow = Fn.subtract(BigInteger.ONE) ;
		Fn_pow = Fn_pow.divide(BigInteger.TWO) ;
		BigInteger result = null ;
		
		result = three.modPow(Fn_pow, Fn) ;
		result = result.add(BigInteger.ONE) ;
		if( result.compareTo(Fn) == 0 )
		return true ;
		
		return false ;
		
		
	}
	
	//Nice extension function for power to larger then Integer based sizes
	//Mostly impossible to use on modern computers or what we have today
	//Yet some short comings of BigInteger and arbitrary precision libraries can be overcome with this to
	//Get a little farther in computing ridiculously big numbers that one could normally do on even with arbitrary precision libraries
	//But mostly useless because it exceeds are computing physical limits as of currently
	public BigInteger pow(BigInteger base, BigInteger exponent) {
		  BigInteger result = BigInteger.ONE;
		  
		  if( base.signum() == 0 && (exponent.signum() == 0 || exponent.signum() == -1) )
		     throw new  ArithmeticException("BigInteger 0^0 or O^-n indetermined/undefined ") ;
		  
		  if( base.signum() < 0 || exponent.signum() < 0 )
			  throw new  ArithmeticException("BigInteger negative bases not excepted and negative exponents not excepted ") ;
			  
		  if( base.signum() == 0 )
			  return BigInteger.ZERO ;
		  
			  
		  while (exponent.signum() > 0) {
		    if (exponent.testBit(0)) result = result.multiply(base);
		    base = base.multiply(base);
		    exponent = exponent.shiftRight(1);
		  }
		  return result;
		}
	
	//Simple method to compute fermat numbers
	//They get big very fast
	public BigInteger getFermatNumber(int n)
	{
		BigInteger power = BigInteger.TWO ;
		power = power.pow(n) ;
		BigInteger result = pow(BigInteger.TWO, power ) ;
		result = result.add(BigInteger.ONE) ;
		return result ;
	}
	
	
	public boolean Proths_test( BigInteger p , int accuracy  )
	{
        BigInteger power = (p.subtract(BigInteger.ONE)).divide(BigInteger.TWO) ;
        BigInteger maxrand = p ;
        
        BigInteger a = generateRandom(BigInteger.ONE , maxrand) ;
        int i = 0 ;
		
		while( i < accuracy)
		{
			a = a.modPow(power, p) ;
			a = a.add(BigInteger.ONE) ;
			if( a.compareTo(p) == 0 )
			  return true ;
				
			a = generateRandom(BigInteger.ONE , maxrand) ;
			i++ ;
		}
		
		return false ; // probably composite 
		
	}
	
	//generates a random number between minLimit and maxLimit exclusively
	public BigInteger generateRandom(BigInteger minLimit , BigInteger maxLimit) {
	     BigInteger bigInteger = maxLimit.subtract(minLimit);
	      Random randNum = new Random();
	      int len = maxLimit.bitLength();
	      BigInteger res = new BigInteger(len, randNum);
	      if (res.compareTo(minLimit) <= 0)
	         res = res.add(minLimit);
	      if (res.compareTo(bigInteger) >= 0)
	         res = res.mod(bigInteger).add(minLimit);
		         
	      return res ;
	}
	
	
	//computes proth numbers which are numbers of the form k*2^n + 1 where k is odd and k < 2^n 
	public BigInteger getProthNum1(BigInteger k , BigInteger n )
	{
		BigInteger result = BigInteger.TWO ;
		result = pow(result,n) ;
		result = k.multiply(result) ;
		result = result.add(BigInteger.ONE) ;
		
		return result ;
		
	}
	
	
	public BigInteger getProthNumS( String k ,String n )
	{
		return getProthNum1(new BigInteger(k) , new BigInteger(n) ) ;
		
	}
	
	public BigInteger getProthNumL( long k ,long n )
	{
		return getProthNum1(new BigInteger("" + k) , new BigInteger("" + n) ) ;
		
	}
	
	
	//Test main for computing 
	//And the verification of F33
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PrimeTest pt = new PrimeTest() ;
		BigInteger power = BigInteger.TWO ;
		for( int k = 0 ; k <10 ; k++)
		{
		System.out.println( pt.getFermatNumber(k)) ;
		}
		
		System.out.println("Proth numbers ") ;
		System.out.println(pt.getProthNumL(3, 3)) ;
		//System.out.println(pt.getProthNumS("10223", "31172165")) ;
		System.out.println("Proth test = " + pt.Proths_test( pt.getProthNumS("10223", "31172165") , 20  ) ) ;
				
//		for( int j = 0 ; j < 40 ; j++)
//		{
//			System.out.println( "Fn = " + j + " "  + pt.Pepins_test( j ) ) ;
//		}
		
	}

}
