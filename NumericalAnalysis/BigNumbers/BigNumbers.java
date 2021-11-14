import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * This class was developed for the short comings of BigInteger and BigDecimal class
 * Most arbitrary precision math libraries that i know arent able or dont have the built in functions
 * for raising to a biger than integer power size like BigInteger^BigInteger (only has BigInteger^integer)
 * 
 * For obvious reason in most case BigInteger^BigInteger could never be computed yet there are case like
 * 2^BigInteger power or a small number raise to a BigInteger power is computable in a reasonable amount of time.
 * 
 * This class has methods in it to handle BigInteger^BigInteger as well as it has methods to handle
 * BigDecimal^BigDecimal. Most arbitrary precision libraries only have BigDecimal^integer power.
 * 
 * Again this class implements methods to do BigDecimal^BigInteger as well as BigDecimal^BigDecimal
 * 
 * Improvements are still need for the case of BigDecimal^BigDecimal 
 * But this is a start 
 * 1) convergence has to be improved optimally
 * 2) start values for nth roots needs to be improved optimally for different cases
 * 3) roundoff/knowing the number of decimal digits that are significant and knowing how many iterations
 * are need to obtain a certain error bound
 * 
 * Those situation will make this class 100% accurate but for now its good enough
 * 
 * 
 * @author nate
 *
 */


public class BigNumbers {

	//Nice extension function for power to larger then Integer based sizes
	//Mostly impossible to use on modern computers or what we have today
	//Yet some short comings of BigInteger/BigDecimal and arbitrary precision libraries can be overcome with this to
	//Get a little farther in computing ridiculously big numbers/small numbers that one couldnt normally with arbitrary precision libraries functions
	//First Input a base number to raise an integer power to
	//Second Input a integer exponent >= 0 that is not restricted to the size of an integer so can be BigInteger!!!
	public BigDecimal pow(BigDecimal base, BigInteger exponent) {
		 
//		  if( exponent.signum() < 0)
//		  {
//			  BigDecimal recpbase = ( new BigDecimal("1") ).divide(base,base.scale(),RoundingMode.DOWN) ;
//			  pow(base, exponent.abs()) ;
//		  }
			  
		  BigDecimal result = new BigDecimal( BigInteger.ONE );
		  
		  if( base.signum() == 0 && (exponent.signum() == 0 || exponent.signum() == -1) )
		     throw new  ArithmeticException("BigInteger 0^0 or O^-n indetermined/undefined ") ;
		  
		  if( ( base.signum() < 0 && exponent.compareTo(BigInteger.ZERO) != 0 ) || exponent.signum() < 0 )
			  throw new  ArithmeticException("BigDecimal negative bases not excepted and negative exponents not excepted ") ;
			  
		  if( base.signum() == 0 )
			  return new BigDecimal( BigInteger.ZERO ) ;
		  
			  
		  while (exponent.signum() > 0) {
		    if (exponent.testBit(0)) result = result.multiply(base);
		    base = base.multiply(base);
		    exponent = exponent.shiftRight(1);
		  }
		  return result;
		}
	
	//Uses newtons roots to calculate nthroots 
	//aka iterations of the form x = x - (x^n -c) / nx^(n-1)
	// First Input is base number to compute the nthroot for can be BigDecimal (aka not restricted to only integer bases!
	// Second Input is integer exponent > 0 not restricted to integer sizes is a BigInteger size
	// Third parameter is the number of digits of precision to calculate the nthroot out to
	public BigDecimal nthRoot(BigDecimal base, BigInteger exponent , int digitprecision)
	{
		
		
		  if( base.signum() == 0 && (exponent.signum() == 0 || exponent.signum() == -1) )
			     throw new  ArithmeticException("Number 0^0 or O^-n indetermined/undefined ") ;
			  
		  if( ( base.signum() < 0 && exponent.compareTo(BigInteger.ZERO) != 0 ) || exponent.signum() < 0 )
			     throw new  ArithmeticException("BigDecimal negative bases not excepted and negative exponents not excepted ") ;
				  
		  if( base.signum() == 0 )
			     return new BigDecimal( BigInteger.ZERO ) ;
			  
		  if( exponent.signum() == 0 )
			  return new BigDecimal( BigInteger.ZERO ) ;
		  
			  
		int iteration = digitprecision ;
		int i = 0 ;
		int startval = 1 ;
		
		BigDecimal x = new BigDecimal(startval) ;
		BigDecimal N = new BigDecimal(exponent) ;
		BigDecimal BASE = new BigDecimal(base.toString()) ;
		BigDecimal ONE  = new BigDecimal("1") ;
		BigDecimal finalres = null ;
		
		while( i < iteration )
		{
			//later make method for pow( BigDecimal , BigInteger ) currently BigDecimal only can do integer size powers!
		BigDecimal num = (x.pow(N.toBigIntegerExact().intValueExact())).subtract(BASE) ; 
		
		BigDecimal den = N.multiply(pow( x , (N.subtract(ONE).toBigIntegerExact() ) ));       // N.multiply(  x.pow( (N.subtract(ONE)).intValueExact() ) ) ;
		BigDecimal res = num.divide(den ,digitprecision,RoundingMode.DOWN) ;
		finalres = x.subtract(res) ;
		x = finalres ;
		
		i++ ;
		System.out.println(i);
		}
		
		return x ;
		
	}
	
	
	public BigDecimal pow( BigDecimal base , BigDecimal exp , int digitprecision  )
	{
		BigInteger exp1 = exp.unscaledValue() ;
		
		BigInteger exp2 = new BigInteger( "10" ) ;
		exp2 = exp2.pow(exp.scale()) ;
		System.out.println( exp1 + "   " + exp2) ;
		
		BigDecimal result = nthRoot(base , exp2 , digitprecision) ;
		
		System.out.println( result ) ;
		result = pow( result , exp1) ;
		System.out.println( result ) ;
		//result = nthRoot(result , exp2 , digitprecision) ;
				
		return result ;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BigDecimal b = new BigDecimal( "2" ) ;
		BigInteger e = BigInteger.TWO ;
		BigNumbers bn = new BigNumbers() ;
		//System.out.println(bn.nthRoot( b , e , 100000 )) ;
		
		//System.out.println( bn.pow(new BigDecimal("-0.1"), BigInteger.ZERO) ) ; 
		//System.out.println(bn.nthRoot(new BigDecimal("345") , BigInteger.ZERO , 100 )) ;
		System.out.println( bn.pow( b , new BigDecimal("2.345") , 10 ) ) ;
		
	}

}
