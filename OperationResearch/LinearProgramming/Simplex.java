import java.math.BigInteger;

/**
 * 
 * This is the amazing algorithm of George Bernard Dantzig
 * By no means is this the most optimized or general simplex algorithm but it covers most LP problems.
 * And using Branch and Bounds /Cut the plane method one can solve integer programming problems to some extent to.
 * Ofcourse this is a first version so improvements will get incorporated later.
 * I may even extend the algorithm to cover the quadratic programming problems or at least more general convex programming problems.
 * 
 * Though it be nice if the simplex method was truely polynomial time then just on average.
 * But there is the ellipsoidal method which is proven polynomial time for LP.
 * However its less often used because the average runtime of the Simplex method is usually much better.
 * 
 * It all started in the late 40's - 60's the first way of development for solving LP.
 * Second wave was thru 60-80's when the focus was more on different methods and revision of the simplex method.
 * 
 * Now most LP stuff is pretty well known and the focus is shifting to attempting to solve nonconvex programming problems
 * Or nonlinear programming problems.
 * 
 * @author nate
 *
 */

public class Simplex {

	private static final int MAXIMIZE = -1;  //For indicating a maximization linear programming problem
	private static final int MINIMIZE = -2;  //For indicating a minimization linear programming problem
	private static final int UNBOUNDED = -3; //nonfinite solution min or max would be -inf or +inf because the feasible region is not bounded
	private static final int INFEASIBLE = -4; //used to indicate nonsolution , infeasible region or constraints
	private static final int ALTERNATIVES = -5; //used to indicate that there is more then one solution that yields optimality for problem.
	private boolean ALTERNATIVESOLUTIONS = false ; 
	
	private int basicvars[] ;
	private BigRational tableau[][] ;
	private int slackvars[] ;
	
	public Simplex( BigRational tableau[][] , int basicvarindex[] )
	{
		this.tableau = tableau ; 
		this.basicvars = new int[tableau.length];
		this.slackvars = new int[tableau.length-1];
	    for( int i = 0 ; i < basicvars.length - 1 ; i++ )
		{
			basicvars[i] = basicvarindex[i] ;
			slackvars[i]  = basicvarindex[i] ;
		}

	    basicvars[basicvars.length - 1] = -1 ;
	    
	}
	
	//Function that find the next pivoit col given the tableau 
	//used in determining/updating the current basic variables in a given iteration of the simplex
	//basically it looks for the least value column in the objective function and returns that column index
	public static int FindPivoitCol( BigRational tableau[][] , int OPTIMAL_TYPE)
	{
		if( OPTIMAL_TYPE == MAXIMIZE )
		{
		
			int piviotcol = 0 ;
			BigRational min = tableau[tableau.length-1][0] ;
			
			
			for( int k = 1 ; k < tableau[0].length - 1 ; k++ )
			{
				if( min.compare( tableau[tableau.length-1][k] ) == 1 )
				{
					min = tableau[tableau.length-1][k] ;
				    piviotcol = k ;
				
				}
				
					
			}
			
			return piviotcol ;
			
		}

		
		if( OPTIMAL_TYPE == MINIMIZE )
		{
		
			int piviotcol = 0 ;
			BigRational max = tableau[tableau.length-1][0] ;
			
			
			for( int k = 1 ; k < tableau[0].length - 1 ; k++ )
			{
				if( max.compare( tableau[tableau.length-1][k] ) == -1 )
				{
					max = tableau[tableau.length-1][k] ;
				    piviotcol = k ;
				
				}
				
					
			}
			
			return piviotcol ;
			
		}
		
		
		return -1;
			
	}
	
