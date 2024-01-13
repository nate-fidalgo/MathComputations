import java.util.Vector;

/***
 *     $$$$$$\                                 $$\                $$$$$$\            $$\   $$\     $$\                                $$\     $$\                 
 *    $$  __$$\                                $$ |              $$  __$$\           \__|  $$ |    $$ |                               $$ |    \__|                
 *    $$ /  \__| $$$$$$\  $$\   $$\  $$$$$$\ $$$$$$\    $$$$$$\  $$ /  $$ | $$$$$$\  $$\ $$$$$$\   $$$$$$$\  $$$$$$\$$$$\   $$$$$$\ $$$$$$\   $$\  $$$$$$$\       
 *    $$ |      $$  __$$\ $$ |  $$ |$$  __$$\\_$$  _|  $$  __$$\ $$$$$$$$ |$$  __$$\ $$ |\_$$  _|  $$  __$$\ $$  _$$  _$$\ $$  __$$\\_$$  _|  $$ |$$  _____|      
 *    $$ |      $$ |  \__|$$ |  $$ |$$ /  $$ | $$ |    $$ /  $$ |$$  __$$ |$$ |  \__|$$ |  $$ |    $$ |  $$ |$$ / $$ / $$ |$$$$$$$$ | $$ |    $$ |$$ /            
 *    $$ |  $$\ $$ |      $$ |  $$ |$$ |  $$ | $$ |$$\ $$ |  $$ |$$ |  $$ |$$ |      $$ |  $$ |$$\ $$ |  $$ |$$ | $$ | $$ |$$   ____| $$ |$$\ $$ |$$ |            
 *    \$$$$$$  |$$ |      \$$$$$$$ |$$$$$$$  | \$$$$  |\$$$$$$  |$$ |  $$ |$$ |      $$ |  \$$$$  |$$ |  $$ |$$ | $$ | $$ |\$$$$$$$\  \$$$$  |$$ |\$$$$$$$\       
 *     \______/ \__|       \____$$ |$$  ____/   \____/  \______/ \__|  \__|\__|      \__|   \____/ \__|  \__|\__| \__| \__| \_______|  \____/ \__| \_______|      
 *                        $$\   $$ |$$ |                                                                                                                          
 *                        \$$$$$$  |$$ |                                                                                                                          
 *                         \______/ \__|                                                                                                                          
 *
 *
 *
 *
 *		Really neat recreational math problem
 *      Though i dont believe anybody truely knows who created/invented this they became popular in the 1800's
 *      But seem to have died down as being popular. However this puzzle are not only recreational
 *      It give professionals insight or can enumerate certain forms of a number.
 *      
 *      For example if you looking for all the number expressions that have a certain structure
 *      Like NN+NM = ME
 *
 *		This program will spit out all possible base 10 solutions to this form.
 *      Puzzle Chars=> NME 
 *  	33+37 = 70
 *  	11+12 = 23
 *  	22+24 = 46
 *  	44+49 = 93
 *  	33+36 = 69
 *  
 *      So one can search for and enumerate patterns in numbers using this neat recreational math
 *      puzzle solver. Great for using in the area of Arithmetic dynamics‎ 
 *
 *
 *
 */


public class CryptoASolver {

	private StringBuilder puzzlechars ;
	private final String capuzzle;
	private static Vector<String> possiblesolutions = null ;
	private static int elements[] = new int[] {0,1,2,3,4,5,6,7,8,9} ;
	
	//Initializes the puzzle solver
	public CryptoASolver(String capuzzle)
	{
		this.capuzzle = capuzzle ;
		puzzlechars = new StringBuilder() ;
		buildpuzzle( capuzzle ) ;
		if( puzzlechars.length() > 10)
		System.out.println("Puzzle error to many symbols for capuzzle" );
			//then its not a valid puzzle to many characters for only 10 digits in decimal system
		System.out.println("Puzzle Chars=> " + puzzlechars);

		
	}
	
