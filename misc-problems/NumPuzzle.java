
import java.math.BigInteger ;

/*
Martin Gardner's problem

"A number's persistence is the number of steps required to reduce it to a single digit by multiplying all its digits to obtain a second number, 
then multiplying all the digits of that number to obtain a third number, and so on until a one-digit number is obtained.
For example, 77 has a persistence of four because it requires four steps to reduce it to one digit: 77-49-36-18-8.
The smallest number of persistence one is 10, the smallest of persistence two is 25, the smallest of persistence three is 39, and the smaller of persistence four is 77.
What is the smallest number of persistence five?"



NEW FEATURES SET STARTNUMBER TO A VALUE YOU WANT TO START AT SO YOU DONT ALWAYS HAVE TO START AT THE BEGINING 10 NUMBER!!!!!!!!!!
ALLOWS ONE TO SEARCH AT ARBITRARY HIGH NUMBERS THE ONLY LIMIT IS THE SIZE OF MEMORY ALLOCATE TO THE JVM HEAP TO HOLD THE NUMBER STRING WHICH IS USUALLY NOT AN ISSUE FOR BIGINTEGER 

HECK BIGINTEGER HOLD THE LARGEST PRIME NUMBER IN EXISTENCE WITH EASY SO YOU REALLY HAVE TO BE DOING SOMETHING MESSED UP TO CAUSE AN OUT OF MEMORY ERROR

*/



public class NumPuzzle {


private static final String STARTNUMBER = "2147483647" ; ///SET YOUR START VALUE IF YOUR SEARCHING FOR HIGHER AND HIGHER PERSISTENCE /CYCLE HERE DEFAULT IS START FROM FIRST 2 DIGIT NUMBER

//Takes in only positive base10 numbers as a string 
//And returns the multiplied digits of that string number as a base10 string number
//Uses BigInteger class so your not restricted in size of string number or size of digits
//No worries on overflow errors or any data type size limits only worry about if you got the memory 
//to complete the computation and the time :)
public static String multdigit(String num)
{

if( num.length() == 0 )
return num ;

BigInteger bn = new BigInteger( "" + num.charAt(0) ) ;

for( int i = 1 ; i < num.length() ; i++ )
bn = bn.multiply( new BigInteger( "" + num.charAt(i) ) ) ;

return bn.toString() ;

}



//Takes the cycle length to search for if clength is -1 it goes on for as long as you want 
//until you manually control+c it to stop you can uses -1 to this function as a way to search 
//go higher and higher indefinitely dumping it to a file or so.

//But the main uses is to find the first number that has a cycle length of clength
//See Martin Gardner's recreational math book of numbers.

//persistence MEANS cycle and visa-versa as a reminder

public static String findfirstcyclelength( int clength )
{

int cycle_length = 0 ;
String number2 = "" ;
BigInteger startval = new BigInteger(STARTNUMBER) ;
String number = startval.toString() ;  
while( true )
{
number= startval.toString() ;
System.out.println( "The iterations for number " + number ) ;

while( true )
{
number2 = number ;
number = multdigit(number) ;



if( number2.equals( number ) == true && number.length() == 1 )
break ;

System.out.println( number ) ;
cycle_length++ ;

}

System.out.println( "cycle length is " + cycle_length ) ;
if( cycle_length == clength )
{
System.out.println("the first number of cycle length " + clength + " is " + startval) ;
return startval.toString() ; 
}

cycle_length = 0;

startval = startval.add( BigInteger.ONE ) ;

}


}

/*

Added function to call if you just want to find out the persistence of a particular number not wanting to run through computing all the numbers persistence's
Takes a positive base10 number as a string
prints the numbers in the iteration and how many iterations it takes
AKA the info on that particular number persistence
Alot of randomly high numbers such as say this random number
3482482894329837472384723987489274239849287348929847927394872999999999999999211111111111111111133333333333333333333444444444444488888888888822222222211111111199999999333334
Has persistence of 2 because most digits multiplied out will at least create a large number with at least one zero digit in it 
at that point the next iteration is 0 and your done so it get really hard to find higher and higher persistence 1 and 2 are very common from there it get harder and hard to find.

Note: I believe you can keep finding higher and higher persistences but unsure of how to prove it yet
      I believe there are infinite persistences of k size so there are infinite 1, 2,3,4 ...etc persistence also but cannt prove it yet
      I dont know of the growth of the persistences or the distribution or any functions corrosponding to bigO or discribe this 
      Does there exist a prime number like formula or function to discribe the growth or generate the next biggest persistence not sure !!!!
       
All great question maybe somebody knows out there :)

But at least this function allows you to type in any number string not confined to a certain size and it will compute it
with no worries of data type size or overflows or other inaccuracies that computers could do with arithmetic with arbitrary large sizes.
Got to love BigInteger api of computer programming languages :)

*/
public static void persistence( String num )
{

int cycle_length = 0 ;
String number2 = "" ;
String number = num ;

while( true )
{
number2 = number ;
number = multdigit(number) ;

if( number2.equals( number ) == true && number.length() == 1 )
break ;

System.out.println( number ) ;
cycle_length++ ;

}

System.out.println( "cycle length is " + cycle_length ) ;

}


public static void main( String args[] )
{

System.out.println( "Computes a huge random numbers persistence" ) ;
persistence( "998888878777772111111111111111111" ) ;

persistence( "277777788888899" ) ;

//findfirstcyclelength( 10 ) ;
// Uncomment to see the brute force solution of the main problem 
//System.out.println( "Martin Gardner's problem solved with answer = " + findfirstcyclelength( 5 ) ) ;
System.exit(0) ;


}





}
