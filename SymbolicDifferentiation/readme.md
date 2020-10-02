Listen to the ListenDIFF mp3 file you have to download it with the download button on the github page in the below link.
<br>
Its sizzly as hell but i hope it easier with this podcast type thing to understand then reading a readme file.
<br>
Because readme files and documents get to be a pain in the butt in typing them up. ProofReading them gets boring and the wording confuses people so i figured i try a short cut with audio podcast. 
<br>
I am trying to see by this if the podcast audio files are easier to comprehend then readme files interms of understanding how to uses the Differentiation.java program
<br>
 <a href="https://github.com/nate-fidalgo/MathComputations/blob/master/SymbolicDifferentiation/ListenDIFF">ListenDIFF mp3</a> 
 
<br>
If you want to add your own custom special functions to differentiate  do the following in code
<br>
<pre>
<code>
//Currently these function are support to differentiate or will be fairly soon  !!!
private static String function[] = {  "Sinh" , "Cosh" , "Tanh" , "Sech" , "Coth" , "Csch" ,
"Sin" , "Cos" , "Tan" , "Sec" , "Cot" , "Csc" , "ArcSinh" , "ArcCosh" , 
"ArcTanh" , "ArcSech" , "ArcCoth" , "ArcCsch" , "ArcSin" , "ArcCos" , "ArcTan" , "ArcSec" ,
"ArcCot" , "ArcCsc" , "Exp" , "Ln" , "Log" 
} ; 

//In the code you can add your own special function like bessel , hypergeometric function , elliptical ,...etc by doing this
private static String function[] = {  "Sinh" , "Cosh" , "Tanh" , "Sech" , "Coth" , "Csch" ,
"Sin" , "Cos" , "Tan" , "Sec" , "Cot" , "Csc" , "ArcSinh" , "ArcCosh" , 
"ArcTanh" , "ArcSech" , "ArcCoth" , "ArcCsch" , "ArcSin" , "ArcCos" , "ArcTan" , "ArcSec" ,
"ArcCot" , "ArcCsc" , "Exp" , "Ln" , "Log" , "ADD YOUR NEW FUNCTION HERE"
} ; 

//GO TO THE isKNOWN_diff function in the code also and add your new functions derivative there 
//...
           case 25: //this was for Ln derivative which you all know is 1/var
              if( isPrimitiveArgument( arg ) == true )
              return "1/" + var  ; 
              else
              return "NO" ;
              
           case 26: //this was for Log derivative which should be different then 1/var but for now i am fine with just letting it be the same as Ln
              if( isPrimitiveArgument( arg ) == true )
              return "1/" + var  ; 
              else
              return "NO" ;
              //HERE IS WHERE YOU WOULD ADD YOUR DERIVATIVE FOR YOUR NEW SPECIAL FUNCTION, JUST CREATE A CASE 27: AND SO ON!! 
           default:
          
//...

//then one last step is create a function to take care of your new special function derivative when it has a complex argument by adding a function call for it
//Go to private static String diff_function( String func_name , String argument ) in the code 
   ...
   if( func_name.equals( "Sin" ) == true )
   return sindiff( argument ) ;
    
   if( func_name.equals( "Cos" ) == true )
   return cosdiff( argument ) ; 
    
   if( func_name.equals( "Tan" ) == true )
   return tandiff( argument ) ; 
    
   if( func_name.equals( "Sec" ) == true )
   return secdiff( argument ) ; 
    
   if( func_name.equals( "Csc" ) == true )
   return cscdiff( argument ) ;
   
   if( func_name.equals( "Cot" ) == true )
   return cotdiff( argument ) ;
   
   if( func_name.equals( "Log" ) == true )
   return logdiff( argument ) ;
   
   if( func_name.equals( "Exp" ) == true )
   return expdiff( argument ) ;
   
   //ADD YOUR IF STATEMENT TO CALL YOUR SPECIAL FUNCTION FOR CHAIN RULE HERE
   //EXAMPLE
   if( func_name.equals( "Sndiff" ) == true ) 
   return Sndiff( argument ) ;
   ....
   
   
//All that need to be done now is make sure you create your private static String Sndiff( argument ) function 
//And your done congradulation you just add a new special function to my symbolic differentiation programs capabilities in this example Sn
//Would Probably Stand for elliptical function Sn.
//The reason why you need to create a method and not just add a case statement is the case statement only takes care of primitive arguments for example
//Sin[x] the case statement would give back Cos[x] however if one want to differentiate Sin[x^2+1] then it have to uses a chain rule.
//And your Sndiff( argument ) function is the equivalent of differentiating for chain rule when the argument is not primitive!

</code>
</pre>

<br>
Most of the time you probably not be adding new special functions to be differentiated you probably just be using my program to differentiate the standardly used functions. I.E trig , hyperbolic , there inverses , exp , ln , log ,polynomial , or rational functions.
<br>

<pre>
<code>
//For that you just call diff( "Sin[x^(1/2)-x^2]" ) ; // diff( String func ) the func is the function to differentiate returns a String representation back
//Which you then can just print it to the screen like this     System.out.println( diff( "Sin[x^(1/2)-x^2]" ) ) ; or dump it to a file or whatever you want.
//NOTE I FIGURED OUT HOW TO PROVIDE SIMPLIFICATION IN A REASONABLE MANNER BUT ITS NOT IMPLEMENTED YET SO MOST EXPRESSIONS WILL BE NOT IN SIMPLIFIED FOR
//ALSO THE FORMAT OF THE OUTPUT IS NOT IN LATEX/TEX EVENTUALLY I MAY CREATE A TRANSLATOR PROGRAM TO CONVERT THE OUTPUT FORMAT INTO TEXT SO YOU CAN GENERATE THE 
//MATH LATEX NICE FORMAT FOR THE DERIVATIVES OR EVEN FROM THERE MAKE GIF OR IMAGE FILES OF THE LATEX TO POST ON MATH SITES
//THE DEFAULT VARIABLE THAT YOU DIFFERENTIATE WITH RESPECTS TO IS x found here in code 
private static String var = "x" ; // if you want a different variable change it var = "w" , or whatever and then you can  have Sin[w^(1/2)-w^2] instead of Sin[x^(1/2)-x^2] in your diff. Only restriction currently is keep the variable one character so any of the 26 letter would be fine

//To me 26 letters is enough for choice however eventually all make it unlimited but most calculus students until they reach multivariable levels will only need single variables

</code>
</pre>
