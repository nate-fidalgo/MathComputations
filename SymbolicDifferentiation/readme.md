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
...
           case 25: //this was for Ln derivative which you all know is 1/var
              if( isPrimitiveArgument( arg ) == true )
              return "1/" + var  ; 
              else
              return "NO" ;
              
           case 26: 
              if( isPrimitiveArgument( arg ) == true )
              return "1/" + var  ; 
              else
              return "NO" ;
              //HERE IS WHERE YOU WOULD ADD YOUR DERIVATIVE FOR YOUR NEW SPECIAL FUNCTION!!!
              //Or special functions like gamma beta zeta and so on ....
          default:
          
...

//then one last step is create a function to take care of your new special function derivative when has a complex argument by adding a function call for it
//in private static String diff_function( String func_name , String argument ) in the code go there and it should look like this
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

</code>
</pre>
