import java.util.Scanner;

public class ContinuedFractions {

	/**
	 * Euler Probably provide this!!!
	 * Periodic Continued fractions are in one-to-one correspondence with the real quadratic irrationals
	 * Aka numbers of the form (P+Q*sqrt(r))/M  where P ,Q are integers,
	 * r is a positive integer that not a perfect square ,
	 * M is any integer not equal to zero.
	 * 
	 * This function computes the continued fraction expansion of a given sqrt(r)
	 * For example sqrt(114) has a periodical continued fraction
	 * 
	 *  ContinuedFraction expansion for 114
		[ 10 , 1 , 2 , 10 , 2 , 1 , 0 , 20  ] 
        
        The length of the continued fraction expansion of sqrt(r) is bounded by 2r proven by gauss
        Meaning the period of the continued fraction expansion for sqrt(r) cannt exceed 2r


       Input must be a positive integer that not perfect square
	 * @param r
	 * @return
	 */
	
	public long[] computeExpansion( int r )
	{
		long cexpansion[] = new long[2*r] ;
		
		double m0 = 0 ;
		double d0 = 1 ;
		long a0 = (long)Math.sqrt(r) ;
		
		double mn = d0*a0 - m0 ;
		double dn = (r - Math.pow(mn, 2))/d0 ;
		long   an = (long)((a0 + mn)/dn)  ;
		
		cexpansion[0] = a0 ;
		int i = 1 ;
		
		while( an != 2*a0)
		{
			cexpansion[i] = an ;
			i++ ;
			
			mn = dn*an - mn ;
			dn = (r - Math.pow(mn, 2))/dn ;
			an = (long)((a0 + mn)/dn)  ;
			
		}
		
		cexpansion[++i] = 2*a0 ;
		
		//shortening the array size
		//just to save space
		long cout[] = new long[i+1] ;
		for( int k = 0 ; k < cout.length ; k++)
		cout[k] = cexpansion[k] ;
	
		
		return cout ;
		
	}
	
	//Program for allowing the user to output the continued fraction expansion
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ContinuedFractions cf = new ContinuedFractions() ;
		
		while( true )
		{
			
		
		System.out.println( "Input a number: type q (quit)" ) ;
	    Scanner sc=new Scanner(System.in);  
	    int r = 0 ;
	    try{
			r = sc.nextInt() ;
		}
		catch(RuntimeException ex)
		{
			System.exit(0) ;
		}
		long output[] = cf.computeExpansion( r ) ;
		
		System.out.println("ContinuedFraction expansion for " + r );
		System.out.print( "[ ") ;
		
		for( int i = 0 ; i < output.length ; i++ )
		{
			if( i != output.length - 1)
			System.out.print(output[i] + " , " ) ;
			else
			System.out.print(output[i] + " " ) ;
			
		}
		
		System.out.print( " ] ") ;
		System.out.println();
		
		}
		
		
	}

}
