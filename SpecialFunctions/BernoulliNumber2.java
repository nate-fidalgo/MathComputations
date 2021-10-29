import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mariadb.jdbc.MySQLDataSource;

public class BernoulliNumber2 {
	//look in databasetesting folder
	private Connection dbcon = null ;
	
	public BernoulliNumber2()
	{
		
		MySQLDataSource mydb = new MySQLDataSource( "localhost" , 3306 , "BernoulliDB" ) ;
		Connection con = null ;
		
		try {
			mydb.setUser("nate2") ;
			mydb.setPassword("fidalgo");
			con = mydb.getConnection() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		if( con == null )
		{
		System.out.println( "Connection failed" ) ;
		System.exit(0) ;
		}

		System.out.println( "Connection Success" ) ;
		dbcon = con ;
		
		
	}
	
	/*
	 * Computes n choose s aka the number of combinations on n object choosing s of them.
	 * This function is kind of brute force ish and a better method using properties of the
	 * Pascal triangle can be used which would be a bigger performance speed up then the naive way
	 * When i get around to it all implement the most efficient means to compute using pascals triangle.
	 * With dynamic programming aka storing in a database and looking up previous computed values
	 * 
	 */
	public BigRational C(BigInteger n , BigInteger s )
	{
		
		if( s.compareTo(BigInteger.ZERO) == 0 || s.compareTo(n) == 0 )
			return new BigRational( BigInteger.ONE , BigInteger.ONE ) ;
		
		try {
	    PreparedStatement pstm =	dbcon.prepareStatement("Select c_n_s from Ctable where n=? and s=?") ;
	    pstm.setString(1, n.toString() );
	    pstm.setString(2, s.toString() );
	    
	    ResultSet rset = pstm.executeQuery();
	    
	    if( rset != null )
	    {
	    	rset.next() ;
	    	return new BigRational( new BigInteger( rset.getString(1) ) , BigInteger.ONE ) ;
	    }
	    
		}
		catch( SQLException e)
		{
			 System.out.println("..........  ") ;
			
		}
		
		
		BigInteger result = BigInteger.ONE ;
	    BigInteger i = n.subtract(s) ;
	    BigInteger j = n ;
		for( ; j.compareTo(i) != 0 ; )
		{
			result = result.multiply(j) ;
			j = j.subtract(BigInteger.ONE) ;
						
		}
			
		j=BigInteger.ONE ;
		i=s ;
		BigInteger result2 = BigInteger.ONE ;
		for( ; j.compareTo(i) != 0 ;  )
		{
			result2 = result2.multiply(i) ;
			i = i.subtract(BigInteger.ONE) ;		
		}
		
		BigRational bigrat = new BigRational( result.divide(result2) , BigInteger.ONE ) ;
		

	    try {
			PreparedStatement pstm =	dbcon.prepareStatement("INSERT INTO Ctable ( n, s, c_n_s ) VALUES ( ?, ?, ? )") ;
		    pstm.setString(1, n.toString() );
			pstm.setString(2, s.toString() );
			pstm.setString(3, bigrat.toString() );
			pstm.executeUpdate() ;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		
	    
		return bigrat ;
			
	}
	
	/*
	 * This is the function that computes bernoulli numbers using the C(n,s) as a helper function
	 * This function can also be speed up using dynamic programming and databases to be able to compute the 
	 * next bernoulli number from the previous.... right now its just brute force ish and need to get
	 * optimized when i get the chances with C(n,s) function as well. At that point you can just hand out
	 * the database and any one can start to compute the next bernoulli prime... I might set up the nextBernoulli prime
	 * project similar how gimps is for primes of the form 2^n+1.
	 * 
	 * I say bernoulli numbers are just as important if not more important as they link summation and integration.
	 * As well as give the ability to compute all Zeta(2s) =... values one wants which are cool!
	 * 
	 * Neat stuff i wonder if ada lovelace had a crush on babbage but she definitely one of the first
	 * To compute bernoulli numbers on these old computer ish devices of charles babbage :)  
	 */
	public BigRational BernoulliNum( BigInteger n )
	{
		if( n.compareTo(BigInteger.ZERO) == 0 )
			return new BigRational( BigInteger.ONE , BigInteger.ONE ) ; 
		
		if( n.compareTo(BigInteger.ONE) == 0 )
			return new BigRational( new BigInteger("-1") , BigInteger.TWO ) ; 
		
		if( ( n.mod(BigInteger.TWO)).compareTo(BigInteger.ZERO) != 0 )
		    return new BigRational( BigInteger.ZERO , BigInteger.ONE ) ;
			
		    BigRational bn = checkBernoulliNum(n) ;
		
		if( bn != null )
			return bn ;
			
		    BigRational result = new BigRational(BigInteger.ZERO , BigInteger.ONE) ;
		    
		    BigInteger nnn = n.subtract(BigInteger.ONE) ;
		    BigInteger nnnn = n.add(BigInteger.ONE) ;
		    
			for( BigInteger i = BigInteger.ZERO ; i.compareTo(nnn) <= 0 ; i=i.add(BigInteger.ONE) )
			{
				result = result.add( C(nnnn,i).mult( BernoulliNum( i ) ) ) ;
			}

			result = result.mult( new BigRational(new BigInteger("-1") , BigInteger.ONE ) ) ;
			BigRational bfinal = result.div( C(nnnn,n) )  ;
			
			try {
			PreparedStatement pstm =	dbcon.prepareStatement("INSERT INTO Btable (i,B_i) VALUES (?,?)") ;
		    pstm.setString(1, n.toString() );
		    pstm.setString(2, bfinal.toString() );
		    
			int rset = pstm.executeUpdate();
			}
			catch( SQLException e )
			{
				
				System.out.println("Database Execute failed") ;
			}
		    
		    
			return bfinal ;	
				
	}
	
	
	private BigRational checkBernoulliNum(BigInteger n) {
		// TODO Auto-generated method stub
		
		
		try {
		    PreparedStatement pstm =	dbcon.prepareStatement("SELECT B_i FROM Btable WHERE i = ?") ;
		    System.out.println(n.toString() ) ;
		    pstm.setString(1, n.toString() );
		    ResultSet rset = pstm.executeQuery();
		    
		    if( rset == null )
		    	return null ;
		    
		    if( rset.next() == false )
		    	return null ;
		    			
		    String bi = rset.getString(1) ;
		    String res[] = bi.split("/") ;
		    return new BigRational( new BigInteger(res[0].trim()) , new BigInteger( res[1].trim() ) ) ;
		    
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		

		
		return null;
	}

	
	
	
	
	
	//Simple test main to show a bernoulli number thats not so easy to compute by hand
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BernoulliNumber2 bnum = new BernoulliNumber2() ;
		BigInteger i = new BigInteger( "10000" ) ;
		//BigInteger iii = new BigInteger( "1 ") ;
		System.out.println("The " + i + "th BernoulliNumber = " +  bnum. BernoulliNum( i ) ) ;
		System.out.println("Finished!!!") ;
		System.exit(0) ;
	}

}
