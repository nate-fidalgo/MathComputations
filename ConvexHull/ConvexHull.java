

public class ConvexHull {


private int a ;
private int b ;
private int c ;
private int countAboveLine = 0 ;
private int countBollowLine = 0 ;
private int countOnLine = 0 ;
private Point extreme_points[] ;
private Point pointsOnSameLine[] ;




public Point[] calculate_ConvexHull( Point p[] )
{


    pointsOnSameLine = new Point[p.length] ;
    extreme_points = new Point[2 * p.length] ;
    int extreme_array_index = 0 ; 
    int pointsOnline_array_index = 0 ;
    boolean pointPairsNotInExtremelPointSet = true ;
    int k = 0 ;


     for( int i = 0 ; i < p.length ; i++ )
       for( int j = i+1 ; j < p.length ; j++ )
       {
                  
        a = p[j].get_y() - p[i].get_y() ;

        b = p[i].get_x() - p[j].get_x() ; 
        
        c = p[i].get_x() * p[j].get_y() - p[i].get_y() * p[j].get_x() ;
 
       
        countAboveLine = 0 ;
        countBollowLine = 0 ;
        countOnLine = 0 ;       
        pointsOnline_array_index = 0 ;
        k = 0 ;
       //pointsOnSameLine = new Point[p.length] ;

        while( k < p.length )
        {

          if( a*p[k].get_x() + b*p[k].get_y() - c > 0 )
          countAboveLine++ ;
  
          if( a*p[k].get_x() + b*p[k].get_y() - c < 0 )
          countBollowLine++ ;

          if( a*p[k].get_x() + b*p[k].get_y() - c == 0 )
          {
            pointsOnSameLine[pointsOnline_array_index] = p[k] ;
            pointsOnline_array_index++ ;
            countOnLine++ ;
          }

          
          if( countAboveLine != 0 && countBollowLine != 0 )
          break ;

  
          k++ ;      
 

        }  






          if( countOnLine > 2 && k == p.length )
          {

           Point holdMaxPoint = pointsOnSameLine[0]   ;
           Point holdMinPoint = pointsOnSameLine[0]   ;            
           
           System.out.println( "" + pointsOnline_array_index + "   " + pointsOnSameLine.length ) ;
           for( int n = 1 ; n < pointsOnline_array_index ; n++ )
           {

           if( pointsOnSameLine[n].get_x() > holdMaxPoint.get_x() || ( pointsOnSameLine[n].get_x() == holdMaxPoint.get_x() && pointsOnSameLine[n].get_y() > holdMaxPoint.get_y() ) )
           holdMaxPoint = pointsOnSameLine[n] ;
           
           if( pointsOnSameLine[n].get_x() < holdMinPoint.get_x() || ( pointsOnSameLine[n].get_x() == holdMinPoint.get_x() && pointsOnSameLine[n].get_y() < holdMinPoint.get_y() ) )
           holdMinPoint = pointsOnSameLine[n] ;

           
           }

           
           pointPairsNotInExtremelPointSet = true ;
           
           for( int n = 0 ; n < extreme_points.length ; n += 2 )
               if( extreme_points[n] == holdMinPoint && extreme_points[n+1] == holdMaxPoint )
               {
                   pointPairsNotInExtremelPointSet = false ;
                   break;
               }
                   
                   
           if( pointPairsNotInExtremelPointSet == true )
           {
           extreme_points[ extreme_array_index ] = holdMinPoint ;
           extreme_points[ extreme_array_index + 1 ] = holdMaxPoint ;        
           extreme_array_index += 2 ;
           }
           
           

          }          

 

          if( k == p.length && countOnLine == 2 )
          {
           extreme_points[ extreme_array_index ] = p[i] ;
           extreme_points[ extreme_array_index + 1 ] = p[j] ;         
           extreme_array_index += 2 ;
          }

          pointsOnSameLine = null ;
          pointsOnSameLine = new Point[p.length] ;

          
       }

    
       
       int max_index = extreme_points.length ; 

       for( int i = 0 ; i < extreme_points.length ; i++ )
       if( extreme_points[i] == null )
       {  
           max_index = i ;
           break ;
       }
       
       if( max_index == extreme_points.length )
           return extreme_points ;
       
       Point copy_extreme_points[] = new Point[max_index] ;
       
       for( int i = 0 ; i < max_index ; i++ )
       copy_extreme_points[i] = extreme_points[i] ;
       
       
       return copy_extreme_points ; 




}



//For main for testing if function are correct
//Dont uses only for debugging purposes
/*
public static void main( String args[] )
{
    
    ConvexHull c = new ConvexHull() ;
    Point array[] = new Point[4] ;
    
    array[0] = new Point( 0,0 ) ;
    array[1] = new Point( 1,1 ) ;
    array[2] = new Point( 1,2 ) ;
    array[3] = new Point( 1,3 ) ;
   // array[4] = new Point( 2,3 ) ;
   // array[5] = new Point( 3,3 ) ;
   // array[6] = new Point( 3,6 ) ;
   // array[7] = new Point( 3,7 ) ;
   // array[8] = new Point( 4,8 ) ;
    
    
   Point resultsarray[] = c.calculate_ConvexHull(array) ;
   
   for( int i = 0 ; i < resultsarray.length ; i++ )
       System.out.println( "Coordinates are " + resultsarray[i].get_x() + "  " + resultsarray[i].get_y() ) ;
    
    System.exit( 0 ) ;
    
    
}

*/






}




