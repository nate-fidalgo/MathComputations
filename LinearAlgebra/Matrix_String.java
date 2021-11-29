

/**

This Matrix_String class is for doing symbolic matrix computations you can uses it to solve matrix of rational numbers in symbolic nice pretty form.
<br>
You can also uses the Helper function to do symbolic rational computations such as add,subtracting,dividing,multiplying fractions/rational numbers.
<br>
PLEASE NOTE WHEN YOU INPUT A RATIONAL NUMBER IT HAS TO BE IN THE FROM OF A/B WHERE A AND B ARE INTEGER VALUES THIS PROGRAM DOES NOT WORK WITH DECIMAL NUMBERS OR DECIMAL
APPROXIMATIONS IF YOU WANT THAT THE YOU WILL HAVE TO WAIT UNTIL I UPLOAD MY NUMBERICAL ANALYSIS PACKAGES AS THOSE ARE MORE FOR DECIMALS AND APPROXIMATIONS.
THE WHOLE POINT OF THIS CLASS IS YOU WANT SYMBOLIC EXACT NUMBERS NO APPROXIMATIONS LIKE THE GREAT WOLFRAM MATHEMATICA PROGRAM YIELDS

*/

public class Matrix_String {


/**
@params String matrix[][] a matrix of rational numbers as Strings 
@return the result the matrix simplied using gaussElimination the algorithm is time complexity O(n^3) 
*/

private static int isevenswap = 0 ;

//helper function for computing the determinant of a square matrix
//which keeps track of the amount of row swaps which will change the determinant D from D to -D
//based on the even or odd amount of row swaps.
public static boolean SwapCountEven()
{
	if( isevenswap % 2 == 0 )
		return true ;
	
	return false ;
	
}
	
public static String[][] gaussElimination( String matrix[][] )
{
    
    int max_row_element_index = 1 ;
    boolean need_to_swap_row = false ;
    isevenswap = 0 ;
    
    for( int i = 0 ; i < matrix[0].length ; i++ )
    {
        
        max_row_element_index = i ;
        for( int j = i + 1 ; j < matrix.length ; j++ )
        if( greaterAbsVALUE( matrix[j][i] , matrix[ max_row_element_index ][i] ) == true )
        {
        max_row_element_index = j ;
        need_to_swap_row = true ;
        }
        
        
        if( max_row_element_index < matrix.length && matrix[max_row_element_index][i].equals("0") == false )
        {
        
                
        if( need_to_swap_row == true )
        {
        	isevenswap++ ;
            String holdrow[] = new String[ matrix[0].length ] ;
            for( int p = 0 ; p < matrix[0].length ; p++ )
            {
                
                holdrow[p] = matrix[i][p] ;
                matrix[i][p] = matrix[max_row_element_index][p] ;
                matrix[max_row_element_index][p] = holdrow[p] ;
                
            }
        
                
        }
        
    
        for( int j = i + 1 ; j < matrix.length ; j++ )
          for( int k = matrix[0].length - 1 ; k >= i ; k-- )
              matrix[j][k] = subtract_fractions( matrix[j][k] , mult_fractions( matrix[i][k] , divide_fractions( matrix[j][i] , matrix[i][i] ) ) ) ; 
        
        need_to_swap_row = false ;
     
        
       }
        

    }

    
   
       
     for( int i = matrix[0].length - 1 ; i >= 0 ; i-- )
     {
        
                 
       for( int j = i - 1 ; j >= 0 ; j-- )
          for( int k = matrix[0].length - 1 ; k >= 0 ; k-- )
              if( j < matrix.length && i < matrix.length )
              matrix[j][k] = subtract_fractions( matrix[j][k] , mult_fractions( matrix[i][k] , divide_fractions( matrix[j][i] , matrix[i][i] ) ) ) ;
        
                
       }
        
   
    return matrix ;
    
   
}




/**
@params String fraction a fraction of the form a/b 
@return the reduced or most simplied possible form of the fraction as a String
*/
public static String reduce_fraction( String fraction )
{

  int numerator = 0 ;
  int denominator = 0 ;
  String reducedfraction = "" ;
  
  if( fraction.equals("0") == true || fraction.equals("-0") == true )
      return "0" ;
  
  if( has_no_dash( fraction ) == true )
      return fraction.trim() ;
  
  for( int i = 0 ; i < fraction.length() ; i++ )
     if( ("" + fraction.charAt(i)).equals( "/" ) )
     {
        numerator = Integer.parseInt( fraction.substring( 0 , i ).trim() ) ;
        denominator = Integer.parseInt( fraction.substring( i+1 , fraction.length() ).trim() ) ;
        break;
     }

  if( denominator == 0 )
  return fraction + " error divide by zero" ;
  
  int gcd = gcd( numerator , denominator ) ;
  numerator = numerator/gcd ;
  denominator = denominator/gcd ;
  
  if( denominator == 1 )
  reducedfraction = "" + numerator ;
  else
  if( denominator == -1 && numerator > 0 )
  reducedfraction = "-" + numerator ;
  else
  if( denominator == -1 && numerator < 0 )
  reducedfraction = "" + (-1 * numerator ) ;
  else
  if( numerator == 0 )
  reducedfraction = "0" ;
  else
  if( denominator < 0 && numerator < 0 )
  reducedfraction = "" + (-1 * numerator ) + "/" + (-1 * denominator ) ;
  else
  reducedfraction = numerator + "/" + denominator ;

  return reducedfraction ;

}


/**
@params String fraction1 a fraction of the form a/b 
@params String fraction2 a fraction of the form c/d
@return the result of the two fractions add together aka (a/b)+(c/d) = (ad+cb)/bd in simplifed fraction form
*/
public static String add_fractions( String fraction1, String fraction2 )
{

  int numerator1 = 0 ;
  int denominator1 = 0 ;
  int numerator2 = 0 ;
  int denominator2 = 0 ;
  String reducedfraction = "" ;
  
  if( fraction1.equals("0") == true || fraction1.equals("-0") == true )
      return reduce_fraction( fraction2 );
  
  if( fraction2.equals("0") == true || fraction2.equals("-0") == true)
      return reduce_fraction( fraction1 );
  
  if( has_no_dash( fraction1 ) == true && has_no_dash( fraction2 ) == true )
  return "" + ( Integer.parseInt( fraction1.trim() ) + Integer.parseInt( fraction2.trim() ) ) ;
  else  
  if( has_no_dash( fraction1 ) == true )
  return "" + reduce_fraction( (Integer.parseInt(fraction1) * Integer.parseInt(get_denominator(fraction2)) + Integer.parseInt(get_numerator(fraction2))) + "/" + get_denominator(fraction2) ) ;
  else
  if( has_no_dash( fraction2 ) == true )
  return "" + reduce_fraction( (Integer.parseInt(fraction2) * Integer.parseInt(get_denominator(fraction1)) + Integer.parseInt(get_numerator(fraction1))) + "/" + get_denominator(fraction1) ) ;
  
    
  for( int i = 0 ; i < fraction1.length() ; i++ )
     if( ("" + fraction1.charAt(i)).equals( "/" ) )
     {
        numerator1 = Integer.parseInt( fraction1.substring( 0 , i ).trim() ) ;
        denominator1 = Integer.parseInt( fraction1.substring( i+1 , fraction1.length() ).trim() ) ;
        break;
     }
    
  
   for( int i = 0 ; i < fraction2.length() ; i++ )
     if( ("" + fraction2.charAt(i)).equals( "/" ) )
     {
        numerator2 = Integer.parseInt( fraction2.substring( 0 , i ).trim() ) ;
        denominator2 = Integer.parseInt( fraction2.substring( i+1 , fraction2.length() ).trim() ) ;
        break;
     }
  
    
    if( denominator1 == 0 || denominator2 == 0 )
        return fraction1 + " + " + fraction2 + " divide by zero error " ; 
  
  
   if( denominator1 * denominator2 == -1 && (numerator1 * denominator2 + numerator2 * denominator1) > 0 )
   return "-" + (numerator1 * denominator2 + numerator2 * denominator1) ;
  
   if( denominator1 * denominator2 == -1 && (numerator1 * denominator2 + numerator2 * denominator1) < 0 )
   return "" + ( -1* (numerator1 * denominator2 + numerator2 * denominator1) ) ;
  
   if( (numerator1 * denominator2 + numerator2 * denominator1) == 0 && (denominator2 * denominator1 != 0 ) )
   return "0" ;
  
   if( denominator1 * denominator2 < 0 && (numerator1 * denominator2 + numerator2 * denominator1) < 0 )
   return reduce_fraction( ( -1 * (numerator1 * denominator2 + numerator2 * denominator1) ) + "/" + ( -1 * (denominator1 * denominator2) ) ) ; 
   
  
  
   return reduce_fraction( (numerator1 * denominator2 + numerator2 * denominator1) + "/" + (denominator1 * denominator2) ) ; 
  
  
  
    
}

/**
@params String s : A rational number 
@return the true if the rational number is an integer or another words contains no dash / Vinculum false otherwise
*/
private static boolean has_no_dash( String s )
{
    
 for( int i = 0 ; i < s.length() ; i++ )
     if( ( "" + s.charAt(i) ).equals("/") ) 
       return false ;
    
  return true ;   
 
}

/**
@params String val a fraction of the form a/b 
@return the result a String of the denominator of the fraction of val
*/
public static String get_denominator( String val )
{
    
    String denominator = "" ;
    
   for( int i = 0 ; i < val.length() ; i++ )
     if( ("" + val.charAt(i)).equals( "/" ) )
     {
        denominator = val.substring( i+1 , val.length() ).trim() ;
        break;
     }
    
    
    if( denominator.equals("") == true )
        return "1" ;
    else
        return denominator ;
    
    
}



/**
@params String val a fraction of the form a/b 
@return the result as a String of the numerator of the fraction for val
*/
public static String get_numerator( String val )
{
    
    String numerator = "" ;
    
   for( int i = 0 ; i < val.length() ; i++ )
     if( ("" + val.charAt(i)).equals( "/" ) )
     {
        numerator = val.substring( 0 , i ).trim() ;
        break;
     }
    
    
    if( numerator.equals("") == true )
        return val.trim() ;
    else
        return numerator ;
    
    
}


/**
@params String fraction1 a fraction of the form a/b 
@params String fraction2 a fraction of the form c/d
@return the result of the two fractions subtracted together aka (a/b)-(c/d) = (ad-cb)/bd in simplifed fraction form
*/
public static String subtract_fractions( String fraction1 , String fraction2 )
{

  if( Integer.parseInt(get_numerator(fraction2)) > 0 )  
  return add_fractions( fraction1 , "-" + fraction2 ) ; 
  else 
  return add_fractions( fraction1 , ( -1 * Integer.parseInt( get_numerator( fraction2 ) ) ) + "/" + get_denominator( fraction2 )  ) ; 
  
  
}


/**
@params String fraction1 a fraction of the form a/b 
@params String fraction2 a fraction of the form c/d
@return the result of the two fractions multiplied together aka (a/b)x(c/d) = ac/bd in simplifed fraction form
*/
public static String mult_fractions( String fraction1 , String fraction2 )
{
    
    return reduce_fraction( "" + (Integer.parseInt( get_numerator( fraction1 ) ) * Integer.parseInt( get_numerator( fraction2 ) ) ) + "/" + (Integer.parseInt( get_denominator( fraction1 ) ) * Integer.parseInt( get_denominator( fraction2 ) )) ) ;
    
}


/**
@params String fraction1 a fraction of the form a/b 
@params String fraction2 a fraction of the form c/d
@return the result of the two fractions divided aka (a/b)/(c/d) = ad/bc in simplifed fraction form
*/
public static String divide_fractions( String fraction1 , String fraction2 )
{
    
    if( get_denominator(fraction1).equals("0") || get_denominator(fraction2).equals("0") || get_denominator(fraction1).equals("-0") || get_denominator(fraction2).equals("-0") )
    return "(" + fraction1 + ")" + " / " + "(" + fraction2 + ")" + " Divide by Zero Error " ;
    
     return reduce_fraction( "" + (Integer.parseInt( get_numerator( fraction1 ) ) * Integer.parseInt( get_denominator( fraction2 ) ) ) + "/" + (Integer.parseInt( get_denominator( fraction1 ) ) * Integer.parseInt( get_numerator( fraction2 ) )) ) ;

}




public static boolean greaterAbsVALUE( String fraction1 , String fraction2 )
{


   if( Math.abs( Integer.parseInt( get_numerator( fraction1 ) ) * Integer.parseInt( get_denominator( fraction2 ) ) ) > Math.abs( Integer.parseInt( get_numerator( fraction2 ) ) * Integer.parseInt( get_denominator( fraction1 ) ) ) )
   return true ;
   else
   return false ;


}


/**
@params int m integer 
@params int n integer
@return int the gcd of the two numbers m and n
The main work horse of function that computes the gcd is called by gcd function as well
*/
public static int euclid_Alogrithm( int m , int n )
{

//These do nothing if m and n are all ready positive
//if not set m and n the |m| and |n| so algorithm works right!!!
m = Math.abs(m);    
n = Math.abs(n);    
//------------------------------------------------------    
int r = 0 ;

while( n != 0 )
{

  r = m % n ; 
  m = n ;
  n = r ;

}

return m ;

}

/**
@params int m integer 
@params int n integer
@return int the gcd of the two numbers m and n
*/
public static int gcd( int m , int n )
{ 
    return euclid_Alogrithm( m , n ) ;
}


//Compute the determinant from gaussian elimination O(n^3) runtime algorithm
public static String determinant(String m[][])
{
	String d[][] = gaussElimination( m ) ;
	String det = "1" ;
	for( int i = 0 ; i < d.length ; i++ )
	{
		det = mult_fractions( d[i][i] , det ) ;
	}
	
	if( SwapCountEven() == true )
	return det ;
	
	return mult_fractions( "-1" , det ) ;
	
}


/*
 * This function computes the resultant of two polynomials a[] and b[]
 * returns resultant number as a String associated to the polynomials
 */
public static String resultant(String a[] , String b[])
{
	int size = (a.length + b.length)-2 ;
	String det[][] = new String[size][size] ;
	
	String detop[][] = new String[b.length -1][size] ;
	String debot[][] = new String[a.length -1][size] ;
	
	for( int row = 0 ; row < size ; row++ )
	{
		if( row < b.length-1 )
		shiftRow(detop, a , row ) ;
		else
		shiftRow(debot, b , row - (b.length - 1)) ;

	}
	

	for( int i = 0 ; i < detop.length ; i++)
	{
		
		for( int j = 0 ; j < detop[0].length ; j++ )
		{
			if( detop[i][j] != null )
			det[i][j] = detop[i][j] ;
			else
			det[i][j] = "0" ;
			
		}

	}
	
	
	for( int i = 0 ; i < debot.length ; i++)
	{
		
		for( int j = 0 ; j < debot[0].length ; j++ )
		{
			if( debot[i][j] != null )
			det[detop.length+ i][j] = debot[i][j] ;
			else
			det[detop.length+ i][j] = "0" ;
			
		}

	}
	
	
	
/*	
	   for( int i = 0 ; i < det.length ; i++ )
	   {
	     System.out.println() ; 
	     foar( int j = 0 ; j < det[0].length ; j++ )
	        System.out.print( "\t" + det[i][j] ) ; 
	   
	   }
*/	
	return determinant(det);
	

}

//Helper function to build the Sylvester matrix for computing the resultant
public static void shiftRow( String m[][] , String a[] , int row) 
{
	
	for( int i = 0 ; i < a.length ; i++)
	{
		if( row+i < m[0].length )
		m[row][row+i] = a[i] ;
	}
		
}


//A naive matrix multiplication function that multiplies two matrices of appropriate size
//returns the result as a String matrix
//O(n^3) is just fine but eventually all recode to use the fastest math matrix multiplcation
//Optimality is O(nlogn) it is believed and recently in theory it was achieved
public static String[][] multiplyMatrix( String m1[][] , String m2[][] )
{
	if( m1[0].length != m2.length )
	throw new RuntimeException("Matrices not correct size for multiplication!!!") ;
		
	int r = m1.length ;
	int p = m2[0].length ;
	
	String result[][] = new String[r][p] ;
	fill( result , "0") ;
	
	
	for( int i = 0 ; i < r ; i++ )
		for( int j = 0 ; j < p ; j++ )
		{
			for( int k = 0 ; k < m2.length ; k++ )
			result[i][j] = add_fractions( result[i][j] , mult_fractions( m1[i][k] , m2[k][j] ) ) ; //m1[i][k] * m2[j][k] ;
					
		}
			
			
			
	return result ;
	
}

//Helper function for initializing a String matrix to all values of second parameter val
public static void fill( String m[][] , String val)
{
	for( int i = 0 ; i < m.length ; i++ )
	{
		for( int j = 0 ; j < m[0].length ; j++ )
		{
			m[i][j] = val ;
		}
		
	}
	
	return ;
}


///computes the transpose of a matrix
//returns the transpose of a matrix
public static String[][] transpose( String m[][] )
{
	
	String result[][] = new String[m[0].length][m.length] ;
	
	for( int i = 0 ; i < m.length ; i++ )
		for( int j = 0 ; j < m[0].length ; j++ )
		{
			result[j][i] = m[i][j] ;
			
		}
	
	return result ;
	
	
}



public static int compareFraction( String frac1 , String frac2 )
{
	boolean isneg1 = isnegativefraction( frac1 );
	boolean isneg2 = isnegativefraction( frac2 );
	
   if( Math.abs( Integer.parseInt( get_numerator( frac1 ) ) * Integer.parseInt( get_denominator( frac2 ) ) ) == Math.abs( Integer.parseInt( get_numerator( frac2 ) ) * Integer.parseInt( get_denominator( frac1 ) ) ) )
   return 0 ;
   
   if( Math.abs( Integer.parseInt( get_numerator( frac1 ) ) * Integer.parseInt( get_denominator( frac2 ) ) ) > Math.abs( Integer.parseInt( get_numerator( frac2 ) ) * Integer.parseInt( get_denominator( frac1 ) ) ) )
   {
	   if( isneg1 == isneg2 && isneg1 == true )
	   return -1 ;
	   
	   if( isneg1 == isneg2 && isneg1 == false )
	   return 1 ;
	   
	   if( isneg1 != isneg2 && isneg1 == true )
	   return  -1 ;
	   
	   if( isneg1 == isneg2 && isneg1 == false )
	   return 1 ;
	   
   }
   else
   {
	   if( isneg1 == isneg2 && isneg1 == true )
	   return 1 ;
	   
	   if( isneg1 == isneg2 && isneg1 == false )
	   return -1 ;
	   
	   if( isneg1 != isneg2 && isneg1 == true )
	   return  1 ;
	   
	   if( isneg1 == isneg2 && isneg1 == false )
	   return -1 ; 
	   
   }
return -2; //should never get here
	   
	
}


private static boolean isnegativefraction( String frac )
{
   int minus1 =  get_numerator(frac).indexOf("-") ;
   int minus2 =  get_denominator(frac).indexOf("-") ;
	
   if( minus1 == minus2 )
	   return false ;
   
   return true ;
   
}


public static String[][] scalarmult( String m[][] , String scaler )
{
	String outm[][] = new String[m.length][m[0].length] ;
	for( int i = 0 ; i < m.length ; i++ )
	{
		for( int j = 0 ; j < m[0].length ; j++ )
		{
				outm[i][j] = mult_fractions( m[i][j] , scaler ) ; //m1[i][k] * m2[j][k] ;
			
		}
		
	}
	
	return outm ;
	
}



//Test main to see if the function programs work and to resolve any bug issues in code
//Dont uses Only for debugging the classes code create your Own Matrix_String object and call the gaussElimination(...) function to uses!
public static void main( String args[] )
{

   //String m[][] = { { "1" , "2" , "3" , "1" , "0" , "0" } , { "0" , "2" , "4" , "0" , "1" , "0" } , { "5" , "8" , "6/78" , "0" , "0" , "1" }  } ;
   //String m[][] = { { "9" , "35" , "4/3" , "70" } , { "4" , "3" , "4" , "8" } , { "1/5" , "1" , "1" , "3" }  } ;
  // String m[][] = { {"1","-3","1","4","7"} ,{"2","-8","8","-2","7"} ,{"-6","3","-15","9","3"}    } ;
  // String m[][] = { {"2","1","-1","8"} ,{"-3","-1","2","-11"} ,{"-2","1","2","-3"} } ;
 //  String m[][] = { {"2","-1","0","1","0","0"} , {"-1","2","-1","0","1","0"} , {"0","-1","2","0","0","1"} } ;
  // String m[][] = { { "3" , "4"  } , {"2","1" } } ;
   //String m[][] = { { "2" , "1","1"  } , {"3","3","1" } , { "1","1","9"} } ;
   String m[][] = { {"3","2","1","2"} ,{"1","2","3","3"} ,{"3","3","2","7"} , {"3","2","1","1"} } ;
	
   String a[] = { "1" , "2" , "3" } ;
   String b[] = { "2" , "2" } ;
   System.out.println( "resultant: " + resultant(a , b) );
   
   System.out.println( "det : " + determinant(m) ) ;
   m = gaussElimination( m ) ;
   System.out.println(SwapCountEven());
   for( int i = 0 ; i < m.length ; i++ )
   {
     System.out.println() ; 
     for( int j = 0 ; j < m[0].length ; j++ )
        System.out.print( "\t" + m[i][j] ) ; 
   
   }
 
   System.out.println() ;
   System.out.println() ;
   System.out.println() ; 
   System.exit(0) ;


}




}