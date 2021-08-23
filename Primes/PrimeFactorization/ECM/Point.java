import java.math.BigInteger;


/**
 * 
 * @author nate
 *This is a Point class representing an Integer Point (x,y) it is not restricted to int data type sizes
 *It uses BigInteger to get nearly unlimited number sizes for the x and y coordinates if needed!!! 
 *
 */

public class Point {

	private BigInteger x;
	private BigInteger y;

	public Point( BigInteger x , BigInteger y )
	{
		this.x = x ;
		this.y = y ;
	}
	
	/*
	 * Getter and Setter methods for X and Y coordinates for this Point
	 * 
	 */
	
	public BigInteger getX()
	{
		return x ;
	}
	
	public BigInteger getY()
	{
		return y ;
	}
	
	public void setX(BigInteger x)
	{
		this.x = x ;
	}
	
	public void setY(BigInteger y)
	{
		this.y = y ;
	}
	
	
	//Test if the point Q is equal to this point
	public boolean equals( Point Q)
	{
		
		if( getX().compareTo( Q.getX() ) == 0 && getY().compareTo( Q.getY() ) == 0 )
		{
			return true ;
		}
		
		return false ;
		
	}
	
	//Display this point class in form (x,y)
	public String toString() 
	{
		
		return "( " + getX() + " , " + getY() + " )" ;
 		
	}
	
	
}
