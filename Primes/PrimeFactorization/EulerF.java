import java.math.BigInteger;
public class EulerF {

	/*
	 * Note Important Euler factorization can only be used to factor numbers that are not of the form 4k+3 
	 * Because as we discussed about prime that are the sum of two square being of the form 4k+1 dont exist so you will never beable to find representation a^2+b^2 relations!!!
	 * This and the fact that finding two representation of a number as the sum of two square can be difficult 
	 * It is a factorization algorithm of old days wouldnt stand up to compete with general number sieve , rational number sieve , special number sieve , or quadratic number sieve
	 * 
	 * But the only algorithms around this time was Fermats , Eulers , trial divisions/brute force , and Sieve of Eratosthenes
	 * 
	 * And Sieve of Eratosthenes is more a prime generating table method then a means given a number to compute its factors so i debate on if its even should be consider as 
	 * A prime factorization method more then a prime generating method
	 * 
	 * Sure there was prime test around like Lucas test and Fermat little probable prime test PPR but these where prime testing algorithms not prime factorization algorithms
	 * So above is all you had until the realization that one can improve on Fermats square difference to yield Dixon's factorization method 
	 * After that a plethora of new and improved algorithms came out like... general number sieve , rational number sieve , special number sieve , or quadratic number sieve
	 * But again these new and improved algorithms where only first conceived in 1981 onward 
	 * 
	 * The top 3 fastest factorization algorithms know as of current is 
	 * 1) numbers greater then 10^100 General Number Sieve essentially uses polynomials of a number fields to find factor bases that quickly has a good chance at find one factor
	 * 2) quadratic number sieve any number on the order of 100 digit along or less this method is still the best for those numbers to fact that are less than or equal to hundred digits
	 * 3) Eliptical Curve factorization algorithm slightly less good on average then General Number Sieve and Quadratic Number Sieve
	 * 
	 * I am re-implementing all the prime algorithms and prime factorization algorithms trying to improve on them the key to most of them at these high levels is to be able to generate a good factor base of B-smooth numbers
	 * And find many relation that possible yield a good x^2=y^2 mod N relation which then can be used like gcd(x-y,N) to hopefully give a nontrival factor for N.
	 * The runtimes are a Bitch to compute but i finally figured out how the runtime of these top algorithms are computer there subexp but there runtime is still harder then P
	 * And i dont think  it will every be in Class P but then again AKS prime test for  determining if a number is a prime was thought for many years to not be in P class.
	 * 
	 * But Primes in P was done in 2002 so you can verify if a number is prime in P
	 * But It still Stands can you Factor a Number in P ?????
	 * 
	 */
	
	public static void EulerFactor( BigInteger N , BigInteger a ,  BigInteger b ,  BigInteger c ,  BigInteger d )
	{
		BigInteger l1 = (a.subtract(c)).abs() ;
		BigInteger l2 = (d.add(b)).abs() ;
		
		BigInteger m1 = (a.add(c)).abs() ;
		BigInteger m2 = (d.subtract(b)).abs() ;
		
        BigInteger L = l1.gcd(l2) ;
		BigInteger M = m1.gcd(m2) ;
		
		BigInteger k = l1.gcd(m2) ;
		BigInteger h = m1.gcd(l2) ;
		
		if( k.mod( new BigInteger("2") ).equals( BigInteger.ZERO ) == true ) 
		{
			
		 k = k.divide( new BigInteger("2") ) ;	
		 k = k.pow(2) ;
		 
		 h = h.divide( new BigInteger("2") ) ;	
		 h = h.pow(2) ;
		 
		 BigInteger factor1 = k.add(h) ;
		 BigInteger factor2 = N.divide(factor1) ;
		 System.out.println( "The factors of " + N.toString() + " = " + factor1 + " x "  + factor2 ) ;
		 return ;
		 
		}
		
		 
		 k = k.pow(2) ;
		 h = h.pow(2) ;
		 BigInteger factor1 = k.add(h) ;
		 BigInteger factor2 = N.divide(factor1) ;		 
		 System.out.println( "The factors of " + N.toString() + " = " + factor1 + " x "  + factor2 ) ;
         return ;
		
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

    // Euler Factorization Algorithm the assumption is that you know the two representation of the number
    // As the sum of two squares aka
    // N = a^2 + b^2 = c^2 + d^2   
	//This implies with a little math manipulation you get 
    // N = ( (k/2)^2 + (h/2)^2 )(l^2+m^2)
    // where k = gcd( a -c , a +c )
    //       h = gcd( a +c , d +b )
	//       m = gcd( a +c , d -b )
	//       l = gcd( a -c , d +b )
	// (k,h) or (l,m) pairs one of them has to be even so k/2 , h /2 is a valid integer if (l,m) was even just interchange (k,h) pair with (m,l) pair and visa-versa
	EulerFactor( new  BigInteger("1000009") , new BigInteger("1000") ,  new BigInteger("3") ,  new BigInteger("972") ,  new BigInteger("235") ) ;
	System.exit(0);	
	}

}
