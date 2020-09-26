import java.io.File;
import java.util.Scanner;

/**
 * A simple program to determine letter frequency of the English language / English dictionary and other language statistics
 * Use any way you want.
 * 
 * @author nate
 *
 */
public class DictionaryAnalyzer {

	/**
	 * Important fields that DictionaryAnalyzer Uses
	 */
	private static int wordswithletter[] = new int[100] ;
	private static int vowelcountinword[] = { 0 ,0 ,0 ,0 ,0 ,0 } ;
	private static enum vowels {a,e,i,o,u,y} ;
	private static int lettercount[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 } ;
	private static enum alphebet { a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z} ;
	
	/**
	 * 
	 * @param args takes one argument args[0] = the path/filename of the dictionary file to analyzes 
	 */
	public static void main(String[] args) {
		
		 int countpdromes = 0 ;
		 String longestword = "" ;
		 int totaldictionarywords = 0 ;
		 
		  try {
	 
	      File englishwords = new File(args[0]); // filename is based as arg[0] thru main method
	      Scanner dictreader = new Scanner(englishwords);
	      StringBuffer data = new StringBuffer() ;
	      String word = null ;
	      String reverseword = null ;
	     
	      while (dictreader.hasNextLine()) {
	    	word =  dictreader.nextLine() ; 
	        data.append( word );
	        reverseword = data.reverse().toString() ;
	        if( reverseword.equals(word) == true )
	        {
	        //	System.out.println( word );
	            countpdromes++ ;
	        }

	        if( word.length() > longestword.length())
	        {
	        	longestword = word ;
	        }
	        
	        vowelcountinword[vowels.a.ordinal()] += (lettercnt( word ,  'a' ) != 0 ) ? 1: 0 ;
	        vowelcountinword[vowels.e.ordinal()] += (lettercnt( word ,  'e' ) != 0 ) ? 1: 0 ;
	        vowelcountinword[vowels.i.ordinal()] += (lettercnt( word ,  'i' ) != 0 ) ? 1: 0 ;
	        vowelcountinword[vowels.o.ordinal()] += (lettercnt( word ,  'o' ) != 0 ) ? 1: 0 ;
	        vowelcountinword[vowels.u.ordinal()] += (lettercnt( word ,  'u' ) != 0 ) ? 1: 0 ;
	        vowelcountinword[vowels.y.ordinal()] += (lettercnt( word ,  'y' ) != 0 ) ? 1: 0 ;
	        
	        lettercount[alphebet.a.ordinal()] += (lettercnt( word ,  'a' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.b.ordinal()] += (lettercnt( word ,  'b' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.c.ordinal()] += (lettercnt( word ,  'c' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.d.ordinal()] += (lettercnt( word ,  'd' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.e.ordinal()] += (lettercnt( word ,  'e' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.f.ordinal()] += (lettercnt( word ,  'f' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.g.ordinal()] += (lettercnt( word ,  'g' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.h.ordinal()] += (lettercnt( word ,  'h' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.i.ordinal()] += (lettercnt( word ,  'i' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.j.ordinal()] += (lettercnt( word ,  'j' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.k.ordinal()] += (lettercnt( word ,  'k' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.l.ordinal()] += (lettercnt( word ,  'l' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.m.ordinal()] += (lettercnt( word ,  'm' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.n.ordinal()] += (lettercnt( word ,  'n' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.o.ordinal()] += (lettercnt( word ,  'o' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.p.ordinal()] += (lettercnt( word ,  'p' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.q.ordinal()] += (lettercnt( word ,  'q' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.r.ordinal()] += (lettercnt( word ,  'r' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.s.ordinal()] += (lettercnt( word ,  's' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.t.ordinal()] += (lettercnt( word ,  't' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.u.ordinal()] += (lettercnt( word ,  'u' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.v.ordinal()] += (lettercnt( word ,  'v' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.w.ordinal()] += (lettercnt( word ,  'w' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.x.ordinal()] += (lettercnt( word ,  'x' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.y.ordinal()] += (lettercnt( word ,  'y' ) != 0 ) ? 1: 0 ;
	        lettercount[alphebet.z.ordinal()] += (lettercnt( word ,  'z' ) != 0 ) ? 1: 0 ;
	        
	        updatewords( letterCnt( word ) ) ;
	        
	        data = data.delete(0, data.length() ) ;
	        totaldictionarywords++ ;
	        
	      }
	      dictreader.close();
		
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
			  System.out.println("Error in processing") ;
			  System.exit(0) ;
		  }
		  
		  System.out.println("Finished ") ;
		  System.out.println("Palindrome count " + countpdromes ) ;
		  System.out.println("Longest dictionary word is " + longestword + " letter/character count = " + longestword.length()) ;
		  
		  System.out.println("Count of words with a given number of letters " ) ;
		  for( int i = 0 ; i < wordswithletter.length ; i++ )
		  {
			  System.out.println( wordswithletter[i] + " words with " + (i+1) + " letters out of total " + totaldictionarywords + "  percentage = " + (double)wordswithletter[i]/(double)totaldictionarywords * 100 ) ;
		  }
		  
		  System.out.println( "Vowel analysis count: " ) ;
		  System.out.println("----------distinct word count containing a given vowel in dictionary" ) ;
		  System.out.println( "Vowel a : " + vowelcountinword[vowels.a.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.a.ordinal()]/(double)totaldictionarywords * 100) ;
		  System.out.println( "Vowel e : " + vowelcountinword[vowels.e.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.e.ordinal()]/(double)totaldictionarywords * 100) ;
		  System.out.println( "Vowel i : " + vowelcountinword[vowels.i.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.i.ordinal()]/(double)totaldictionarywords * 100) ;
		  System.out.println( "Vowel o : " + vowelcountinword[vowels.o.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.o.ordinal()]/(double)totaldictionarywords * 100) ;
		  System.out.println( "Vowel u : " + vowelcountinword[vowels.u.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.u.ordinal()]/(double)totaldictionarywords * 100) ;
		  System.out.println( "Vowel y : " + vowelcountinword[vowels.y.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.y.ordinal()]/(double)totaldictionarywords * 100) ;

		  System.out.println() ;
		  System.out.println( "Letter analysis count: ") ;
		  System.out.println( "-----------distinct word count containing a given letter in dictionary ") ;
		  System.out.println( "Letter a : " + lettercount[alphebet.a.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.a.ordinal()]/(double)totaldictionarywords * 100 );
		  System.out.println( "Letter b : " + lettercount[alphebet.b.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.b.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter c : " + lettercount[alphebet.c.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.c.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter d : " + lettercount[alphebet.d.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.d.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter e : " + lettercount[alphebet.e.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.e.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter f : " + lettercount[alphebet.f.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.f.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter g : " + lettercount[alphebet.g.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.g.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter h : " + lettercount[alphebet.h.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.h.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter i : " + lettercount[alphebet.i.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.i.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter j : " + lettercount[alphebet.j.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.j.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter k : " + lettercount[alphebet.k.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.k.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter l : " + lettercount[alphebet.l.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.l.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter m : " + lettercount[alphebet.m.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.m.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter n : " + lettercount[alphebet.n.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.n.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter o : " + lettercount[alphebet.o.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.o.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter p : " + lettercount[alphebet.p.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.p.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter q : " + lettercount[alphebet.q.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.q.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter r : " + lettercount[alphebet.r.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.r.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter s : " + lettercount[alphebet.s.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.s.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter t : " + lettercount[alphebet.t.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.t.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter u : " + lettercount[alphebet.u.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.u.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter v : " + lettercount[alphebet.v.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.v.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter w : " + lettercount[alphebet.w.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.w.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter x : " + lettercount[alphebet.x.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.x.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter y : " + lettercount[alphebet.y.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.y.ordinal()]/(double)totaldictionarywords * 100);
		  System.out.println( "Letter z : " + lettercount[alphebet.z.ordinal()] + " words, percentage = " + (double)lettercount[alphebet.z.ordinal()]/(double)totaldictionarywords * 100);
		  System.exit(0) ;
		  
	}
	
	/**
	 * Simple method to get the size of the word aka number of letters the word has
	 * @param word A String representing the word
	 * @return an integer size of word
	 */
	public static int letterCnt( String word )
	{
		return word.length();
		
	}
	
	/**
	 * Method counts the number of times a given character occurs in the word 
	 * @param word A String representation of the word
	 * @param c A char datatype of the letter count
	 * @return A integer that is the number of occurences of the char c letter
	 */
	public static int lettercnt( String word ,  char c )
	{
		int vcnt = 0 ;
		for( int i = 0 ; i < word.length() ; i++ )
		{
			if( word.charAt(i) == c )
			{
				vcnt++ ;
			}
		}
		
		return vcnt ;
	}
	
	
	/**
	 * Used to increment the number of words of length numofletters
	 * @param numofletters the number of letters in the word
	 */
	public static void updatewords( int numofletters )
	{
		if( numofletters >  wordswithletter.length )
			return ;
		
		 wordswithletter[numofletters-1]++ ;
		 
	}

}
