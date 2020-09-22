

import java.math.*;





public class Matrix_Operations {


public static int[][] matrixMult( int m1[][] , int m2[][] ) throws MatrixException
{


     if( m1[0].length != m2.length )
     throw new MatrixException("\nInCompatible Matrices for Multiplying ---> must have first Matrix columns = second Matrix rows\n" ) ;


     int resultMatrix[][] = new int[ m1.length ][ m2[0].length] ;

     for( int i = 0 ; i < m1.length ; i++ )
	for( int j = 0 ; j < m2[0].length ; j++ )
          for( int k = 0 ; k < m2.length ; k++ )
              resultMatrix[i][j] = resultMatrix[i][j] + m1[i][k] * m2[k][j]  ;


      return resultMatrix ;


}

    
    

public static double[][] matrixMult( double m1[][] , double m2[][] ) throws MatrixException
{


     if( m1[0].length != m2.length )
     throw new MatrixException("\nInCompatible Matrices for Multiplying ---> must have first Matrix columns = second Matrix rows\n" ) ;


     double resultMatrix[][] = new double[ m1.length ][ m2[0].length] ;

     for( int i = 0 ; i < m1.length ; i++ )
	for( int j = 0 ; j < m2[0].length ; j++ )
          for( int k = 0 ; k < m2.length ; k++ )
              resultMatrix[i][j] = resultMatrix[i][j] + m1[i][k] * m2[k][j]  ;


      return resultMatrix ;


}




public static BigInteger[][] matrixMult( BigInteger m1[][] , BigInteger m2[][] ) throws MatrixException
{


     if( m1[0].length != m2.length )
     throw new MatrixException("\nInCompatible Matrices for Multiplying ---> must have first Matrix columns = second Matrix rows\n" ) ;


     BigInteger resultMatrix[][] = new BigInteger[ m1.length ][ m2[0].length] ;

     for( int i = 0 ; i < m1.length ; i++ )
	for( int j = 0 ; j < m2[0].length ; j++ )
          for( int k = 0 ; k < m2.length ; k++ )
              resultMatrix[i][j] = resultMatrix[i][j].add( m1[i][k].multiply( m2[k][j] ) ) ;


      return resultMatrix ;


}



public static BigDecimal[][] matrixMult( BigDecimal m1[][] , BigDecimal m2[][] ) throws MatrixException
{


     if( m1[0].length != m2.length )
     throw new MatrixException("\nInCompatible Matrices for Multiplying ---> must have first Matrix columns = second Matrix rows\n" ) ;


     BigDecimal resultMatrix[][] = new BigDecimal[ m1.length ][ m2[0].length] ;

     for( int i = 0 ; i < m1.length ; i++ )
	for( int j = 0 ; j < m2[0].length ; j++ )
          for( int k = 0 ; k < m2.length ; k++ )
              resultMatrix[i][j] = resultMatrix[i][j].add( m1[i][k].multiply( m2[k][j] ) ) ;


      return resultMatrix ;


}





public static int[][] matrixAdd( int m1[][] , int m2[][] ) throws MatrixException
{


    if( m1.length == m2.length && m1[0].length == m2[0].length )
    {

       int result[][] = new int[m1.length][m1[0].length] ;
       
       for( int i = 0 ; i < m1.length ; i++ )
         for( int j = 0 ; j < m1[0].length ; j++ )
         result[i][j] = m1[i][j] + m2[i][j] ;

       return result ;


    }
    else
    {

       throw new MatrixException("\nInCompatible Matrices: Must be of the same size to ADD\n") ;    

    }


    

}




public static double[][] matrixAdd( double m1[][] , double m2[][] ) throws MatrixException
{


    if( m1.length == m2.length && m1[0].length == m2[0].length )
    {

       double result[][] = new double[m1.length][m1[0].length] ;
       
       for( int i = 0 ; i < m1.length ; i++ )
         for( int j = 0 ; j < m1[0].length ; j++ )
         result[i][j] = m1[i][j] + m2[i][j] ;

       return result ;


    }
    else
    {

       throw new MatrixException("\nInCompatible Matrices: Must be of the same size to ADD\n") ;    

    }



}





public static BigInteger[][] matrixAdd( BigInteger m1[][] , BigInteger m2[][] ) throws MatrixException
{


    if( m1.length == m2.length && m1[0].length == m2[0].length )
    {

       BigInteger result[][] = new BigInteger[m1.length][m1[0].length] ;
       
       for( int i = 0 ; i < m1.length ; i++ )
         for( int j = 0 ; j < m1[0].length ; j++ )
         result[i][j] = m1[i][j].add( m2[i][j] ) ;

       return result ;


    }
    else
    {

       throw new MatrixException("\nInCompatible Matrices: Must be of the same size to ADD\n") ;    

    }


    
}






public static BigDecimal[][] matrixAdd( BigDecimal m1[][] , BigDecimal m2[][] ) throws MatrixException
{


    if( m1.length == m2.length && m1[0].length == m2[0].length )
    {

       BigDecimal result[][] = new BigDecimal[m1.length][m1[0].length] ;
       
       for( int i = 0 ; i < m1.length ; i++ )
         for( int j = 0 ; j < m1[0].length ; j++ )
         result[i][j] = m1[i][j].add( m2[i][j] ) ;

       return result ;


    }
    else
    {

       throw new MatrixException("\nInCompatible Matrices: Must be of the same size to ADD\n") ;    

    }


    
}



public static int[][] transposeMatrix( int m[][] )
{
    
    int transpose[][] = new int[m[0].length][m.length] ; 
    
    for( int i = 0 ; i < transpose.length ; i++ )
        for( int j = 0 ; j < transpose[0].length ; j++ )
        transpose[i][j] = m[j][i] ;
    
      
    return transpose ;
            
}



public static double[][] transposeMatrix( double m[][] )
{
    
    double transpose[][] = new double[m[0].length][m.length] ; 
    
    for( int i = 0 ; i < transpose.length ; i++ )
        for( int j = 0 ; j < transpose[0].length ; j++ )
        transpose[i][j] = m[j][i] ;
    
      
    return transpose ;
            
}



public static BigInteger[][] transposeMatrix( BigInteger m[][] )
{
    
    BigInteger transpose[][] = new BigInteger[m[0].length][m.length] ; 
    
    for( int i = 0 ; i < transpose.length ; i++ )
        for( int j = 0 ; j < transpose[0].length ; j++ )
        transpose[i][j] = m[j][i] ;
    
      
    return transpose ;
            
}





public static BigDecimal[][] transposeMatrix( BigDecimal m[][] )
{
    
    BigDecimal transpose[][] = new BigDecimal[m[0].length][m.length] ; 
    
    for( int i = 0 ; i < transpose.length ; i++ )
        for( int j = 0 ; j < transpose[0].length ; j++ )
        transpose[i][j] = m[j][i] ;
    
      
    return transpose ;
            
}



public static boolean isEqualMatrices( int m1[][] , int m2[][] )
{
    
    if( m1.length != m2.length || m1[0].length != m2[0].length )
        return false ;
    
    for( int i = 0 ; i < m1.length ; i++ )
        for( int j = 0 ; j < m1[0].length ; j++ )
        if( m1[i][j] != m2[i][j] )
            return false ;
    
   
    return true ;
    
}





public static boolean isEqualMatrices( double m1[][] , double m2[][] )
{
    
    if( m1.length != m2.length || m1[0].length != m2[0].length )
        return false ;
    
    for( int i = 0 ; i < m1.length ; i++ )
        for( int j = 0 ; j < m1[0].length ; j++ )
        if( m1[i][j] != m2[i][j] )
            return false ;
    
   
    return true ;
    
}





public static boolean isEqualMatrices( BigInteger m1[][] , BigInteger m2[][] )
{
    
    if( m1.length != m2.length || m1[0].length != m2[0].length )
        return false ;
    
    for( int i = 0 ; i < m1.length ; i++ )
        for( int j = 0 ; j < m1[0].length ; j++ )
        if( m1[i][j].compareTo( m2[i][j] ) != 0 )
            return false ;
    
   
    return true ;
    
}




public static boolean isEqualMatrices( BigDecimal m1[][] , BigDecimal m2[][] )
{
    
    if( m1.length != m2.length || m1[0].length != m2[0].length )
        return false ;
    
    for( int i = 0 ; i < m1.length ; i++ )
        for( int j = 0 ; j < m1[0].length ; j++ )
        if( m1[i][j].compareTo( m2[i][j] ) != 0 )
            return false ;
    
   
    return true ;
    
}





public static boolean isSquareMatrix( int m[][] )
{
    
   if( m.length == m[0].length )
       return true ;
   else
       return false ;
    
}



public static boolean isSquareMatrix( double m[][] )
{
    
   if( m.length == m[0].length )
       return true ;
   else
       return false ;
    
}




public static boolean isSquareMatrix( BigInteger m[][] )
{
    
   if( m.length == m[0].length )
       return true ;
   else
       return false ;
    
}




public static boolean isSquareMatrix( BigDecimal m[][] )
{
    
   if( m.length == m[0].length )
       return true ;
   else
       return false ;
    
}



public static int traceOfMatrix( int m[][] )throws MatrixException
{
    
    int trace = 0 ;
    
    if( isSquareMatrix( m ) == true )
    {
        
    for( int i = 0 ; i < m.length ; i++ )
    trace += m[i][i] ;   
    
    return trace ;
    
    }
    else
    throw new MatrixException("\nMUST BE A SQUARE MATRIX TO USE TRACE METHOD\nMATRIX WAS NOT SQUARE\n") ;    
    
   
}





public static double traceOfMatrix( double m[][] )throws MatrixException
{
    
    double trace = 0 ;
    
    if( isSquareMatrix( m ) == true )
    {
        
    for( int i = 0 ; i < m.length ; i++ )
    trace += m[i][i] ;   
    
    return trace ;
    
    }
    else
    throw new MatrixException("\nMUST BE A SQUARE MATRIX TO USE TRACE METHOD\nMATRIX WAS NOT SQUARE\n") ;    
    
   
}




public static BigInteger traceOfMatrix( BigInteger m[][] )throws MatrixException
{
    
    BigInteger trace = BigInteger.ZERO ;
    
    if( isSquareMatrix( m ) == true )
    {
        
    for( int i = 0 ; i < m.length ; i++ )
    trace = trace.add( m[i][i] ) ;   
    
    return trace ;
    
    }
    else
    throw new MatrixException("\nMUST BE A SQUARE MATRIX TO USE TRACE METHOD\nMATRIX WAS NOT SQUARE\n") ;    
    
   
}





public static BigDecimal traceOfMatrix( BigDecimal m[][] )throws MatrixException
{
    
    BigDecimal trace = new BigDecimal(BigInteger.ZERO) ;
    
    if( isSquareMatrix( m ) == true )
    {
        
    for( int i = 0 ; i < m.length ; i++ )
    trace = trace.add( m[i][i] ) ;   
    
    return trace ;
    
    }
    else
    throw new MatrixException("\nMUST BE A SQUARE MATRIX TO USE TRACE METHOD\nMATRIX WAS NOT SQUARE\n") ;    
    
   
}


/*

public static int bandwidthOfMatrix( int m[][] )
{
    
    
    int bandwidth = -1 ;
    
    for( int i = 0 ; i < m.length ; i++ )
      for( int j = i ; j < m[0].length ; j++ )
      if( m[i][j] != 0 && Math.abs(i - j)  )
    
    
    
    
}


*/
/*******************************************************************
public static double[][] gaussElimination( double matrix[][] )
{
    
    int max_row_element_index = 1 ;
    boolean need_to_swap_row = false ;
    
    
    for( int i = 0 ; i < matrix[0].length ; i++ )
    {
        
        max_row_element_index = i ;
        for( int j = i + 1 ; j < matrix.length ; j++ )
        if( Math.abs( matrix[j][i] ) > Math.abs( matrix[ max_row_element_index ][i] ) )
        {
        max_row_element_index = j ;
        need_to_swap_row = true ;
        }
        
        
        if( matrix[max_row_element_index][i] != 0 )
        {
        
        System.out.println( "Counting " + i + " " + matrix[max_row_element_index][i] ) ;        
        if( need_to_swap_row == true )
        {
            
            double holdrow[] = new double[ matrix[0].length ] ;
            for( int p = 0 ; p < matrix[0].length ; p++ )
            {
                System.out.println( "3trgrt" + " " + i + " " + matrix[max_row_element_index][p] ) ;
               // holdrow[p - i] = matrix[i+1][p - i] ;
               // matrix[i+1][p-i] = matrix[max_row_element_index][p-i] ;
               // matrix[max_row_element_index][p-i] = holdrow[p - i] ;
                holdrow[p] = matrix[i][p] ;
                matrix[i][p] = matrix[max_row_element_index][p] ;
                matrix[max_row_element_index][p] = holdrow[p] ;
                
            }
        
                
        }
        
    
        for( int j = i + 1 ; j < matrix.length ; j++ )
          for( int k = matrix[0].length - 1 ; k >= i ; k-- )
              matrix[j][k] = matrix[j][k] - matrix[i][k] * ( matrix[j][i] / matrix[i][i] ) ; 
        
        need_to_swap_row = false ;
     
        
       }
        

    }
    
    
    return matrix ;
    
    
    
}


*****************************************************/





public static double[][] gaussElimination( double matrix[][] )
{
    
    int max_row_element_index = 1 ;
    boolean need_to_swap_row = false ;
    
    
    for( int i = 0 ; i < matrix[0].length ; i++ )
    {
        
        max_row_element_index = i ;
        for( int j = i + 1 ; j < matrix.length ; j++ )
        if( Math.abs( matrix[j][i] ) > Math.abs( matrix[ max_row_element_index ][i] ) )
        {
        max_row_element_index = j ;
        need_to_swap_row = true ;
        }
        
        
        if( max_row_element_index < matrix.length && matrix[max_row_element_index][i] != 0 )
        {
        
                
        if( need_to_swap_row == true )
        {
            
            double holdrow[] = new double[ matrix[0].length ] ;
            for( int p = 0 ; p < matrix[0].length ; p++ )
            {
                
                holdrow[p] = matrix[i][p] ;
                matrix[i][p] = matrix[max_row_element_index][p] ;
                matrix[max_row_element_index][p] = holdrow[p] ;
                
            }
        
                
        }
        
    
        for( int j = i + 1 ; j < matrix.length ; j++ )
          for( int k = matrix[0].length - 1 ; k >= i ; k-- )
              matrix[j][k] = matrix[j][k] - matrix[i][k] * ( matrix[j][i] / matrix[i][i] ) ; 
        
        need_to_swap_row = false ;
     
        
       }
        

    }

    
    
    int index_for_row = matrix.length - 1 ;
    
     for( int i = matrix[0].length - 1 ; i >= 0 ; i-- )
     {
        
                 
       for( int j = i - 1 ; j >= 0 ; j-- )
          for( int k = matrix[0].length - 1 ; k >= index_for_row ; k-- )
              if( j < matrix.length && i < matrix.length )
              matrix[j][k] = matrix[j][k] - matrix[i][k] * ( matrix[j][i] / matrix[i][i] ) ; 
        
        index_for_row-- ;
        
       }
        

    return matrix ;
    
   
}
















public static void main( String args[] )
{

   double arraym[][] = { { 1 , 2, 3 , 1,3 } , { 3 , 2, 1 , 1,7 } , { 0 , 2, 4 , 1,1 } , {1,1,1,1,4} } ;

      
   for( int i = 0 ; i < arraym.length ; i++ )
   {
       System.out.println() ;
       for( int j = 0 ; j < arraym[0].length ; j++ )
       System.out.print( " " + arraym[i][j] ) ;
   }
   
   
    System.out.println() ;
    System.out.println() ;
    System.out.println() ;
    
   double resulta[][] = gaussElimination( arraym ) ;

     for( int i = 0 ; i < resulta.length ; i++ )
     {
       System.out.println() ;
       for( int j = 0 ; j < resulta[0].length ; j++ )
       System.out.print( "   " + resulta[i][j] ) ;
     }
   
    
   int a1[][] = { {1,2,3,4} ,{ 1,1,1,1 } } ;
   int a2[][] = { {1,2,3,4} , { 2,2,2,2 } } ; //, { 1,1,1,1} , {1,3,5,3} } ;
   int r[][] = matrixAdd( a1 , a2 ) ;
   
   System.out.println() ;
   System.out.println() ;
   
   for( int i = 0 ; i < r.length ; i++ )
     {
       System.out.println() ;
       for( int j = 0 ; j < r[0].length ; j++ )
       System.out.print( "   " + r[i][j] ) ;
     }
   
   System.out.println() ;
   System.out.println() ;
   
   int mt[][] = { {1,2,3,4,5,6} , {2,3,4,5,6,7} , {3,4,55,4,3,7} } ;
   
   int rt[][] = transposeMatrix( mt ) ;
   
   
   for( int i = 0 ; i < rt.length ; i++ )
   {
       System.out.println() ;
       for( int j = 0 ; j < rt[0].length ; j++ )
       System.out.print( "   " + rt[i][j] ) ;
   }
   
   System.out.println() ;
   int mtr[][] = { { 1 , 2 , 3 } , { 2 , 3 , 5 } , { 3 , 4, 7 } }  ;
   System.out.println( "\n" + traceOfMatrix( mtr ) ) ;
   
    
   
   System.exit(0) ;
   
   
    
    
}



}
