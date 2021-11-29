import gamestrategyutils.Matrix_String ;

//The issue is coming up with a algorithm to generate all submatrix of a given size of the orginal payoff matrix
//This is NP hard because one has to go thru all possible submatrix in general so going to be very slow.
//However domancy test will aid in skipping some irrelevant submatrices
//Think on how to code this above issue up !!!!

public class MixedStrategies {

	
	//Finish Coding this method to complete this project
	public OptimalPayOffData[] FindAllOptimalStrategy( String payoff[][] )
	{
		//This function unlike FindOptimalStrategy that find a optimal strategy for the finite games payoff matrix
		//This function computes all possible optimal strategies that can be played
		
		//enumerate all submatrice as well as the whole matrix itself and test to see if the optimal solution
		//for the submatrix extends up to an optimal solution to the main payoff matrix
		// if it does, it add the OptimalPayOffData object to the vector of OptimalPayoffData objects
		// if its not, it does nothing and moves on to next the submatrix until completely enumerated all possibilities.
		//The enumeration must find at least one mixed strategy solution by game theory
		
		return null ;
		
	}
	
	//Finish Coding this method to complete this project
	public OptimalPayOffData FindOptimalStrategy( String payoff[][] )
	{
		//Find a square submatrix of the payoff matrix that has nonzero determinant
		//Check weather it is an optimal solution aka all strategies activated!
		//If not search for another there will always exist at least one optimal square submatrix
		//because of game theory
		
		//Once found an optimal square submatrix use computeGameExceptedValue to compute the game value
		//From OptimalPayOffData data
		//As well as computePlayersMixedStrategies for the players playing mix strategy vector
		//From OptimalPayOffData data

		String submatrix[][] = { {"3" , "3"} ,{"4" ,"2"} } ;
		OptimalPayOffData d = computeSquareMatrixOptimal( submatrix , new int[] {2} , new int[] {1} ) ;
		//Use  computeSquareMatrixOptimal( String payoff[][] ) function to compute the submatrix optimal data
		//Also code up domency  test to rule out nonsingular / not all active strategy case when checking submatrice
		//OptimalPayOffData d = new OptimalPayOffData() ;
		String tpayoff[][] = Matrix_String.transpose(payoff) ;
		
		String MIN = findMin( Matrix_String.multiplyMatrix(tpayoff, d.getXStrategyMainGame() ) ) ;
		String MAX = findMax( Matrix_String.multiplyMatrix(payoff, d.getYStrategyMainGame() ) ) ;
		if( Matrix_String.compareFraction(MAX , MIN ) == 0)
		return d; //solution// an OptimalPayOffData object
		//else keep searching submatrices
		
		return null ; //never should get here
		
	}
	
	
	private String findMax( String m[][] )
	{    
		
		String maxval = m[0][0] ;
		for( int i = 1 ; i < m.length ; i++ )
		{
			if( Matrix_String.compareFraction(maxval , m[i][0] ) < 0 )
				maxval = m[i][0] ;

		}
		
		return maxval;
	}
	
	private String findMin( String m[][] )
	{    
		
		String minval = m[0][0] ;
		for( int i = 1 ; i < m.length ; i++ )
		{
			if( Matrix_String.compareFraction(minval , m[i][0] ) > 0 )
				minval = m[i][0] ;

		}
		
		return minval;
	}
	
	
	
	
	//Given a square payoff matrix computes the optimal strategy data and game value for it.
	//throws an error if the matrix isnt square
	//Use the returned OptimalPayOffData object with methods computePlayersMixedStrategies and computeGameExceptedValue
	//to compute the players mixed strategy vector and game value/expected value respectfully
	private OptimalPayOffData computeSquareMatrixOptimal( String payoff[][] , int drows[] , int dcols[] )
	{
	    int msize = payoff.length ;
		if( msize != payoff[0].length )
			throw new RuntimeException("Payoff Matrix Not Square!!!") ;
		
		String inversepayoff[][] = new String[msize][2*msize] ;
		
		for( int i = 0 ; i < inversepayoff.length ; i++ )
		{
			for( int j = 0 ; j < inversepayoff[0].length ; j++ )
			{
				if( j < payoff[0].length )
				inversepayoff[i][j] = payoff[i][j] ;
				else
				if( j >= payoff[0].length )
				{
					if( i == j - payoff[0].length  )
					inversepayoff[i][j] = "1" ;
					else
				    inversepayoff[i][j] = "0" ;
				}
					
					
			}
			
		}
		
		
		inversepayoff = Matrix_String.gaussElimination(inversepayoff) ;
		String tempinv[][] = new String[msize][msize] ; 
		
		for( int i = 0 ; i < msize ; i++ )
			for( int j = msize ; j < 2*msize ; j++ )
			{
				tempinv[i][j-msize] = inversepayoff[i][j] ; 
			}
		
		
		for( int k = 0 ; k < msize ; k++ )
		{
			for( int j = 0 ; j < msize ; j++ )
			tempinv[k][j] = Matrix_String.divide_fractions( tempinv[k][j] , inversepayoff[k][k] )  ;
			
		}
		
		String J[][] = new String[msize][1] ;
		Matrix_String.fill(J , "1");
		
		String tempres[][] = Matrix_String.multiplyMatrix( tempinv , J ) ;
		J = Matrix_String.transpose( J ) ;
		tempres = Matrix_String.multiplyMatrix( J , tempres ) ;
		 //Matrix_String.divide_fractions("1", tempres[0][0]) ;
		String gamevalue = Matrix_String.divide_fractions("1", tempres[0][0]) ;
		tempinv = Matrix_String.scalarmult(tempinv, gamevalue) ;
		return new OptimalPayOffData( gamevalue , tempinv , drows , dcols ) ;
		
	}
	
	public void printStrategySolution(OptimalPayOffData data) 
	{
		String rowplayer[][] = data.getXStrategyMainGame() ;
		String colplayer[][] = data.getYStrategyMainGame() ;
		String gamevalue = data.getGameValue() ;
		System.out.println( "Game Excepted Value For Optimal Play = " + gamevalue ) ;
		System.out.println( "Row Player Optimal Mixed Strategy ") ;
		printPlayerMixStrategy( rowplayer ) ;
		System.out.println( "Column Player Optimal Mixed Strategy ") ;
		printPlayerMixStrategy( colplayer ) ;
		
		
		
	}

	public void printPlayerMixStrategy( String p[][] )
	{
		for( int i = 0 ; i < p.length ; i++  )
			for( int j = 0 ; j < p[0].length ; j++ )
			{
				System.out.println(p[i][j]) ;
			}
		
	}
	
	public void printPayOffMatrix( String payoff[][] ) 
	{
		for( int i = 0 ; i < payoff.length ; i++  )
		{	for( int j = 0 ; j < payoff[0].length ; j++ )
			{
				System.out.print(payoff[i][j] + "\t") ;
			}
		    System.out.println();
		}
	}
	
	//Just for debugging dont use
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MixedStrategies ms = new MixedStrategies() ;
		String payoff[][] = {{"3","5","3"} , {"4","-3","2"} , {"3" ,"2","3"} };
		System.out.println("Payoff Matrix is ") ;
		ms.printPayOffMatrix(payoff)  ;
		
		OptimalPayOffData d = ms.FindOptimalStrategy(payoff) ;
	
		ms.printStrategySolution(d) ;
		
	}

}
