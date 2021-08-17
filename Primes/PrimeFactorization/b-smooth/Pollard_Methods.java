import java.math.BigInteger;
//Using package so that its not limited to overflows issues like
//c/c++ example above

public class Pollard_Methods {

		
	public BigInteger Pollards_rho_algorithm(BigInteger num)
	{
		BigInteger x = BigInteger.TWO ;
		BigInteger y = BigInteger.TWO ;
		BigInteger factor = BigInteger.ONE ;
		BigInteger difference ;
		while( factor.compareTo(BigInteger.ONE) == 0  )
		{
			x=G(x,num) ;
			y=G(G(y,num),num) ;
			difference = x.subtract(y) ;
			difference = difference.abs();
			factor = difference.gcd(num) ;

		}
		
		
		if( factor.compareTo(num) == 0 ) //if algorithm failed
		return BigInteger.ONE ;
		
		return factor; // if succeeded find factor
		
		
	}
	
	//computes the function (a^2+1) mod m
	//BigInteger package is used to get rid of overflow issue when computing big prime factors for factorization
	private BigInteger G( BigInteger a , BigInteger m)
	{
		a=a.pow(2) ;
		a=a.add(BigInteger.ONE) ;
		a=a.mod(m) ;
		return a;
	
	}
	
	
	//Computes different then base 10 and e log bases
	private double customLog(double base, double logNumber) {
	    return Math.log(logNumber) / Math.log(base);
	}
	
	
	public BigInteger Pollards_p_minus_one_algorithm(BigInteger num , int attempts)
	{
		int B = 100 ;
		BigInteger result = BigInteger.ONE ;
		int failedattempts = 0 ;

		while( failedattempts < attempts )
		{
			result = Pollards_p_minus_1_algorithm(B , num , false ) ;
			
			if( result.compareTo(BigInteger.ONE) != 0 && result.compareTo(num) != 0)
			return result ;
			
			if( result.compareTo(BigInteger.ONE) == 0)
			B = B * 10 ;
			
			if( result.compareTo(num) == 0)
			B = B / 10 ;
			
			System.out.println("B = " + B  ) ;
			failedattempts++ ;
			
		}
		
		
		return BigInteger.ONE;
	}
	
	public BigInteger Pollards_p_minus_1_algorithm(int B , BigInteger num , boolean randompick )
	{
		BigInteger factor = BigInteger.TWO ;
		FactorBases fb = new FactorBases() ;
		fb.generateFactorBase(B);
		int pbase[] = fb.getBase() ;
		BigInteger M = generateM(pbase , B ) ;
		
		//factor = pow(BigInteger.TWO,M) ; //a=2 later add random coprimes selection for a
		factor = factor.modPow(M, num) ;
		factor = factor.subtract(BigInteger.ONE) ;
		factor = factor.gcd(num) ;
		return factor ;
			

	}
	
	private BigInteger generateM(int[] pbase , int B ) {
		// TODO Auto-generated method stub
		BigInteger M = BigInteger.ONE;
		BigInteger temp = BigInteger.ONE;
		int pexponent = 0;
		for( int i = 0 ; i < pbase.length ; i++ )
		{
			pexponent = (int) Math.floor( customLog( pbase[i]	, B  ) ) ;
			temp = new BigInteger(Integer.toString(pbase[i])) ;
			M = M.multiply(temp.pow(pexponent))  ;
			
		}
		
		
		return M;
	}

	
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
	
	
	public BigInteger William_P_plus_one_algorithm(BigInteger num)
	{
		BigInteger A = new BigInteger("5") ;    //A is choosen greater then 2 at random to characterize the Lucas sequence still curious if educate choices matter in some way
		BigInteger M = new BigInteger("5040") ; //values used for M are successive factorials but possible look into other options to generate M
		BigInteger V = BigInteger.ONE ;
		
		BigInteger x = A ;
		BigInteger y = A.pow(2) ;
		y = y.subtract(BigInteger.TWO) ;
		y = y.mod(num) ;
		
		int bit = M.bitLength()-1 ;
	  while( bit > 0)
	  {
	    if( M.testBit(bit-1) )
	    {
	    	x = ((x.multiply(y)).subtract(A)).mod(num) ;
	    	y = ((y.multiply(y)).subtract(BigInteger.TWO)).mod(num) ;
	    	
	    }
	    else
	    {
	    	y = ((x.multiply(y)).subtract(A)).mod(num) ;
	    	x = ((x.multiply(x)).subtract(BigInteger.TWO)).mod(num) ;
	    	
	    }
       bit-- ;
	    
	  }
		
	  V = x ;
	  V = V.subtract(BigInteger.TWO) ;
	  return V.gcd(num) ;
	
	}
	
	
	public static void main(String[] args) {
		//115792089237316195423570985008687907853269984665640564039457584007913129639937
		String n = "115792089237316195423570985008687907853269984665640564039457584007913129639937" ; //test factoring 8051
		BigInteger num = new BigInteger(n) ;
		Pollard_Methods pollM = new Pollard_Methods() ;
		BigInteger resultfactor = null ;//pollM.Pollards_rho_algorithm(num) ;
		//System.out.println("factor = " + resultfactor.toString()) ;
		
		resultfactor = pollM.Pollards_p_minus_one_algorithm(new BigInteger("299"), 20) ;
		System.out.println("factor = " + resultfactor.toString()) ;
		
		resultfactor = pollM.William_P_plus_one_algorithm(new BigInteger("112729"));
		System.out.println("factor = " + resultfactor) ;
		
		
	}

}
