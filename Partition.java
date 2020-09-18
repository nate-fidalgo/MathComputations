
import java.util.*;


public class Partition {

private int n ; // the number to compute its partitions
private int a[] ; // the array to hold temporarily partitions in the computing process 
private int j ;  // internal variable used in partition function for computing partitions 
private int counter ; //internal variable that keeps track of how many partitions there are 

//Constructor that initialize the class to compute partitions of the number num
public Partition( int num )
{
a = new int[num] ;
n = num ;
counter = 0 ;
j = 0 ;
partition( n ) ;
}

//Method that computes all the partitions of a given number x
//Note does not include the number itself
private void partition( int x )
{
 
 j++ ;   

if( x == 1 ) 
{
    a[j-1] = 1 ;
    
    for( int i = 0 ; i < n ; i++ )
      if( a[i] != 0 )
      {
        System.out.print( " " + a[i] ) ;
        if( i != n - 1 && a[i+1] != 0 ) 
        System.out.print( " +" ) ;
      }
      else
          break;
    
    System.out.println() ;
    counter++ ;
   
}
else
{
  
  for( int i = 1 ; i < x ; i++ )
  {
    
     a[j-1] = x - i ;
    
     if( i != 1 )
     {   
         
         for( int k = 0 ; k < n ; k++ )
            if( a[k] != 0 )
            {    
              System.out.print( " " + a[k] ) ;
              if( k != n - 1 && a[k+1] != 0 ) 
              System.out.print( " +" ) ;
            }
            else
                break ;
   
    System.out.print( " + " + i ) ;
    System.out.println() ;
    counter++;
    
   }
   
    partition( i );
    a[j] = 0 ;
    
  }

}

 j-- ;

}


//returns the number of partition for the number
public int getNumberOfPartitions()
{
return counter ;
}

//returns the number that we want the partitions of
public int getNumberForPartitions()
{
return n ;
}

//##########################STATIC HELPER/UTILITY FUNCTION NOT REALLY NEED FOR COMPUTING PARTITIONS DIRECTLY ###########################################
//But are important to have around for other programs to analysis the partitions dumped to file from this program

//Returns the length of the partition from the array representation of the partition
public static int length_of_partition( int partition_array[] )
{
    
   int p_length = 0 ;
    
   for( int i = 0 ; i < partition_array.length ; i++ )
   { 
   if( partition_array[i] == 0 )
       break ;
   else
       p_length++ ; 
   }
   
   return p_length ;
   
}


//Method returns true if the partition of a number contains all distinct numbers no repeat numbers
//returns false otherwise
public static boolean is_distinct_partition( int partition_array[] )
{
    

  Set<Integer> duplicates = new HashSet<Integer>();
  for (int i : partition_array )
  {
    if (duplicates.contains(i)) return false;
    duplicates.add(i);
  }
  return true;
  
}


//Method returns true if the partition is an even partition meaning an even number of numbers making up the partition
//returns false otherwise 
public static boolean is_even_parts_partition( int partition_array[] )
{
    
   if( length_of_partition( partition_array ) % 2 == 0 )
       return true ;
   else
       return false ;
    
}


//Method returns true if the partition is an odd partition meaning an odd number of numbers making up the partition
//returns false otherwise 
public static boolean is_odd_parts_partition( int partition_array[] )
{
    
   if( length_of_partition( partition_array ) % 2 == 1 )
       return true ;
   else
       return false ;
    
}

//Method returns true if the partition contains k number of numbers making up the partition
//returns false otherwise 
public static boolean is_k_parts_partition( int partition_array[] , int k )
{
    
    if( length_of_partition( partition_array ) == k )
       return true ;
    else
       return false ;
    
}

//################################################################################################################################################################

//Simple test main to test all the functions are working properly
//DONT USE THIS MAIN ONLY FOR TESTING PURPOSE CREATE YOUR OWN CLASS TO USE THIS ONE!
public static void main( String args[] )
{

Partition p = new Partition( 20 ) ;
/*
System.out.println( p.getNumberOfPartitions() ) ;
System.out.println( p.getNumberForPartitions() ) ;

     int part[] = { 1 , 6 } ;
     System.out.println( Partition.is_k_parts_partition( part , 6 ) ) ;
     System.out.println( Partition.is_even_parts_partition( part  ) ) ;
     System.out.println( Partition.is_odd_parts_partition( part  ) ) ;
     System.out.println( Partition.is_distinct_partition( part ) ) ;
     System.out.println( "" + p.n + "  --- " + p.j + " ---- " ) ;
for( int i = 0 ; i < p.a.length ; i++ )
System.out.println( p.a[i] ) ;
*/
     System.exit(0);



}



}