	//Function that find the next pivoit row given the tableau and pivoit col
	//used in determining/updating the current basic variables in a given iteration of the simplex
	//basically it finds the min_val{ RHS/[row][mincol] } 
	public int FindPivoitRow( BigRational tableau[][] , int col )
	{
		
		if( isUnbounded(tableau , col) == true )
			return UNBOUNDED ;
		
		BigRational ZERO = new BigRational( BigInteger.ZERO , BigInteger.ONE ) ;
		BigRational min = tableau[0][tableau[0].length-1].div( tableau[0][col] );
		int pivoitrow = 0 ;
		
		for(int i = 1 ; i < tableau.length - 1 ; i++ )
		{
			if( tableau[i][col].compare(ZERO) != 0 && tableau[i][col].compare(ZERO) > 0 )
			{
				
			if( min.compare( tableau[i][tableau[0].length-1].div( tableau[i][col] ) ) == 1  )
			{
				min = tableau[i][tableau[0].length-1].div( tableau[i][col] ) ;
				pivoitrow = i ;
			}
				
			}
			
		}
		
		//Double checks to see if where not in a degenerate case and if we are
		//Breaks ties with blandsRule 
		pivoitrow = blandsRule( min , tableau , col ) ;
	
		return pivoitrow ;
		
	}
	
	//A Rule to take care of degenerate cases so we dont infinite loop in the simplex algorithm
	public int blandsRule( BigRational min , BigRational tableau[][] , int col )
	{
		int has_ties = 0 ;
		int lowestindextie = -1 ;
		int updatedPiviotRow = -1 ;
		BigRational ZERO = new BigRational( BigInteger.ZERO , BigInteger.ONE ) ;
		
		for( int i = 0 ; i < tableau.length ; i++ )
		{
			if( tableau[i][col].compare(ZERO) != 0 && min.compare( tableau[i][tableau[0].length-1].div( tableau[i][col] ) ) == 0 )
			{
				if( lowestindextie > basicvars[i] || lowestindextie == -1 )
				{
					lowestindextie = basicvars[i] ;
					updatedPiviotRow = i ;
					
				}
				
				has_ties++ ;
				
			}
			
		}
		
		basicvars[updatedPiviotRow] = col ;
		if( has_ties > 1 )
		{
			ALTERNATIVESOLUTIONS = true ;
		}
		return updatedPiviotRow ;
		
		
	}
	
	
	
	//Helper function to determine if we are in a degenerate case of the simplex method
	//where there is not max or min aka its unbounded feasible region
	public boolean isUnbounded( BigRational tableau[][] , int col )
	{
		
		BigRational ZERO = new BigRational( BigInteger.ZERO , BigInteger.ONE ) ;
		
		for( int i = 0 ; i < tableau.length - 1 ; i++ )
		{
			if( tableau[i][col].compare(ZERO) == 1 )
				return false ;
			
		}
		
		return true ;
		
	}
	
    //work horse of the simplex method similar to gaussian elimination but applied to tableaus
	public BigRational[][] eliminate(  BigRational tableau[][] , int piviotcol , int piviotrow )
	{
		//basicvars[]
		BigRational ONE = new BigRational( BigInteger.ONE , BigInteger.ONE ) ;
		BigRational NEGONE = new BigRational( new BigInteger("-1") , BigInteger.ONE ) ;
		
	//	if( tableau[piviotrow][piviotcol].compare(ONE) != 0 )
	//	{	
		BigRational scale = tableau[piviotrow][piviotcol] ;	
		for( int i = 0 ; i< tableau[0].length ; i++ )
		{
			tableau[piviotrow][i] = tableau[piviotrow][i].div( scale ) ;			
		}
		
	//	}
		
		BigRational row[] = new BigRational[tableau[0].length] ;
		for( int i = 0 ; ( i < tableau.length) ; i++ )
		{
            if( i != piviotrow )
            {
    			for( int j = 0 ; j < tableau[0].length ; j++ )
    			{
    				row[j] =  ( tableau[i][piviotcol].mult(NEGONE).mult(tableau[piviotrow][j]) ) ;
    			}	
            	
    			for( int k = 0 ; k < tableau[0].length ; k++ )
    			{
    				tableau[i][k] = row[k].add(tableau[i][k]) ;
    			}
    			
            }

		//	-Rn*Rp + Rn
		}
		
		return tableau ;
	}
	
	
	// check for the condition for the simplex method to be finished
	public boolean checkFinishedPrimal( BigRational tableau[][] , int OPTIMAL_TYPE )
	{
		
		if( OPTIMAL_TYPE == MAXIMIZE )
		{
			BigRational ZERO = new BigRational( BigInteger.ZERO,BigInteger.ONE ) ;
			
			for( int i = 0 ; i < tableau[0].length-1 ; i++ )
			{
				if( tableau[tableau.length-1][i].compare(ZERO) < 0 )
					return false ;
			}
		
			return true ;
			
		}
		

		if( OPTIMAL_TYPE == MINIMIZE)
		{
			
			BigRational ZERO = new BigRational( BigInteger.ZERO,BigInteger.ONE ) ;
			
			for( int i = 0 ; i < tableau[0].length-1 ; i++ )
			{
				if( tableau[tableau.length-1][i].compare(ZERO) > 0 )
					return false ;
			}
		
			return true ;
			
		}
		
		return true ;
		
		
	}
	
	
	// check for the condition for the dual simplex method to be finished
	public boolean checkFinshedDual( BigRational tableau[][]  )
	{
		BigRational ZERO = new BigRational( BigInteger.ZERO,BigInteger.ONE ) ;
		
			for( int i = 0 ; i < tableau.length-1 ; i++ )
			{
				if( tableau[i][tableau[0].length-1].compare(ZERO) < 0 )
					return false ;
			}
			
			return true ;
			
		
	}
	
	
	
