import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class AnagramSolver {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

	      File englishwords = new File(args[0]); // filename is based as arg[0] thru main method
	      RandomAccessFile dictreader = new RandomAccessFile(englishwords, "r");
	      RandomAccessFile dictreader2 = new RandomAccessFile(englishwords, "r");
	      String word = null ;
	      String word2 = null ;
	     
	      System.out.println("Starting AnagramSolver...") ;
	      	      
			while ((word = dictreader.readLine()) != null) {
			//	System.out.println(word + "$$$$$4");
			
				while ((word2 = dictreader2.readLine()) != null) {
			//		System.out.println(word2);

	    		if( areAnagram(word.toCharArray() , word2.toCharArray() ) == true && word.equals(word2) == false )
	    		{
	    			System.out.println( "Anagrams " + word + " --- " + word2 ) ;
	    		}


	      }
	      
				dictreader2.seek(0);
				
		  }
			
	      System.out.println("finished") ;
	      dictreader.close();
	      dictreader2.close();

	}
	
	 /* function to check whether two strings are
    anagram of each other */
	//This code i have been told is written by/contributed by Nikita Tiwari
	//time complexity is O(nlogn) because of the time it takes to quicksort in this algorithm.
	//There is a more efficient O(n) algorithm if one sacrifices size using a count letter array
	//and incrementing/decrementing the count of the letters.
	//But i use this less efficient but more intuitive great code that runs a little slower
	//But has no restrictions on the size of the words in dictionary being considered!
    static boolean areAnagram(char[] str1, char[] str2)
    {
        // Get length of both strings
        int n1 = str1.length;
        int n2 = str2.length;
  
        // If length of both strings is not same,
        // then they cannot be anagram
        if (n1 != n2)
            return false;
  
        // Sort both strings
        Arrays.sort(str1);
        Arrays.sort(str2);
  
        // Compare sorted strings
        for (int i = 0; i < n1; i++)
            if (str1[i] != str2[i])
                return false;
  
        return true;
    }
	
	
}