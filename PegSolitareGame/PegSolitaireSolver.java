import java.util.Stack;

/**
 * This <B>PegSolitaireSolver program</B> is a program to solve peg solitaire board games. 
 * Uses DFS = depth for search for the backtracking and a backward pruning technique to only hold needed game states as it goes.
 * @author Nate
 * @version 1.0
 * @since   2020-09-23
 */
public final class PegSolitaireSolver {

	/**
	 * Add your specific peg board to analysis / solve
	 * 0 = a empty peg hole, 1 = a peg , 2 = a wall to shape the board 
	 */
	private static int pegbrd[][] = {
	{2,2,1,2,2},
	{2,1,1,2,2},
	{2,0,0,1,2},
	{2,0,0,0,2},
	{2,1,0,0,2}
	} ;
	
	/**
	 * <pre>
	 * main method entry point into the PegSolitaireSolver program where the backtracking algorithm begins 
	 * for solving the peg solitaire game 
	 * </pre>
	 * @param args the file name to be read from for the pegboard currently not used not need yet so.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    
	Stack<PegState> pgstates = new Stack<PegState>() ; // Stores previous states to backtrack on
	int pegcount = getPegCount( pegbrd ) ; //counts how many pegs on board
	PegState pstate = new PegState(null) ;	//root of the search tree
	PegMove pm = pstate.nextMove(pegbrd) ;
	if( pm == null ) 
	{
		System.out.println( "NO SOLUTION") ;
	    System.exit(0) ;
	}
	
	doMove( pm , pegbrd ) ;
	pegcount-- ;
	pgstates.push(pstate) ;
	
	boolean endmovereached = false ;
	
	while( pgstates.empty() != true )
	{
		
		if( endmovereached == false )
		{
			pstate = new PegState( pstate ) ;
		}
		pm = pstate.nextMove(pegbrd) ;
		if( pm != null )
		endmovereached = false ;
			
		if( pm == null && ( solvedboard( pegbrd) != true || pegcount != 1 ) )
		{
			//undo the move and go back to the previous state
			pstate = pgstates.pop();
			pm = pstate.getCurrentMove() ;
			undoMove( pm , pegbrd ) ;
			pegcount++ ; 
			
			endmovereached = true ;
						
		}
		else
		if( pegcount == 1 && solvedboard( pegbrd) == true )
		{
			PegState tmp = pstate ;  //currently not used but is need if later we want to not print one solution
			//but all solutions of the peg game.
			StringBuffer solutionmoves = new StringBuffer() ; //Is used to convert the the backward stack moves into
			//what the moves you would make from start to finish not finish to start because of the stack FILO structure
			while( pstate != null )
			{
				if( pstate.getCurrentMove() != null )
					solutionmoves.insert( 0 , pstate.getCurrentMove() ) ;
				solutionmoves.insert(0 , " | ") ;
				
				//print( pstate.getCurrentMove() ) ; //comment in to print the stack of moves its better to uses
				//StringBuffer solutionmoves as it prints the moves in order of executing them not backwards order
				pstate = pstate.getParentState() ;
							
			}
			System.out.println("FOUND SOLUTION!!!") ;
			//either exist program because solution was found
			//OR put in logic to continue to the next solution
			//currently we just call System.exit once a solution is found 
			//later i may not stop here but just have the program go thru the entire tree and print all the
			//possible solutions 
			System.out.println( solutionmoves.toString().trim() ) ;
			System.exit(0);
		}
		
		if( endmovereached == false )
		{
			doMove( pm , pegbrd ) ;
			pegcount-- ;
			pgstates.push(pstate) ;
		}

		
		
		
	}
	
	System.out.println("NO SOLUTIONS FOUND!!!") ;
	System.exit(0) ;
		
	}
	
	/**
	 * Method to print a peg move out to the standard output aka the console most likely 
	 * @param pm The PegMove to print
	 */
	private static void print( PegMove pm)
	{
		if( pm != null )
		System.out.println( pm ) ;
	}
	
