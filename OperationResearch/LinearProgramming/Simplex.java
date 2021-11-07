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
	public static int FindMinPivoitCol( BigRational tableau[][] )
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
	
	//Function that find the next pivoit row given the tableau and col
	//used in determining/updating the current basic variables in a given iteration of the simplex
	//basically it finds the min_val{ RHS/[row][mincol] } 
	public int FindMinPivoitRow( BigRational tableau[][] , int col)
	{
		BigRational ZERO = new BigRational( BigInteger.ZERO , BigInteger.ONE ) ;
		BigRational min = tableau[0][tableau[0].length-1].div( tableau[0][col] );
		int pivoitrow = 0 ;
		
		for(int i = 1 ; i < tableau.length - 1 ; i++ )
		{
			if( tableau[i][col].compare(ZERO) != 0)
			{
				
			if( min.compare( tableau[i][tableau[0].length-1].div( tableau[i][col] ) ) == 1  )
			{
				min = tableau[i][tableau[0].length-1].div( tableau[i][col] ) ;
				pivoitrow = i ;
			}
				
			}
			
		}
		
		return pivoitrow ;
		
	}
	
    //work horse of the simplex method similar to gaussian elimination but applied to tableaus
	public BigRational[][] eliminate(  BigRational tableau[][] , int piviotcol , int piviotrow )
	{
		//basicvars[]
		BigRational ONE = new BigRational( BigInteger.ONE , BigInteger.ONE ) ;
		BigRational NEGONE = new BigRational( new BigInteger("-1") , BigInteger.ONE ) ;
		
		if( tableau[piviotrow][piviotcol].compare(ONE) != 0 )
		{	
		BigRational scale = tableau[piviotrow][piviotcol] ;	
		for( int i = 0 ; i< tableau[0].length ; i++ )
		{
			tableau[piviotrow][i] = tableau[piviotrow][i].div( scale ) ;			
		}
		
		}
		
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
	public boolean checkAllPositive( BigRational tableau[][] )
	{
		BigRational ZERO = new BigRational( BigInteger.ZERO,BigInteger.ONE ) ;
		
		for( int i = 0 ; i < tableau[0].length-1 ; i++ )
		{
			if( tableau[tableau.length-1][i].compare(ZERO) < 0 )
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
	public BigRational[][] computeOptimal()
	{
		while( checkAllPositive( tableau ) == false )
		{
			int col = FindMinPivoitCol(tableau ) ;
			int row = FindMinPivoitRow(tableau, col);
			tableau = eliminate( tableau , col , row ) ;
			basicvars[row] = col ;
		}
		
		BigRational sol[][] = new BigRational[tableau.length][2] ;
		for( int i = 0 ; i < tableau.length ; i++ )
		{
			sol[i][1] =  tableau[i][tableau[0].length-1] ;	
		    sol[i][0] = new BigRational( new BigInteger( "" + basicvars[i] ) , BigInteger.ONE ) ;
		}
		
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
	
	//simple method to print the solution of the simplex method
	//and information relevant to optimal solutions for the linear program problem
	public void printOptimalValue( BigRational sol[][] )
	{
		System.out.println( "The OptimalValue = " + sol[sol.length-1][1]  ) ;
		
		System.out.println( "The coordinates for optimal value are " ) ;
	    for( int i = 0 ; i < sol.length - 1 ; i++ )
	    {
	    	
	    	System.out.print( "x"+sol[i][0] + " = " +  ( ( isSlack(sol[i][0]) )  ? "0"  : "" + sol[i][1] ) + " , ");
	    }
	    System.out.println("\nThe slack vars are at col indexes ") ;
	    for( int i = 0 ; i < slackvars.length ; i++ )
	    {
	    	System.out.print( slackvars[i] + " ") ;
	    }
	    System.out.println("\nAll other col indexs in tableau are non slack variables ") ;

	    
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
		
		BigRational tabs[][] = Simplex.readTableau(t) ;
		Simplex s = new Simplex( tabs , new int[] {3,4,5} )  ;
	
		BigRational sol[][] = s.computeOptimal() ;
		for( int i = 0 ; i < sol.length ; i++)
			System.out.println( sol[i][0] + " " + sol[i][1] + " ") ;
		
		s.printOptimalValue( sol );
		
		
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
