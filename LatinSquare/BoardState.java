

public class BoardState {

private int bd[][] = null ;
private int row ;
private int col ;
private int bdsize = 0 ;

public BoardState( int brd[][] , int row , int col )
{
//this.bd = bd ;
bd = new int[brd.length][brd.length] ;
for( int i = 0 ; i < brd.length ;i++ )
for( int j = 0 ; j < brd.length ;j++ )
	bd[i][j] = brd[i][j] ;
	
this.row = row ;
this.col = col ;
this.bdsize = brd.length ;
}

public BoardState()
{

}

public int getRow()
{
return row ;
}

public int getCol()
{
return col ;
}

public int[][] getBoard()
{
return bd ;
}

public int getBoardSize()
{
return bdsize ;
}

}