	//This method just parses the inputted puzzle collecting all the distinct letters to
	//figure out
	private void buildpuzzle(String capuzzle)
	{
		for( int i = 0 ; i < capuzzle.length() ; i++ )
		{
			char c = capuzzle.charAt(i) ;
			if( Character.isLetter(c) == true &&  isNotInPuzzleString(c) == true )
			{
				puzzlechars.append(c) ;
			}
		}
			
	}
	
	//helper function to compute how much space for vector size to store all
	//the k-perm of 10.
	private int factorial(int n) {
	    int fact = 1;
	    for (int i = 2; i <= n; i++) {
	        fact = fact * i;
	    }
	    return fact;
	}
	
	//solves the puzzle by brute force testing all possibilities
	//note most distinct letters in these types of puzzles for base 10 number system is
	//10! which is doable in a reasonable short time for a compute to check thru
	//however the problem generalized to any base number systems is known to be NP problem.
	public void solve()
	{
		if( puzzlechars.length() > 10)
		return ;
			
		int capacity = factorial(elements.length) / factorial(elements.length - puzzlechars.length()) ;
		possiblesolutions = new Vector<String>(capacity) ;
		
		computeEnumerations( elements.length , puzzlechars.length()) ;
		String solutionstr = null ;
		
		for( int i = 0 ; i < possiblesolutions.size() ; i++ )
		{
			solutionstr = possiblesolutions.elementAt(i) ;
		
		    String solution = isvalidSolution(solutionstr) ;
		
		    if(solution != null )
		       System.out.println( solution ) ;

		}
		
	}
	

    //helper function to properly get the expression ready for computeMath function
	//which computes the actual mathematical expression
	private String isvalidSolution(String pdigits) {
		// TODO Auto-generated method stub
		String mathexpression =  capuzzle ;
		int i = 0 ;
		for( int j = 0 ; j < capuzzle.length() ; j++ )
		{
			if( i >= puzzlechars.length() )
				break ;
			if( puzzlechars.charAt(i) == capuzzle.charAt(j) )
			{
				mathexpression = mathexpression.replace(puzzlechars.charAt(i), pdigits.charAt(i)) ;
			    i++ ;
			}
			
		}
      
		if( computeMath(mathexpression) == true )
			return  mathexpression ;
			
		return null;
	}

	
	//This method can be modified and generalized to incorporate any type of cryptoarithmetic expression
	//This method compute the mathematical expression for the string input it receives 
	//Note currently this method only computes expressions of the form word+word+...+word = word
	//however one can easily switch the internals of this function with his own to compute any type.
	//Of cryptoarithemtic expression.
	//All one need to do is provide his evaluation method for the internals of this function
	//and then return true if his mexpr string is valid or false if the expression is invalid
	private boolean computeMath(String mexpr )
	{
		String eval[] = mexpr.split("\\=") ;
		String leftexpr[] = eval[0].split("\\+") ;
		String rightexpr = eval[1].trim() ;
		
		int l[] = new int[leftexpr.length] ;
		for( int i = 0 ; i < leftexpr.length ; i++ )
				l[i] = Integer.parseInt(leftexpr[i].trim() ) ;
		
		int r = Integer.parseInt(rightexpr) ;
		
		int sum = 0 ;
		for( int i = 0 ; i < l.length ; i++ )
		    sum += l[i] ;
			
		if( sum == r )
		{
			return true ;
		}
		
		
		return false ;
	}
	
	
	private boolean isNotInPuzzleString(char c) {
		// TODO Auto-generated method stub
		for( int i = 0 ;  i < puzzlechars.length() ; i++ )
		{
			if( puzzlechars.charAt(i) == c )
			return false ;
		}
		return true;
	}

