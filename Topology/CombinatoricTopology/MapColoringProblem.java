import java.math.BigInteger;

/**
 * 
 * This computes the min amount of colors need to color any map , graph ,...etc 
 * On a particular surface of a given genus right now it only works for orientable surfaces
 * The nonorientable surfaces are a work in progress 
 * 
 * It truely remarkable theorem now it use to be known as the Heawood conjecture
 * But was proven by being able to break it into equivalence class for genus
 * aka g = 12x+s so at this point solved over a few decades by these guys 
 * 
 *  1954, Ringel: case 5
	1961, Ringel: cases 3,7,10
    1963, Terry, Welch, Youngs: cases 0,4
	1964, Gustin, Youngs: case 1
	1965, Gustin: case 9
	1966, Youngs: case 6
	1967, Ringel, Youngs: cases 2,8,11
    The last seven sporadic exceptions were settled as follows:

    1967, Mayer: cases 18, 20, 23
    1968, Ringel, Youngs: cases 30, 35, 47, 59, and the conjecture was proved.
    
    Thank god for Ringel , Young ... for finding extreme cases to g = 12x+s
    without the ability to do that there would be no nice equation to do this calculation
    for general surface.
    
    And we all know how hard the single case for genus 0 aka four color theorem was 
    so very amazing all other case reduce so nicely to a close form equation 
 * 
 * 
 * @author nate
 *
 */


public class MapColoringProblem {

	//Computes euler characteristic give the genus and if the surface is orientatiable
	//Currently only works for orientable = true
	//false case is a work in progress!!!
	public BigInteger EulerCharacteristic(BigInteger genus , boolean orientable )
	{
		BigInteger ec = null ;
		
		if( orientable == true )
		ec = (BigInteger.TWO).subtract( (BigInteger.TWO).multiply(genus) ) ;
		else
		ec = (BigInteger.TWO).subtract( genus ) ; // this is not currently correct
		//still must implement away to compute nonorientatible euler characteristic x = 2 - k 
		
		return ec ;
		
		
	}
	
	//COMPUTES THE NUMBER OF COLORS FOR THE SURFACE SIMPLY AMAZING !!!
	//Compute the Heawood theorem
	//Returns the minimum amount of colors need to color on a surface of a given genus
	//Input genus of surface  , if its orientable or not
	//As a special case computeNumberOFCOLORS( '0' , true ) returns 4 which is equivalent to the 
	//4 COLOR THEOREM 
	public BigInteger computeNumberOFCOLORS( BigInteger genus , boolean orientable )
	{
		
		BigInteger colornum = null ;
		BigInteger ctemp = (new BigInteger("49")).add((new BigInteger("-24")).multiply(EulerCharacteristic(genus,orientable)) ) ;
		ctemp = ctemp.sqrt() ;
		ctemp = ctemp.add(new BigInteger("7")) ;
		colornum = ctemp.divide(BigInteger.TWO) ;
		
		return colornum ;
		
	}
	
	//Test main
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MapColoringProblem mp = new MapColoringProblem() ; 
		
		for( int i = 0 ; i < 50 ; i++ )
		{
		System.out.println( "euler characteristics for orientatible surface " + mp.EulerCharacteristic(new BigInteger(""+i) , true )  + " for genus " + i) ;
		}
		
		for( int i = 0 ; i < 50 ; i++ )
		{
		System.out.println( "Number of colors need to color this surface with genus " + i + " " + mp.computeNumberOFCOLORS( new BigInteger(""+i) , true  ) ) ;
		}
		
		
	}

}
