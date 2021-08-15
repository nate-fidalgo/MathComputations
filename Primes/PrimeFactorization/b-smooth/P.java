
/**
 * 
 * @author nate
 * Should not use this class direct it is internally used by FactorBases
 * To compute the factorization of B-smooth numbers
 * Call getB_SmoothNumFactors() of Class FactorBases to retrieve a copy of this class
 * 
 */


public final class P implements Cloneable{

	
	 public Object clone() throws CloneNotSupportedException {
	        return super.clone();
	    }
	
	private int e[] ;  //prime exponent for prime factorization
	private int p[] ;  //prime factor   for prime factorization
	
	public P( int factorbases[] )
	{
		p = factorbases.clone() ;
		e = new int[p.length] ;
		
	}
	
	public void addFactorElement(int f)
	{
		for( int i = 0 ; i < p.length ; i++ )
		{
			
			if(p[i] == f)
			{
				e[i]++ ;
				return ;
			}
				
		}
					
	}
	
	
	public int[] getE()
	{
		return e ;
	}
	
	public int[] getP()
	{
    	return p ;
	}
	
	//make computevalue() that computes the B-smooth number from its factorization
	//think about this possible
	
	
	public void clear() 
	{
		for( int i = 0 ; i < e.length ; i++)
		{
			e[i] = 0 ;			
		}
		
	}
	
	
}
