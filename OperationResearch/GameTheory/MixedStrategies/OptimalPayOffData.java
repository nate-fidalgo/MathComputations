import gamestrategyutils.Matrix_String;

public class OptimalPayOffData {

	
	private String gamevalue ;
    private String inverseB[][]  ;
	private int deletedRows[] = null  ;
	private int deletedCols[] = null  ;
	private String XStrategy[][] = null ; //X is row player
	private String YStrategy[][] = null ; //Y is column player 
	private String XStrategyMainGame[][] ;//X is row player
	private String YStrategyMainGame[][] ;//Y is column player
	
	public OptimalPayOffData( String expectedvalue , String inversesubmatrix[][] , int drows[] ,int dcols[] )
	{
		gamevalue = expectedvalue ;
		inverseB = inversesubmatrix.clone() ;
		if( drows != null )
		deletedRows = drows.clone() ;
		if( dcols != null )
		deletedCols = dcols.clone() ;
		
		String invB[][] = this.getinverseB() ;
		String J[][] = new String[1][invB.length]  ;
		Matrix_String.fill(J, "1");
		XStrategy = Matrix_String.multiplyMatrix(J, invB) ;
		String tinvB[][] = Matrix_String.transpose(invB) ;
		YStrategy = Matrix_String.multiplyMatrix(J, tinvB) ;
		
	//	YStrategy = Matrix_String.scalarmult(YStrategy , gamevalue) ;
	//	XStrategy = Matrix_String.scalarmult(XStrategy , gamevalue) ;
		
		computeMainGameStrategies() ; //IMPORTANT CODE THIS METHOD UP
				
		
	}
	
	//Helper function for getting the rows or columns for the MainMatrix
	private int[] keepedElements( int e[] )
	{
		
		int result[] = new int[inverseB.length+e.length] ;
		int places[] = new int[inverseB.length] ;
		for( int i = 0 ; i < e.length ; i++ )
		{
			result[e[i]] = -1 ;
		}
		
		int k = 0 ;
		for( int j = 0 ; j < result.length ; j++ )
		{
			if( result[j] != -1 )
			{
				places[k] = j ;
				k++ ;
			}
			
		}
			
		return places ;
		
	}
	
	//Computes the MainMatrix Optimal X and Y strategies
	//Relies on keepedElements() function
	private void computeMainGameStrategies()
	{
		
		if( deletedRows != null )
		{
			XStrategyMainGame = new String[inverseB.length+deletedRows.length][1] ;
			Matrix_String.fill(XStrategyMainGame, "0");
			//XStrategy[][]
			//PUT IN LOGIC FOR WHAT ROW GET A ZERO
			int keeprows[] = keepedElements( deletedRows ) ;
			
			for( int i = 0 ; i < keeprows.length ; i++ )
			{
				XStrategyMainGame[keeprows[i]][0] = XStrategy[0][i] ;
			}
			
						
		}
		else
		{
			XStrategyMainGame = XStrategy ;
		}
		
		if( deletedCols != null )
		{
			
			YStrategyMainGame = new String[inverseB.length+deletedCols.length][1] ;
			//YStrategy[][]
			
			//YStrategyMainGame = new String[inverseB.length+deletedRows.length][1] ;
			Matrix_String.fill(YStrategyMainGame, "0");
			//YStrategy[][]
			//PUT IN LOGIC FOR WHAT COLUMNS GET A ZERO
			int keepcols[] = keepedElements( deletedCols ) ;
			
			for( int i = 0 ; i < keepcols.length ; i++ )
			{
				YStrategyMainGame[keepcols[i]][0] = YStrategy[0][i] ;
			}
			
			
			
			
			
		}
		else
		{
			YStrategyMainGame = YStrategy ;
		}
		
		
		return ;
		
		
	}
	
	
	public String[][] getinverseB()
	{
		return inverseB ;
	}
	
	public String getGameValue()
	{
		return gamevalue ;
		
	}
	
	public int getGameValueAsInteger()
	{
		return Integer.parseInt( gamevalue ) ;
		
	}
	
	public long getGameValueAsLong()
	{
		return Long.parseLong( gamevalue ) ;
		
	}

	
	
	public String[][] getXStrategy()
	{
		return XStrategy ;
	}
	
	public String[][] getYStrategy()
	{
		return YStrategy ;
	}

	public String[][] getXStrategyMainGame()
	{
		return XStrategyMainGame ;
	}
	
	public String[][] getYStrategyMainGame()
	{
		return YStrategyMainGame ;
	}
	
	public int[] getdeletedRows()
	{
		return deletedRows ;
	}
	
	public int[] getdeletedCols()
	{
		return deletedCols ;
	}

	
	
	//Just for debugging dont use 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
