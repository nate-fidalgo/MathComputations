import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/***
 *     __       __  __   ______   ___   ___   ______    ______   __           ___   __    __  __   ___ __ __    _______   ______   ______    ______      
 *    /_/\     /_/\/_/\ /_____/\ /__/\ /__/\ /_____/\  /_____/\ /_/\         /__/\ /__/\ /_/\/_/\ /__//_//_/\ /_______/\ /_____/\ /_____/\  /_____/\     
 *    \:\ \    \ \ \ \ \\:::__\/ \::\ \\  \ \\:::_ \ \ \::::_\/_\:\ \        \::\_\\  \ \\:\ \:\ \\::\| \| \ \\::: _  \ \\::::_\/_\:::_ \ \ \::::_\/_    
 *     \:\ \    \:\_\ \ \\:\ \  __\::\/_\ .\ \\:(_) ) )_\:\/___/\\:\ \        \:. `-\  \ \\:\ \:\ \\:.      \ \\::(_)  \/_\:\/___/\\:(_) ) )_\:\/___/\   
 *      \:\ \____\::::_\/ \:\ \/_/\\:: ___::\ \\: __ `\ \\::___\/_\:\ \____    \:. _    \ \\:\ \:\ \\:.\-/\  \ \\::  _  \ \\::___\/_\: __ `\ \\_::._\:\  
 *       \:\/___/\ \::\ \  \:\_\ \ \\: \ \\::\ \\ \ `\ \ \\:\____/\\:\/___/\    \. \`-\  \ \\:\_\:\ \\. \  \  \ \\::(_)  \ \\:\____/\\ \ `\ \ \ /____\:\ 
 *        \_____\/  \__\/   \_____\/ \__\/ \::\/ \_\/ \_\/ \_____\/ \_____\/     \__\/ \__\/ \_____\/ \__\/ \__\/ \_______\/ \_____\/ \_\/ \_\/ \_____\/ 
 * 
 *                                                                                                                                                       
 *     
 *  
 *			Very neat stuff very basic to understand pretty much take any number 
 *			n = 1435 and its reverse rn = 5341  add them together keep doing it until you 
 *          reach a palindrome number aka a number that reads the same from left to right
 *          and right to left. It seems most numbers eventually get to a palindrome number.
 *          However the amazing 196 gives some trouble.
 *          
 *          It is not known if lychrel numbers exist? These would be the numbers that dont get to a palindrome number.
 *          
 *          Note the code is very inefficient and all make improvements and optimize it at a later date
 *                                                                                                                                                               
 *                                                                                                                                                                
 *                                                                                                                                                       
 *
 *                                                                                                                                                       
 *                                                                                                                                                       
 *
 *
 */


public class Lychrel {

	public static String currentLychrel[] = null ;
	
	static
	{
	
        DumpWork wk = new DumpWork() ;
	 // register Message as shutdown hook
        Runtime.getRuntime().addShutdownHook(wk);
		
	}
	
	
	public static String[] searchLychrel( String n )
	{
		BigInteger answ = null ;
		String answs = null ;
		int ncycle = 0 ;
		StringBuffer n1 = new StringBuffer(n) ;
		if( notPalindrome(n) == false ) 
			return new String[] { n  , "" + ncycle } ;
		
		String n2 = n1.toString() ;
		n1.reverse() ;
		ncycle++ ;
		BigInteger b1 = new BigInteger( n1.toString() ) ;
		BigInteger b2 = new BigInteger( n2 ) ;
		
		answ = b1.add(b2) ;
		answs = answ.toString() ;
		

		while( notPalindrome(answs) == true ) 
		{
			currentLychrel = new String[] { answs , "" + ncycle , n } ;
			n1 = new StringBuffer(answs) ;
			b1 = new BigInteger( answs ) ;
			n1.reverse() ;
			
			b2 = new BigInteger( n1.toString() ) ;
			answ = b1.add(b2) ;
			answs = answ.toString() ;
			ncycle++ ;
				
		}
		
		currentLychrel = new String[] { answs , "" + ncycle , n } ;
		return new String[] { answs , "" + ncycle } ;
		
	}
	
	private static boolean notPalindrome(String answs) {
		// TODO Auto-generated method stub
	
		StringBuffer n1 = new StringBuffer(answs) ;
		n1.reverse() ;
		String n2 = n1.toString() ;
		if( answs.equals(n2) != true )
			return true ;
		
		return false;
		
	}

	
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		while( true )
		{
			
	        // Enter data using BufferReader
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("Enter a number to test weather it is Lychrel");
	       // Reading data using readLine
	    String Lnumber = reader.readLine();
			

		String ans[] =  Lychrel.searchLychrel( Lnumber ); 
		
		System.out.println( "For Number = " + Lnumber ) ;
		System.out.println( "Answer = " + ans[0]) ;
		System.out.println( "Cycles taken = " + ans[1]) ;
		
		}
		
		
	}

}