	//Just calls the k_perm_of_n(...) method to compute the k-perm of n
	//Realistically n will always be 10 but in future i may provide different numbering systems
	//less then base 10 like the traditional binary 2, and oct 8 of bases under 10 it will be 
	//even easier for a computer to evaluate in a reasonable time less then 10!
	private void computeEnumerations(int n , int k) {
		// TODO Auto-generated method stub
		//enumeration( elements.length, elements,  elements.length - puzzlechars.length() ) ;
		k_perm_of_n(k,n) ;
	}
	
	
	//print all k permutations of 1,...,n
	private void k_perm_of_n(int k,int n){

		int a[] = new int[k] ;
		for( int i = 0 ; i < k ; i++ )
		{
			a[i] = i+1 ;
		}
		
	    do{
	        //for simplicity we just copy A to B as generate() may modify the input array
	        //alternatively you may rotate it back if A.size() is even
	        int b[] = a.clone();
	    	//i also shift down one so my starting display number is 0 not 1
	        for( int i = 0 ; i < b.length ; i++ )
	    		b[i] = b[i] - 1 ;
	        enumeration(k,b);//prints all permutations of this combination
	    }while(next_combination(a,n));//move to next combination
	}
	
	
	  //a is a k-combination of 1,...,n
	  //generates the next larger k-combination after 'a' according to lexicographic order (by mutating 'a')
	  //, e.g. {1,3,4} is larger than {1,2,4} compare from left to right entry-wise
	  //returns false when 'a' is already the largest combination, else returns true (and 'a' will be mutated)
	  private boolean next_combination(int[] a, int n) {
	      int k = a.length;
	      for (int i = k - 1; i >= 0; i--) {
	          if (a[i] < n - k + i + 1) {
	              a[i]++;
	              for (int j = i + 1; j < k; j++)
	                  a[j] = a[j - 1] + 1;
	             // print(a) ;
	              return true;
	          }
	      }
	      //print(a) ;
	      return false;
	  }
	
	
	//heaps algorithm for enumerating all possible permutations of the elements array
	//DONT BE CONFUSED ABOUT HEAP FROM COMPUTER SCIENCE AS ITS NOT ANYTHING TO DO WITH
	//THE HEAP STRUCTURE OF MEMORY. JUST NAMED AFTER THE GUY WHO INVENTED THIS ALGORITHM IN THE 60's
	private static void enumeration( int n, int[] elements) {

			    if(n == 1) {
			        enumerate(elements);
			    } else {
			        for(int i = 0; i < n-1; i++) {
			            enumeration(n - 1, elements);
			            if(n % 2 == 0) {
			                swap(elements, i, n-1);
			            } else {
			                swap(elements, 0, n-1);
			            }
			        }
			        enumeration(n - 1, elements);
			    }
			}
	
	//enumeration helper function to swap objects in the elements array
	private static void swap(int[] elements, int a, int b) {
	    int tmp = elements[a];
	    elements[a] = elements[b];
	    elements[b] = tmp;
	}

	//enumerate method is just the method that add the k-perm to the possiblesolutions vector
	//Vector possiblesolutions eventually contains all the possibilities to check
	//then its just a matter of solve() going  thru a for loop of possiblesolutions.length 
	//testing every single element in that vector for a solution
    private static void enumerate(int[] elements) {
	   
    	StringBuilder str = new StringBuilder() ;
    	for( int i = 0 ; i < elements.length ; i++ )
    	  str.append(elements[i]) ;
		   
    	String selement =  str.toString() ;
    	possiblesolutions.addElement(selement);
    		    	
	}
	
    

    
  
    //Test main to test out some neat alphmetics puzzles 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//CryptoASolver cas = new CryptoASolver("SEND + MORE = MONEY") ;
		CryptoASolver cas = new CryptoASolver("NN+NM = ME") ;
		
		
		//This is the 41character famous puzzle of the 1980's that is a neat one to test against
		//pretty quickly solved to within reason!
		//CryptoASolver cas = new CryptoASolver("SO+MANY+MORE+MEN+SEEM+TO+SAY+THAT+THEY+MAY+SOON+TRY+TO+STAY+AT+HOME+SO+AS+TO+SEE+OR+HEAR+THE+SAME+ONE+MAN+TRY+TO+MEET+THE+TEAM+ON+THE+MOON+AS+HE+HAS+AT+THE+OTHER+TEN=TESTS") ;
		
		cas.solve() ;
		
		System.exit(0);
		
	}

}