	//helper method to convert from a string matrix to a BigRational matrix that has nearly unlimited bounds
	public static BigRational[][] readTableau( String T[][] )
	{
		BigRational tab[][] = new BigRational[T.length][T[0].length] ;
		for( int i = 0 ; i < T.length ; i++ )
		   for( int j = 0 ; j < T[0].length ; j++ )
		   {
			  String a[] = T[i][j].split("/") ;
			  if(a.length != 1)
			  tab[i][j]  = new BigRational( new BigInteger( a[0].trim() ), new BigInteger( a[1].trim() ) ) ;
			  else
			  tab[i][j]  = new BigRational( new BigInteger( a[0].trim() ), BigInteger.ONE ) ;  
			  
		   }
		
		return tab ;
		
	}
	
	//helper function to display the tableau whenever you want to see the full simplex step 
	public static void printTableau( BigRational tableau[][] )
	{
		for( int i = 0 ; i < tableau.length ; i++ )
		{
			for( int j = 0 ; j < tableau[0].length ; j++ )
		
			{
				System.out.print(tableau[i][j] + " ") ;
	
			}
			System.out.println();
		}
			
	}
	
	//main function that computes the optimal essentially this is the main simplex method starting point
	//Simplex Algorithm
	public BigRational[][] computeOptimal(int OPTIMAL_TYPE)
	{
		while( checkFinishedPrimal( tableau , OPTIMAL_TYPE ) == false )
		{
			int col = FindPivoitCol(tableau , OPTIMAL_TYPE) ;
			int row = FindPivoitRow(tableau, col);
			if( row == UNBOUNDED)
			{
				BigRational sol[][] = new BigRational[tableau.length][2] ;
				for( int i = 0 ; i < tableau.length ; i++ )
				{
					sol[i][1] =  tableau[i][tableau[0].length-1] ;	
				    sol[i][0] = new BigRational( new BigInteger( "" + basicvars[i] ) , BigInteger.ONE ) ;
				}
				
				basicvars[basicvars.length-1] = UNBOUNDED ;
				sol[sol.length-1][0] = new BigRational( new BigInteger( "" + basicvars[basicvars.length-1] ) , BigInteger.ONE ) ;
	
				return sol ;
				
			}
			
			
			tableau = eliminate( tableau , col , row ) ;
			
			//Put in test for infesiblity check
			//if( isINFESIBLE( tableau ) == true )
			// return null ;
			
			//basicvars[row] = col ;
		}
		
		BigRational sol[][] = new BigRational[tableau.length][2] ;
		for( int i = 0 ; i < tableau.length ; i++ )
		{
			sol[i][1] =  tableau[i][tableau[0].length-1] ;	
		    sol[i][0] = new BigRational( new BigInteger( "" + basicvars[i] ) , BigInteger.ONE ) ;
		}
		
		basicvars[basicvars.length-1] = OPTIMAL_TYPE ;
		sol[sol.length-1][0] = new BigRational( new BigInteger( "" + basicvars[basicvars.length-1] ) , BigInteger.ONE ) ;
		
		
		return sol ;
		
	}
	
