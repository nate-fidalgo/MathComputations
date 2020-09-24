import java.util.Vector;

/**
 * This class is meant to represent a state in the game of peg solitaire. Moreover its used to keep track of what
 * move to try next in the backtracking algorithm of PegSolitaireSolver  
 * 
 * @author  Nate
 * @version 1.0
 * @since   2020-09-23 
 */
public class PegState {

	/** holds the previous moves already checked for this given PegState */
	private Vector<PegMove> visited  = null ; 
	/** holds the parent PegState for this PegState , null if root */
	private PegState parent = null ; 
	/** holds the current move being analyzed for this PegState */
	private PegMove currentmove = null ; 
	
	/**
	 * Constructor for PegState
	 * @param parent parent of this state, if parent == null its root level of state tree
	 */
	public PegState( PegState parent )
	{
		this.parent = parent ;
		visited = new Vector<PegMove>() ;
	}
	
	/**
	 * Get the parent state 
	 * @return the parent of this PegState if null is returned it means your at the root aka the beginning!
	 */
	public PegState getParentState()
	{
		return this.parent ;
	}
	
//	public void setCurrentMove( PegMove pm )
//	{
//		currentmove = pm ;
//	}
	
	/**
	 * Method to get the current move
	 * @return PegMove that this PegState is currently on
	 */
	public PegMove getCurrentMove()
	{
		return currentmove ;
	}
	
	/**
	 * Method that returns the nextMove to analyzes 
	 * @param pegbrd int[][] the peg board we are analyzing 
	 * @return PegMove the next move to try. If null is returned it means this PegState has no more moves left to try!
	 */
	public PegMove nextMove( int pegbrd[][] ) 
	{
		PegMove pm = new PegMove() ;
		
		for( int i = 0 ; i < pegbrd.length ; i++ )
			for( int j = 0 ; j < pegbrd[0].length ; j++ )
			{
				if( pegbrd[i][j] == 1 )
				{
					
					pm.fromX = i ;
					pm.fromY = j ;
					pm.toX = i+2;
					pm.toY = j ;
					if( isValidMove(pm , pegbrd ) == true &&  visited.contains( pm ) == false )
					{
						//if( currentmove != null )
						//visited.add( currentmove ) ;
						
						currentmove = pm ;
						visited.add(currentmove) ;
						
						return pm ;
					}
					
					
					pm.fromX = i ;
					pm.fromY = j ;
					pm.toX = i   ;
					pm.toY = j+2 ;
					if( isValidMove(pm , pegbrd ) == true && visited.contains( pm ) == false )
					{
						//if( currentmove != null )
						//visited.add( currentmove ) ;
						
						currentmove = pm ;
						visited.add(currentmove) ;
						return pm ;
					}					
					
					pm.fromX = i ;
					pm.fromY = j ;
					pm.toX = i-2;
					pm.toY = j ;
					if( isValidMove(pm , pegbrd ) == true && visited.contains( pm ) == false )
					{
					//	if( currentmove != null )
					//	visited.add( currentmove ) ;
						
						currentmove = pm ;
						visited.add(currentmove) ;
						return pm ;
					}
					
					pm.fromX = i ;
					pm.fromY = j ;
					pm.toX = i   ;
					pm.toY = j-2 ;
					if( isValidMove(pm , pegbrd ) == true && visited.contains( pm ) == false )
					{
						//if( currentmove != null )
						//visited.add( currentmove ) ;
						
						currentmove = pm ;
						visited.add(currentmove) ;
						return pm ;
					}					
					
				}
				
			}

		//if gotten here means you have no more moves for this PegState to explore
		return null ;
	}
	
//	public void setVisited(PegMove p)
//	{
//		visited.add(p) ;
		
//	}
	/**
	 * Method that check to see if the given move is a valid move
	 * @param pm PegMove the move to check if possible or impossible to do
	 * @param pgbrd int[][] the peg solitaire board
	 * @return boolean true if the move is valid/possible, false if move is invalid/impossible 
	 */
	private static boolean isValidMove( PegMove pm , int pgbrd[][] )
	{
		int brdval = -1 ;
		
		try {
		brdval = pgbrd[pm.fromX][pm.fromY] ;
		brdval = pgbrd[pm.toX][pm.toY] ;
		if( brdval != 0 )
			return false ;
		
		if(  Math.abs( pm.fromX - pm.toX ) != 2 &&  Math.abs( pm.fromY - pm.toY ) != 2 )
			return false ;
		
		if(  Math.abs( pm.fromX - pm.toX ) != 0 &&  Math.abs( pm.fromY - pm.toY ) != 0 )
			return false ;
		
		
		if( (pm.fromX - pm.toX) == 0 && ( pm.fromY > pm.toY ) )
				if( pgbrd[pm.toX][pm.fromY-1] != 1 )
					return false ;
		
		if( (pm.fromX - pm.toX) == 0 && ( pm.fromY < pm.toY ) )
			if( pgbrd[pm.toX][pm.fromY+1] != 1 )
				return false ;
		
		if( (pm.fromY - pm.toY) == 0 && ( pm.fromX < pm.toX ) )
		    if( pgbrd[pm.fromX+1][pm.toY] != 1 )
		    	return false ;
		
		if( (pm.fromY - pm.toY) == 0 && ( pm.fromX > pm.toX ) )
		    if( pgbrd[pm.fromX-1][pm.toY] != 1 )
		    	return false ;
		
		
		}
		catch( Exception e)
		{
			return false ;
		}
		return true ;
		
	}
	
}
