
public class FactorBases {

	private int factorbasebound ;
	private int base[] ;
	private P b_smooth_num ;
	
	public P getB_SmoothNumFactors()
	{
		try {
			return (P)b_smooth_num.clone() ;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private void clearB_Smooth_NumFactors()
	{
		b_smooth_num.clear();
	}
	
	
	
	public int[] getBase()
	{
		return (int[])base.clone();
	}
	
	public int getFactorBaseBound()
	{
		return factorbasebound ;
	}
	
	public int getPrimeCountForBases()
	{
		return base.length ;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FactorBases fb = new FactorBases() ;
		fb.generateFactorBase(7);
		System.out.println( "B-SMOOTH " + fb.isB_Smooth(33600 ) ) ;
		System.exit(0) ;
		
	}

	
	public boolean isB_Smooth(int N)
	{
		clearB_Smooth_NumFactors() ;
		return computeB_Smooth(N) ;
		
		
		
		/*
		
		if( N == 1 )
			return true ;
		int n = N ;
		for( int i = 0 ; i < base.length ; i++ )
		{
			if( n % base[i] == 0 )
			{
				n = n / base[i] ;
				System.out.println(base[i]) ;
				b_smooth_num.addFactorElement(base[i]);
				return isB_Smooth(n) ;
			}
		}
			
//		for(int i = 0 ; i < base.length ; i++ )	
//		{
//			if( n == base[i])
//			return true ;
//		}
		
		return false ;
		
		*/
	}
	
	
	public boolean computeB_Smooth(int N)
	{
		if( N == 1 )
			return true ;
		int n = N ;
		for( int i = 0 ; i < base.length ; i++ )
		{
			if( n % base[i] == 0 )
			{
				n = n / base[i] ;
			//	System.out.print(base[i] + " ") ;
				b_smooth_num.addFactorElement(base[i]);
				return computeB_Smooth(n) ;
			}
		}
			
		
		return false ;
		
	}
	
	public void generateFactorBase(int B)
	{
		factorbasebound = B ;
		base = sieveOfEratosthenes(B);
		b_smooth_num = new P(base) ;
		return ;
		
	}
	
	/*
	 * The only function that i didnt code is this one
	 * This code has been contributed by Amit Khandelwal and was copied to use.
	 * I used it to generate factor bases which later i will improve on it and make it more general
	 * Just didnt want to waste time reinventing sieve of eratosthenes code
	 */
	
	private int[] sieveOfEratosthenes(int n)
	    {
	        // Create a boolean array
	        // "prime[0..n]" and
	        // initialize all entries
	        // it as true. A value in
	        // prime[i] will finally be
	        // false if i is Not a
	        // prime, else true.
	        boolean prime[] = new boolean[n + 1];
	        for (int i = 0; i <= n; i++)
	            prime[i] = true;
	 
	        for (int p = 2; p * p <= n; p++)
	        {
	            // If prime[p] is not changed, then it is a
	            // prime
	            if (prime[p] == true)
	            {
	                // Update all multiples of p
	                for (int i = p * p; i <= n; i += p)
	                    prime[i] = false;
	            }
	        }
	 
	        int pcount = 0 ;
	        // Print all prime numbers
	        for (int i = 2; i <= n; i++)
	        {
	            if (prime[i] == true)
	            {
	            	pcount++ ;
	  //          	System.out.print(i + " ");
	            	
	            }
	            
	            
	        }
	        
	   //     System.out.println("\nPrime count = " + pcount) ;
	        
	        int primes[] = new int[pcount] ;
	        int j = 0 ;
	        
	        for( int i = 2 ; i <= n ; i++ )
	        {
	        	if( prime[i] == true )
	        	{
	        		primes[j] = i ;
	        	    j++ ;
	        	}
	        }
	        
	        return primes ;
	        
	    }
	
	
	
}