	//check to see if a index is a slack variable index in the tableau
	public boolean isSlack(BigRational s_val )
	{
		String sval = s_val.toString() ;
		int ival = Integer.parseInt(sval) ;
		for( int i = 0 ; i < slackvars.length ; i++)
		if( slackvars[i] == ival )
			return true ;
		
		return false; 
	}
	
	//simple method to print the solution of the simplex method or dual simplex method
	//and information relevant to optimal solutions for the linear programming problem
	public void printOptimalValue( BigRational sol[][] )
	{
		System.out.println( "The OptimalValue = " + sol[sol.length-1][1]  ) ;
		
//		System.out.println( "The coordinates for optimal value are " ) ;
//	    for( int i = 0 ; i < sol.length - 1 ; i++ )
//	    {
	    	
//	    	System.out.print( "x"+sol[i][0] + " = " +  ( ( isSlack(sol[i][0]) )  ? "0"  : "" + sol[i][1] ) + " , ");
//	    }
	    System.out.print("The slack variables are at column indexes ") ;
	    for( int i = 0 ; i < slackvars.length ; i++ )
	    {
	    	System.out.print( slackvars[i] + " ") ;
	    }
//	    System.out.println("\nAll other col indexs in tableau are non slack variables ") ;

	    
	}
	
	
	
	//Find the piviot row for the dual simplex algorithm
	public int FindDualPivoitRow( BigRational tableau[][] )
	{
		BigRational min = tableau[0][tableau[0].length-1] ;
		int k = 0 ;
		
		for( int i = 1 ; i < tableau.length - 1 ; i++ )
		{
			if( min.compare(tableau[i][tableau[0].length-1]) == 1 )
			{
				min = tableau[i][tableau[0].length-1] ;
				k=i ;
			}
					
		}
		
		return k ;
		
	}
	
	//Find the piviot column for the dual simplex algorithm
	public int FindDualPivoitCol( BigRational tableau[][] ,int row )
	{
		BigRational min = ( tableau[tableau.length-1][0].div( tableau[row][0] ) ).abs() ;
		int k = 0 ;
		BigRational mintestval = null ;
		BigRational ZERO = new BigRational( BigInteger.ZERO , BigInteger.ONE ) ;
		
		for( int i = 1 ; i < tableau[0].length - 1 ; i++ )
		{
			if( tableau[row][i].compare(ZERO) != 0 && tableau[tableau.length-1][i].compare(ZERO) != 0 )
			{
				mintestval = ( tableau[tableau.length-1][i].div( tableau[row][i]) ).abs() ;
				
				if( min.compare( mintestval ) == 1  )
				{
					min = mintestval ;
					k = i ;
				}
				
			}

		}
		
		return k ;
		
	}
	
	
	//This function allows you to compute the dual simplex algorithm on a tableau that is valid for dual simplex algorithm
	//Dual simplex method/algorithm works very similar to Primal "main" Simplex algorithm 
	//Is great to use when you have >= = constraints that would other wise require Big-M method or 2 Phase Method of the
	//Regular simplex algorithm
	public BigRational[][] computeOptimalByDual(int OPTIMIAL_TYPE)
	{
		
		while( checkFinshedDual( tableau ) == false )
		{
			int row = FindDualPivoitRow(tableau ) ;
			int col = FindDualPivoitCol(tableau, row);
			tableau = eliminate( tableau , col , row ) ;
			basicvars[row] = col ;
		}
		
		//check for infesibility
		//check for alternative solutions
		//check for unbounded solution
		//check for degenerate solution
		
		
		BigRational sol[][] = new BigRational[tableau.length][2] ;
		for( int i = 0 ; i < tableau.length ; i++ )
		{
			sol[i][1] =  tableau[i][tableau[0].length-1] ;	
		    sol[i][0] = new BigRational( new BigInteger( "" + basicvars[i] ) , BigInteger.ONE ) ;
		}
		
		
	//	for( int i = 0 ; i < tableau.length ; i++ )
	//	{	for( int j = 0 ; j < tableau[0].length ; j++ )
	//			System.out.print( tableau[i][j] + " " ) ; 
	//	System.out.println();
	//    }
		
		return sol ;
		
	}
	
	
	//Test to see if the variable at index i is currently a basic variable aka in the current bases
	public boolean isBasicVar( int i )
	{
		for( int k = 0 ; k < basicvars[k] ; i++ )
		{
			if( basicvars[k] == i )
				return true ;
				
		}
			
		return false;	
		
	}
	
	
	//Test to see if there is an alternate solution to the linear programming problem.
	public boolean hasAlternativeSolutions( BigRational tableau[][] )
	{
		BigRational ZERO = new BigRational( BigInteger.ZERO , BigInteger.ONE ) ;
		for( int i = 0 ; i < tableau[0].length ; i++ )
		{
			if( tableau[tableau.length-1][i].compare(ZERO) == 0 && isBasicVar( i ) == false )
			{
				return true ;
			}
				
				
		}
		
		
		return false;
		
		
	}
	
