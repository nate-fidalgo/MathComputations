
import java.util.Stack ;

public class Backtrack  {

static final int bsize = 16 ;

public static void main(String args[])
{

int board[][] = new int[bsize][bsize] ;
for( int i = 0 ; i < bsize ; i++ ) 
  for( int j = 0 ; j < bsize ; j++ )
      board[i][j] = -1 ;

board[0][0] = 4 ;
BoardState bstate = new BoardState( board , 0 , 0 ) ;

LatinObj lobj = new LatinObj( null , bstate ) ;
int r = 0 ;
int c = 0 ;
boolean doaddchildren = true ;
int solutioncount = 0 ;
Stack stk = new Stack<LatinObj>() ;

while( true ) //(r+1 < bsize) || (c+1 < bsize) )
{


bstate = (BoardState)lobj.getValue() ;
r = bstate.getRow() ;
c = bstate.getCol() ;

doaddchildren = lobj.isLeaf() ;

if( doaddchildren == true && lobj.getVisited() == false )
{
	
if( r+1 < bsize )
{

board = bstate.getBoard() ;

for( int k = 1 ; k < (bsize+1) ; k++ )
{
board[r+1][c] = k ;
if( isValidBoard( board , r+1,c ) == true )
{lobj.addChild( new LatinObj( null , new BoardState(board,r+1,c) ) ) ;

//System.out.println( "doing r+1 < bsize" + k + " children " + lobj.getChildCount() );
}
} 


}
else
if( c+1 < bsize )
{
   r = 0;
   board = bstate.getBoard() ;
   for( int k = 1 ; k < (bsize+1) ; k++ )
   {
   board[r][c+1] = k ;
   if( isValidBoard( board , r, c+1 ) == true )
   {lobj.addChild( new LatinObj( null , new BoardState(board,r,c+1) ) ) ;
  // System.out.println( "doing c+1 < bsize " +k);
   }
   }
  
}
else
{
//your at a solution
//print solution , add it to tree , you decide
//System.out.println( "Both c+1 and r+1 is >");
lobj.printTreeObj();
lobj.setVisited(true);
solutioncount++ ;
System.out.println( solutioncount ) ;
//lobj.setifValidObject( false ) ;
//lobj = (LatinObj)lobj.getParent();
//lobj.trashInvalidObject() ;
return;
}

}

if( lobj.isLeaf() == false  )
{
	//lobj = (LatinObj)lobj.getChildAt(1) ;
	boolean hasvalidchildrenleft = false ;
	for( int i = 0 ; i < lobj.getChildCount() ; i++ )
	 if(lobj.getChildAt(i).getVisited() != true )
	 {
		 hasvalidchildrenleft = true ;
		 stk.push(lobj) ;
		 lobj = (LatinObj)lobj.getChildAt(i) ;
		 
	 }
	
	if( hasvalidchildrenleft == false)
	{
	   lobj.setVisited(true);
	   if(stk.isEmpty() == true)
		   return;
	   
	   lobj = (LatinObj)stk.pop();
	}
	//lobj.getChildAt(childIndex)
	//get next child and iterate over while loop
	//DFS
	//push on stack
	//lobj.printTreeObj();
}
else if( lobj.isLeaf() == true )
{
	//lobj.setifValidObject(false);
	lobj.setVisited(true);
	if(stk.isEmpty() == true)
		   return;
	lobj = (LatinObj)stk.pop();
	
}



//in either case if you reach the root node with no good options left to traverse then your done!

	
/*
//if( lobj.isLeaf() == false )
if( lobj.isLeaf() == true && lobj.getParent() != null)
{
	if( ((BoardState)(lobj.getValue())).getRow() != (bsize -1) && ((BoardState)(lobj.getValue())).getCol() != (bsize -1)  )
    {
		lobj.setifValidObject(false);	
        lobj.setVisited(true);
    }
	else
	{
		 lobj.setVisited(true);
	}
	
    lobj = (LatinObj)lobj.getParent() ;
    
}
else
{ //lobj.printTreeObj() ;
	int ccount = lobj.getChildCount() ;
	boolean foundchild = false ;
	for( int i = 0 ; i < ccount ; i++ )
	  if( (lobj.getChildAt(i)).isValidObject() == true && (lobj.getChildAt(i)).getVisited() == false )
	  {
		  lobj = (LatinObj)lobj.getChildAt(i) ;
		  foundchild = true ;
	      break ;
	  }
	  
	if( foundchild == false && lobj.getParent() == null )
	return ;
	
	if( foundchild == false && lobj.getParent() != null )
	{
		lobj.setVisited(true);
		lobj.setifValidObject(false);
		lobj = (LatinObj)lobj.getParent() ;
        
	}
	
		
}*/

}




}

public static boolean isValidBoard( int board[][] , int r , int c ) 
{

for( int i = 0 ; i < board.length ; i++ )
if( board[r][c] == board[i][c] && i != r)
return false ;

for( int i = 0 ; i < board.length ; i++ )
if( board[r][c] == board[r][i] && i != c)
return false ;

return true ;

}



}