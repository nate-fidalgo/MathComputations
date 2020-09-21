import java.math.BigInteger;
import java.util.Scanner;
import java.util.* ;

public class Residue {
private static Set<BigInteger> rclass = new TreeSet<BigInteger>();
  public static void main(String[] args) {

System.out.println( "---Modular Residue Calculator---" );
//Note with this program you can display any residue class for any power.
//This is very powerful as one can look for patterns in higher reciprocity 
//And perhaps understand higher reciprocity laws better then just understanding quadratic reciprocity laws of gauss
//One may gain insight into computing more general reciprocity laws  like artins reciprocity ...etc
//I think its one of the most useful programs of mine interms of analysising patterns in modular arithmetic 
//Also the special case when the power = 1 is just standard modular arithmetic , power = 2 is quadratic reciprocity , power = 3 is cubic reciprocity and so on ...

Scanner in = new Scanner(System.in);

System.out.println( "Enter the modulus " ); 
String mod = in.nextLine();  ; 
  
System.out.println( "Enter the power " ); 
String p = in.nextLine();  

System.out.print("Residue table : [" ); 

residueclass(new BigInteger( p ),new BigInteger( mod ) ) ; 

     System.out.println( " ] " ) ;

System.out.println();
System.out.print("Residue set : [" ) ;
Iterator<BigInteger> irclass = rclass.iterator();
BigInteger residuecount = BigInteger.ZERO ;
while( irclass.hasNext() == true )
{
System.out.print( " " + irclass.next() ) ;
residuecount = residuecount.add( BigInteger.ONE ) ;
}

System.out.println( " ] " ) ;
System.out.println( "Number of distinct residues in this residue set/class : " + residuecount ) ;
}


/*****************************************************************************
This function computes the residue class for a given power and a given mod_n
Aka x^pow mod_n for x = { 0 thru ( mod_n - 1 ) }
There is no need to check a higher range of values or a different range once you have those values it will be periodical the sequence 

I used BigInteger package/lib functions to get around issues with overflow or being stuck to only analysising certain low values 
With BigInteger the computer is not bound to the standard max size datatype and thus can display any values 
Its cool to not have to think about overflows. Focusing just on the math at hand!
*****************************************************************************/

public static void residueclass( BigInteger pow , BigInteger mod_n ) 
{

BigInteger startpoint = BigInteger.ZERO ;
BigInteger endpoint = mod_n ; 

while( endpoint.compareTo(startpoint) != 0 )
{
 residue( pow , mod_n , startpoint ) ;
 startpoint = startpoint.add(BigInteger.ONE) ;
}


}

/*****************************************************************************
This function computes x^power mod_n  another words the power residue of x mod_n.
So for example  x^3 mod 7 it would compute the 3 residue of x mod 7 ( also famously called the cubic residue mod 7 of x )
residueclass function uses this function iteratively to compute each value in its startpoint and endpoint range
Thus computing the entire residue class
*****************************************************************************/

public static  void residue( BigInteger power , BigInteger mod_n , BigInteger x )
{

BigInteger residue = x;
//Could have done it with the commented out lines but found if i used the modPow function it gives me away to be slightly more general with BigInteger powers not integer powers
//residue = residue.pow( power ) ;
//residue = residue.mod( mod_n ) ;
residue = residue.modPow( power, mod_n ) ;

rclass.add(residue) ;
System.out.print( " " + residue ) ;

}


}