	//Test to see if there is another solution point of the problem similar to the above method
	//but doesnt have to go thru and look at the tableau its using the internal tableau that the simplex algorithm
	//reduced 
	public boolean hasAnotherSolutionPoint()
	{
		return ALTERNATIVESOLUTIONS ;
	}
	
	//Please Note this is just a test main for debugging one should
	//Note use instead create an instance of the Simplex class in there program.
	//Note also Simplex class depends on the correct BigRational class so 
	//I am uploading the BigRational class in the same folder as the Simplex class.
	//ALl under LinearProgramming subfolder of operation research folder.
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println( "The Simplex Method for LPs!!! ") ;
		
		String t[][] = {
				{"2","1","1","1","0","0","240"},
				{"1","3","2","0","1","0","360"},
				{"2","1","2","0","0","1","300"},
				{"-6","-5","-4","0","0","0","0"},
		};
		
		
		String d[][] = {
				{"2","1","1","0","0","0","600"},	
				{"1","1","0","1","0","0","225"},
				{"5","4","0","0","1","0","1000"},
				{"-1","-2","0","0","0","1","-150"},
				{"-3","-4","0","0","0","0","0"}
		};
		
		BigRational Dtabs[][] = Simplex.readTableau(d) ;
		
		BigRational tabs[][] = Simplex.readTableau(t) ;
		Simplex s = new Simplex( tabs , new int[] {3,4,5} )  ;
	
		BigRational sol[][] = s.computeOptimal( MAXIMIZE ) ;
		for( int i = 0 ; i < sol.length ; i++)
			System.out.println( sol[i][0] + " " + sol[i][1] + " ") ;
		
		s.printOptimalValue( sol );
		
		System.out.println( "\n\nDual Simplex Algorithm ") ;
		Simplex ds = new Simplex(Dtabs, new int[] {2,3,4,5} ) ;
		sol = ds.computeOptimalByDual(MAXIMIZE);
		for( int i = 0 ; i < sol.length ; i++)
			System.out.println( sol[i][0] + " " + sol[i][1] + " ") ;
		ds.printOptimalValue(sol);
		//tabs = Simplex.readTableau(t) ;
		//Simplex.printTableau(tabs) ;
	/*	
		BigRational tabs[][] = Simplex.readTableau(t) ;
		Simplex s = new Simplex( tabs ) ;
		int col = s.FindMinPivoitCol(tabs ) ;
		int row = s.FindMinPivoitRow(tabs, col);
		
		tabs = s.eliminate(tabs, col, row);
		
		System.out.println( col + " c r " + row ) ;		
		Simplex.printTableau(tabs) ;		
		System.out.println();
		col = s.FindMinPivoitCol(tabs ) ;
		row = s.FindMinPivoitRow(tabs, col);
		System.out.println( col + " c r " + row ) ;	
		tabs = s.eliminate(tabs, col, row);
		Simplex.printTableau(tabs) ;
		System.out.println();
		Simplex.printTableau( Simplex.readTableau(t)  ) ;
	*/	
	}

}
