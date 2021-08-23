import java.math.BigInteger;

/**
 * 
 * @author nate
 * 
 *  This class is cool as it does factorization of numbers using elliptic curves.
 *  A very fast algorithm supposedly the 3rd fastest general purpose integer factorization method known
 *  coming after optimized quadratic sieve and general number sieve
 *  
 *  Note: the number sieves like quadratic ,dixons ,rational sieve , special number sieve and general number sieves
 *  are still works in programs i continuously try to improve on them but for now ECM should be enough for
 *  Most peoples needs :)
 *
 *  With this class i focus on elliptic curves of the form y^2=x^3 + ax + b because these are classic.
 *  However by adding another java class for different types of elliptic curve families and implement a different PointAddition method
 *  I can do any type of elliptic curve that one can think up.
 *  
 *  For all intents and purposes we focus on these elliptic curves over finite fields. As they work better for factoring integer numbers!
 *   
 *   
 *  The discoverer of this elliptic curve factorization method using finite fields was Lenstra's 
 *  and sometimes its called Lenstra's ECM = (Lenstra's elliptic curve method)
 * 
 * 
 *  How the algorithm works:
 *  Since where working with finite fields there is only finite amount of Point on the elliptic curve.
 *  Because of this there must be a k for which kP = O where (O is the point of infinity aka the group identity element )
 *  This means that between the point (k-1)P and kP is a vertical line connect them which implies the slope is infinite.
 *  Which in a finite field implies that the modinverse doesnt exist which means there exist a common factor between
 *  N and the element who's modinverse doesn't exist.
 *  Usually this factor is nontrivial factor of N but in rare case it can fail and give you 1 or N as a factor.
 *  In that case try a different point or a different elliptic curve until you reach a nontrivial factor or give up
 */


public class EllipticCurve implements PointAddition {

	//the family of elliptic curves used y^2=x^3 + ax + b	
	private BigInteger a ; //see equation above
	private BigInteger b ; //see equation above
	private BigInteger m ; //the slope of the line when doing elliptic curve point addition computed in $ function 
	private BigInteger N ; //the number your trying to factor 
	
	//Constructor used to create a particular elliptic curve of the form y^2=x^3 + ax + b over a given (mod NN)
	public EllipticCurve( BigInteger a , BigInteger b , BigInteger NN )
	{
		this.a = a ;
		this.b = b ;
		this.N = NN ;
	}
	
	//Used to set the N value 
	//Mostly used if one was just wanting to compute Points/group elements of the elliptic curve over a field N 
	//where N = Zmodp aka the integers mod a prime
	//But had no desire to factor a number using the ECM algorithm
	public void setN(BigInteger NN)
	{
		this.N = NN ;
	}
	
	//Used to get what N is as of current note if one calls ECM that will update N value to a different value.
	//Use the setN to set it back to what you want it to be and this method to check what it is currently
	public BigInteger getN()
	{
		return N ;
	}
	
	
	//Input StartP is a starting point on the elliptic curve
	//Input N is the number one wants to factor
	//Input stop is a max integer bound to check up before giving up
	//Output a BigInteger representing a nontrivial factor of N OR failure with a BigInteger.ONE
	public BigInteger ECM(Point StartP , BigInteger N , int stop)
	{
		this.N = N ;
		Point NextP = null;
		
		BigInteger factor = null ;
		
		try {
		NextP = $(StartP,StartP) ;
		}
		catch(CommonFactorException cex)
		{
			factor = cex.getVal() ;
			factor = factor.gcd(N) ;
			return factor ;
		}
		
		int n = 3 ;
		
		while( n <= stop )
		{   System.out.println(NextP) ;
			
		    try {
		    NextP = $$( NextP , n) ;
		    }
		    catch(CommonFactorException cex)
		    {
		    	factor = cex.getVal() ;
		    	return factor.gcd(N) ;
		    }
			
			n++ ;
		}
		
		return BigInteger.ONE ;
	}
	
	//Go back and improve the speed of doubling a point
	//this is slow should use a power of two process to make it quicker in computing of doubling a point
	//Given a Point P on the elliptic curve and an integer value n this computes the point nP = P + P + ... + P  (ntime)
	public Point $$( Point P , int n)
	{
		Point result = new Point( P.getX() , P.getY() ) ;
		
		if( n == 1)
			return result ;
		
		for( int i = 1 ; i < n; i++)
		result = $(result,P) ;
		
		return result ;
	}
	
	
	//Takes two points P and Q on the elliptic curve and computes the point P+Q
	public Point $(Point P , Point Q) throws CommonFactorException
	{
	
		
		BigInteger tempx2 = null ;
		BigInteger tempy2 = null ;
		BigInteger tempm = null ;
		
		if( P.equals(Q) == true )
		{
			BigInteger three = new BigInteger("3") ;
			BigInteger tempmbottom = null ;
			BigInteger y2bottom = null ;
			try {
				y2bottom = (BigInteger.TWO).multiply(P.getY()) ;
			    tempmbottom = y2bottom.modInverse(N) ;
			
			}
			catch(ArithmeticException aex)
			{
				CommonFactorException cex = new CommonFactorException("Point-Doubling-Type") ;
				cex.setVal(y2bottom);
				throw cex ;
			}
			tempm = ( ( P.getX().modPow(BigInteger.TWO, N) ).multiply( three ) ).mod(N) ;
			tempm = (tempm.add(a)).mod(N) ;
			m = (tempm.multiply(tempmbottom)).mod(N) ;
	
		}
		else
		{
		  m = ( (Q.getY()).subtract(P.getY()) ).mod(N) ;
		  tempm = ( (Q.getX()).subtract(P.getX()) ).mod(N) ;
		  try {
		  tempm = tempm.modInverse(N) ;	
		  }
		  catch( ArithmeticException aex)
		  {
				CommonFactorException cex = new CommonFactorException("Point-Addition-Type") ;
				cex.setVal(tempm);
				throw cex ;
		  }
		  m = m.multiply(tempm) ;
		}
		
		tempx2 = m.multiply(m) ;
		tempx2 = ( tempx2.subtract(P.getX()) ).mod(N) ;
		tempx2 = ( tempx2.subtract(Q.getX()) ).mod(N) ;
		
		tempy2 = m.multiply( ( P.getX().subtract( tempx2 ) ).mod(N)  ) ;
		tempy2 = tempy2.mod(N) ;
		tempy2 = tempy2.subtract(P.getY()) ;
		tempy2 = tempy2.mod(N) ;
		
		
		return new Point( tempx2 , tempy2 );
		
					
	}
	
	//Test main to test that all functions are working correctly iqnore this and use your own main
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BigInteger a = BigInteger.ONE ;
		BigInteger b = BigInteger.ONE ;
		BigInteger N = new BigInteger("1081") ;
		EllipticCurve ec = new EllipticCurve( a,b , N ) ;
		Point SP = new Point(BigInteger.ZERO,BigInteger.ONE) ;
		
	//	for( int i = 1 ; i <= 3 ; i++)
	//	{
	//	System.out.println(	ec.$$(SP, i) );
	//	}
		
		System.out.println( "factor = " + ec.ECM(SP, N, 10) ) ;
		
	}

}