	/**
	 * Method is used to do the undo a move on the pegbrd
	 * @param pm PegMove the move to undo on the pegbrd
	 * @param pegbrd  int[][] the peg board that the undo move will update on
	 */
	private static void undoMove( PegMove pm , int pegbrd[][] )
	{
		pegbrd[pm.fromX][pm.fromY] = 1 ;
		pegbrd[pm.toX][pm.toY] = 0 ;
		
		if( (pm.fromX - pm.toX) == 0 && ( pm.fromY > pm.toY ) )
			pegbrd[pm.toX][pm.fromY-1] = 1 ; 
		else
	    if( (pm.fromX - pm.toX) == 0 && ( pm.fromY < pm.toY ) )
		    pegbrd[pm.toX][pm.fromY+1] = 1 ; 
	    else
	    if( (pm.fromY - pm.toY) == 0 && ( pm.fromX < pm.toX ) )
	        pegbrd[pm.fromX+1][pm.toY] = 1 ; 
	    else	
	    if( (pm.fromY - pm.toY) == 0 && ( pm.fromX > pm.toX ) )
	        pegbrd[pm.fromX-1][pm.toY] = 1 ; 
		
		return ;
		
	}
	
	/**
	 * Method is used to do the move out on the pegbrd
	 * @param pm PegMove the move to do on the pegbrd
	 * @param pegbrd  int[][] the peg board that the move will update on
	 */
	private static void doMove( PegMove pm , int pegbrd[][] )
	{
		
		pegbrd[pm.fromX][pm.fromY] = 0 ;
		pegbrd[pm.toX][pm.toY] = 1 ;
		
		if( (pm.fromX - pm.toX) == 0 && ( pm.fromY > pm.toY ) )
			pegbrd[pm.toX][pm.fromY-1] = 0 ; 
		else
	    if( (pm.fromX - pm.toX) == 0 && ( pm.fromY < pm.toY ) )
		    pegbrd[pm.toX][pm.fromY+1] = 0 ; 
	    else
	    if( (pm.fromY - pm.toY) == 0 && ( pm.fromX < pm.toX ) )
	        pegbrd[pm.fromX+1][pm.toY] = 0 ; 
	    else	
	    if( (pm.fromY - pm.toY) == 0 && ( pm.fromX > pm.toX ) )
	        pegbrd[pm.fromX-1][pm.toY] = 0 ; 
		
		return  ;
		
	}
	
	/**
	 * 
	 * @param pegbrd the peg board
	 * @return <pre>true for now but can be customized with more specific requirements. Like the last peg has to be in
	 * the middle peg hole for it to be a solution... currently a solution to peg solitaire is any move sequence that ends with only one peg on the 
	 * board. So this method is just a stub function that always returns true add your code to it to customize requirements / constraints for a board to be solved.
	 * RIGHT NOW ITS NOT USED AS A SOLUTION IS REACH WHEN ONLY ONE PEG IS LEFT DOESNT MATTER WHERE IT IS LEFT.
	 * YOU MAY FEEL DIFFERENTLY ABOUT THIS AND WANT TO CUSTOMIZE IT TO A SPECIFIC PLACE ONBOARD OR OTHER REQUIREMENTS!</pre>
	 */
	private static boolean solvedboard( int pegbrd[][] )
	{
	   return true ;
		
	}
	
	/**
	 * Method getPegCount() probably obvious what it does
	 * @param pegbrd the peg board to count its pegs, { 1 = peg , 0 = empty space , 2 = wall/restricted area for peg }
	 * @return the peg count of how many pegs are on the int pegbrd[][] , basically counts the 1's in pegbrd
	 */
	private static int getPegCount( int pegbrd[][] )
	{
		int cnt = 0 ;
		for( int i = 0 ; i < pegbrd.length ; i++ )
		   for( int j = 0 ; j < pegbrd[0].length ; j++ )
			   if( pegbrd[i][j] == 1 )
				   cnt++ ;
			   
		return cnt ;
			   
	}
	
	

}
