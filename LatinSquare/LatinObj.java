



public class LatinObj extends TreeObj {


public LatinObj( LatinObj pobj , BoardState bd )
{
super( pobj , bd ) ;

}

public  void printTreeObj()
{
int brdsz = ((BoardState)value).getBoardSize() ;
int board[][] = ((BoardState)value).getBoard() ;

for( int i = 0 ; i < brdsz ; i++ )
 { 

 //if( (i+1) % brdsz == 0 )
 System.out.println() ;
 for( int j = 0 ; j < brdsz ; j++ )
 System.out.print( board[i][j] + " " ) ; 
    
 }

System.out.println() ;

}




}