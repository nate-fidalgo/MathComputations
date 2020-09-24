/**
 * This class is meant to represent a peg move in the game of peg solitaire. 
 * Used in PegState class 
 * @author  Nate
 * @version 1.0
 * @since   2020-09-23
 */
public class PegMove {

	/** x coordinate of the peg you want to move  */
	public int fromX ;
	/** y coordinate of the peg you want to move  */
	public int fromY ;
	/** x coordinate of where you want to move the peg to  */
	public int toX   ;
	/** y coordinate of where you want to move the peg to  */
	public int toY   ;
	
	
/**
 * Method to display the move in a nice format of (x1,y1)-->(x2,y2)
 * Used in printing out a peg move to the console or writing solutions to a file
 * @return String representation of the PegMove
 */
public String toString()
{
	return "(" + fromX + "," + fromY + ")-->(" + toX + "," + toY + ")" ;  
}
	
/**
 * <pre>
 * This is an overload function from Object class used to make Vector's contain method work appropriately in the context 
 * of PegMove comparing very important to have for nextMove(...) to work properly
 * </pre>
 * @param  obj
 * @return true if obj equals this PegMove false otherwise
 */
public boolean equals(Object obj)
{
	if( obj instanceof PegMove )
	{
		PegMove pobj = (PegMove)obj ;
		if( (pobj.fromX == this.fromX) && (pobj.fromY == this.fromY) && (pobj.toX == this.toX) && (pobj.toY == this.toY ) )
		{
			return true ;
		}
			
	}
	return false ;
}

	
}
