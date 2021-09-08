import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Scanner;

public class DiscreteLog {

	
	//Baby-step giant step algorithm for trying to compute discrete log problem
	//It tries to compute x in the expression  a^x = b (mod n)
	//Runtime is about O(sqrt(n)) slightly better then the brute force O(n) trial and error algorithm
	public BigInteger computeDiscreteLog( BigInteger a , BigInteger b , BigInteger n )
	{
		
		BigInteger m = n.sqrt() ; 
		Hashtable<BigInteger, BigInteger> lookup = new Hashtable<BigInteger, BigInteger>();
		BigInteger x = a.add(BigInteger.ZERO) ; 
		
		for( BigInteger j = BigInteger.ZERO ; j.compareTo(m) <= 0 ; j = j.add(BigInteger.ONE) )
		{
			//x = x.modPow(j, n) ;		
			lookup.put(x.modPow(j, n),j) ;
		}
		
		BigInteger a_inverse_m = null ;
		try {
		a_inverse_m= a.modInverse(n) ;
		}
		catch(RuntimeException e)
		{
			return null ;
		}
		a_inverse_m = a_inverse_m.modPow(m, n) ;
		BigInteger y = b.add(BigInteger.ZERO) ; 
		BigInteger j = null ;
		
		for( BigInteger i = BigInteger.ZERO ; i.compareTo(m) <= 0 ; i = i.add(BigInteger.ONE) )
		{
			if( lookup.containsKey(y)  == true )
			{
				j = lookup.get(y) ;
				return (i.multiply(m)).add(j) ;
			}
			y = y.multiply(a_inverse_m) ;
			
		}
		
		return null;
		
		
	}
	
	
	
	//Pohlig–Hellman algorithm coming some but i felt i hold off on this one.
	// Seeing as it requires the factorization of n to be know and this make it impractical alot of times to use.
	//As it has to be a n that is smooth enough in some practical factor bases or known before time.
	//It worst case runtime is about as fast as baby steps giant steps algorithm above. This occurs
	//when the n = a prime power.
	
	//Though the runtime in average case is far superior (however i repeat it depends on knowing the factorization of n to begin with)
    /*
     * Code for Pohlig–Hellman algorithm coming soon!!!
     * 
     */
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DiscreteLog dl = new DiscreteLog() ;
		
		Scanner in = new Scanner(System.in);
		System.out.println("Solving a^x = b (mod n)") ;
		
        System.out.print("Enter a value = " );
        String s1 = in.nextLine();
        System.out.print("Enter b value = " );
        String s2 = in.nextLine();
        System.out.print("Enter n value = " );
        String s3 = in.nextLine();
        
		BigInteger a = new BigInteger(s1) ;
		BigInteger b = new BigInteger(s2) ;
		BigInteger n = new BigInteger(s3) ;
		System.out.println("Solving " + a + "^x = " + b + "(mod " + n + ")" ) ;
		BigInteger result = dl.computeDiscreteLog( a , b , n ) ;
		System.out.println( "Solution to DiscreteLog = " + (result == null ? "no solution" : result) ) ;
		
		
		
	}

}
