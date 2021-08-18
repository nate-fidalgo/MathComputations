import java.math.BigInteger;
//Using package so that its not limited to overflows issues like
//c/c++ example above

//This class began as just focused on Pollard p-1 , williams p+1 , and rho algorithms.
//But has been extended to include the CFRAC /Shank algorithm as well
//I am placing elliptic curve factorization ECM and the top number sieve methods (dixons ,quadratic,rational, special , general)
//In a different class file and is still a improving work in progress...

//Note though these algorithms speed is fundamentally tied to the performance of the java.math package
//Until i get around to coding up a better arbitrary precision library i sort of had to rely on one arbitrary precision library
//So the speed of these algorithms is the optimal proven speed of theoretical algorithm or slower because of arbitrary precision library performance


public class Pollard_Methods {

	
	//Input num a number to find a nontrival factor for
	//Output a nontrivial factor or BigInteger.ONE failed
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
	//Helper function because the Math api only give base 10 or e functions
	//No problem with a little math we all know aka the base changing formula for logs :)
	private double customLog(double base, double logNumber) {
	    return Math.log(logNumber) / Math.log(base);
	}
	
	//input num the number to find a factor for
	//input attempts the number of times to try the p-1 algorithm
	//Output nontrivial factor or BigInteger.ONE if failed
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
	
	
	
	//input B a factor bases bound
	//input num the number to find a factor for
	//input randompick this is not yet implemented but is a boolean that will indicate to randomly generate an a value not just use a = 2
	//output the nontrivial factor or failed with 1 or num returned
	public BigInteger Pollards_p_minus_1_algorithm(int B , BigInteger num , boolean randompick )
	{
		BigInteger factor = BigInteger.TWO ;
		FactorBases fb = new FactorBases() ;
		fb.generateFactorBase(B);
		int pbase[] = fb.getBase() ;
		BigInteger M = generateM(pbase , B ) ;
		
		//factor = pow(BigInteger.TWO,M) ; //a=2 later add random coprimes selection for a
		//As of current it takes easy way out and just selects a = 2 because all odd numbers are coprime to 2 
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
	
	//input a composite number
	//output a nontrivial factor of it or keeps searching forever
	//Note: if using this function you have to manually stop it if it doesnt find a factor 
	//in a reasonable amount of time determined by you/the user.
	//Also note BigInteger a = new BigInteger("5") ; so a = 5
	//You can change this value to any random value > 2 that way you get different Lucas sequence to play with
	public BigInteger  William_P_plus_one_algorithm(BigInteger num )
	{
		BigInteger a = new BigInteger("5") ; // this can be changed to any value > 2 
		BigInteger m = BigInteger.ONE ;
		BigInteger m_plus_1 = BigInteger.ONE ;
		BigInteger result = null ;
		
		while(true)
		{
		m = m.multiply( m_plus_1 ) ;
		result = William_P_plus_1_algorithm(num , m , a) ;
		if( result.compareTo(BigInteger.ONE) != 0 && result.compareTo(num) != 0)
		return result ;
			
		m_plus_1 = m_plus_1.add(BigInteger.ONE);
		
		}
		
	}
	
	
	public BigInteger William_P_plus_1_algorithm(BigInteger num , BigInteger m , BigInteger a)
	{
		BigInteger A = a ; //new BigInteger("5") ;    //A is choosen greater then 2 at random to characterize the Lucas sequence still curious if educate choices matter in some way
		BigInteger M = m ; //new BigInteger("5040") ; //values used for M are successive factorials but possiblely look into other options to generate M
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
	
	//Simple Test main to test that all functions are working as they should
	//Essentially used for bug testing programmers should not use directly they should create
	//There own main 
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
		
		resultfactor = pollM.CFRAC_SHANK(new BigInteger("11111") , BigInteger.ONE) ;
		System.out.println("factor cfrac_shank = " + resultfactor ) ;
		
	}
	
	
	//input N a composite number that is not a perfect square
	//input k a trail number for  sqrt( k*N ) 
	//output a nontrivial factor of N or failed for that given k
	public BigInteger CFRAC_SHANK(BigInteger N , BigInteger k )
	{
		
		BigInteger P_0 =  (N.multiply(k)).sqrt() ;
		BigInteger Q_0 = BigInteger.ONE ;
		BigInteger Q_1 = ( k.multiply(N) ).subtract( P_0.pow(2) ) ;
		BigInteger Ptemp = P_0 ;
		BigInteger Qtemp = Q_1 ;
		BigInteger b = null ;
		BigInteger Ptemp_next = null ;
		BigInteger Qtemp_next = null ;
		BigInteger Qtemp_prev = Q_0 ;
		
		do
		{
			b = (P_0.add(Ptemp )).divide(Qtemp) ;
	        Ptemp_next = (b.multiply(Qtemp)).subtract(Ptemp) ;
			Qtemp_next = Qtemp_prev.add(b.multiply(Ptemp.subtract(Ptemp_next))) ;
		    
			Qtemp_prev = Qtemp ;
			Qtemp = Qtemp_next ;
			Ptemp = Ptemp_next ;
		}
		while( (Qtemp_next.sqrtAndRemainder()[1]).compareTo(BigInteger.ZERO) != 0 ) ;
	

		
		b = P_0.subtract(Ptemp) ;
		b = b.divide( Qtemp_next.sqrt() ) ;
		P_0 = b.multiply( Qtemp_next.sqrt() ) ;
		P_0 = P_0.add(Ptemp_next) ;
		Q_0 = Qtemp_next.sqrt() ;
		Q_1 = (k.multiply(N)).subtract( P_0.pow(2) ) ;
		Q_1 = Q_1.divide(Q_0) ;
		
		BigInteger P_hit = BigInteger.ONE ;
		
		do {
			
			b = (P_0.add(Ptemp )).divide(Qtemp) ;
	        Ptemp_next = (b.multiply(Qtemp)).subtract(Ptemp) ;
			Qtemp_next = Qtemp_prev.add(b.multiply(Ptemp.subtract(Ptemp_next))) ;
		    
			Qtemp_prev = Qtemp ;
			Qtemp = Qtemp_next ;
			P_hit = Ptemp ;
			Ptemp = Ptemp_next ;	
			
		}		
		while( P_hit.compareTo( Ptemp_next ) != 0 ) ;

		
		
		return P_hit.gcd(N) ;
		

	}
	
	

}
