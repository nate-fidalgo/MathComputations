import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.* ;

public class AnalysisPartition {

private static final String FILEPATHNAME = "partitions.dmp" ;

public static void main(String args[] )
{
Vector<String> results = new Vector<String>() ;

	try {
	      File pfile = new File(FILEPATHNAME);
	      Scanner preader = new Scanner(pfile);
		      
	      String data = null ;
	      String parray[] = null ;
              int iparray[] = null ;
	      while (preader.hasNextLine()) {
	        data = preader.nextLine();
                parray = data.split("\\+") ;
                iparray = new int[parray.length] ;
                for(int i=0; i< iparray.length ; i++) 
                iparray[i] = Integer.parseInt(parray[i].trim());
		        
                 if( Partition.is_distinct_partition( iparray ) == true ) // && Partition.is_even_parts_partition( iparray ) == true )
	         {
                   System.out.println(data);
                   results.addElement( data ) ;
                 }

	         }
	         preader.close();
		      
		      			      
	         } catch (FileNotFoundException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		} 
	


System.out.println("######################################################################" ) ;

removeduplicatePartitions( results ) ;
printPartitions( results ) ;
System.exit(0) ;

}








//This method prints the filtered partitions you want to the screen 
public static void printPartitions( Vector<String> results )
{

//print the final partition type results
for( int i = 0 ; i < results.size() ; i++ )
System.out.println( results.get(i) ) ;
return ;

}

//Method to remove duplicate partitions 
//Calls on arePermutation(...) as a helper function to do this!
public static void removeduplicatePartitions( Vector<String> results )
{

for( int i = 0 ; i < results.size() ; i++ )
{
for( int j = i+1 ; j < results.size() ; j++ )
{

if( arePermutation( results.get(i) , results.get(j) ) == true ) // && (results.get(i).length() == results.get(j).length()) )
{
//System.out.println("------- i " + i + "-------j " + j + "  -- " + results.get(j)) ;
results.remove(j) ;
i=0;
j=0;
}

}

}

return ;
}


//Returns true if the two partitions of a given number are the same partition up to permutations
//False if not a permutation , True if a permutation of the parition 
public static boolean arePermutation( String p1 , String p2 )
{
String tmp[] = null ;
tmp = p1.split("\\+") ;
Set<String> tmpset1 = new HashSet<String>() ;

for( int i = 0 ; i < tmp.length ; i++ )
tmpset1.add(tmp[i].trim()) ;

tmp = p2.split("\\+") ;
Set<String> tmpset2 = new HashSet<String>() ;

for( int i = 0 ; i < tmp.length ; i++ )
tmpset2.add(tmp[i].trim()) ;

if( tmpset1.equals(tmpset2) == true &&  (p1.length() == p2.length()) )
return true ;

return false ;

}



}
