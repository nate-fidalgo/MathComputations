

import java.util.* ;
import java.io.* ;

public class Differentiation {
 

private static String function[] = {  "Sinh" , "Cosh" , "Tanh" , "Sech" , "Coth" , "Csch" ,
"Sin" , "Cos" , "Tan" , "Sec" , "Cot" , "Csc" , "ArcSinh" , "ArcCosh" , 
"ArcTanh" , "ArcSech" , "ArcCoth" , "ArcCsch" , "ArcSin" , "ArcCos" , "ArcTan" , "ArcSec" ,
"ArcCot" , "ArcCsc" , "Exp" , "Ln" , "Log" 
} ; 

private static String var = "x" ;
private static String funct = "" ;
private static DiffTable diff_Table = new DiffTable() ;
public static int abcdefg = 0 ;

public Differentiation( String diff_var , String function )
{
    
  var = diff_var ;
  funct = function ;
    
}



public static String[] getFunctions() 
{
     return function ;
}


public static DiffTable getDiff_Table()
{
  return diff_Table ;  
    
}

private static boolean noPlus_orMinus( String expr )
{
 
 int i = 0 ;
 while( i < expr.length() )
 {
  
   if( expr.charAt(i) == '+' || expr.charAt(i) == '-' )
   return false ;
   
   i++ ;   
     
 }
    
 
 return true ;
 
}



private static boolean noMinus( String expr )
{
 
 int i = 0 ;
 while( i < expr.length() )
 {
  
   if( expr.charAt(i) == '-' )
   return false ;
   
   i++ ;   
     
 }
    
 
 return true ;
 
}






private static boolean hasDivides( String expr )
{
    
int i = 0 ;
 while( i < expr.length() )
 {
  
   if( expr.charAt(i) == '/' )
   return false ;
   
   i++ ;   
     
 }
    
 
 return true ;
 
}    
    
    
    
private static boolean needsParAround( String expr )
{
   
   
       
   if( ("" + expr.charAt(0)).equals( "(" ) == true ) 
   {
       int pos = getMatching( ")" , 0 , "SEARCH LEFT" , expr ) ;
       if( pos == expr.length() - 1 )
       return false ;
       
       return true ;       
   }
   else  
   if( isStartOf_SpecialFunction( 0 , expr ) == true )
   {
     
       
       int pos = getMatching( "]" , expr.indexOf( "[" ) , "SEARCH LEFT" , expr ) ;
       if( pos == expr.length() - 1 )
       return false ;
       
       return true ;
       
   }
   else
   {
       
     if( expr.equals( var ) == true )
         return false ;
     
     
     if( isConstant( expr ) == true )
     return false ;
     
     return true ;
       
            
   }
    

    
}




private static String adddiff( String expr1 , String expr2 )
{


System.out.println( "Gott in add + " + expr1 + "   " + expr2 ) ;
String f1 = diff( expr1 ) ;
String f2 = diff( expr2 ) ;

if( isConstant( f1 ) == true && isConstant( f2 ) == true )
{
 try{
     
    int d_value = Integer.parseInt( f1 ) + Integer.parseInt( f2 ) ;
     diff_Table.addDiff( expr1 + "+" + expr2 , "" + d_value ) ;    
    return "" + d_value ;   
 }
 catch(Exception e ){}
 
}


if( f1.equals( "0" ) == true && f2.equals( "0" ) != true )
{

diff_Table.addDiff( expr1 + "+" + expr2 , f2 ) ; 
return f2 ;

}
else
if( f1.equals( "0" ) != true && f2.equals( "0" ) == true )
{
    
diff_Table.addDiff( expr1 + "+" + expr2 , f1 ) ; 
return f1 ;

}
else
{
 




 if( f1.equals( f2 ) == true )
 {
  
  if( f1.charAt( 0 ) == '-' || needsParAround( f1 ) == true )
  {
    diff_Table.addDiff( expr1 + "+" + expr2 ,  "2*(" + f1 + ")" ) ;        
    return "2*(" + f1 + ")"  ;    
      
  }
     
   diff_Table.addDiff( expr1 + "+" + expr2 ,  "2*" + f1 ) ;        
   return "2*" + f1  ;  
     
 }

 //Put in simplification algorithms hereeee   
 if( needsParAround( f1 ) == true )
 f1 = "(" + f1 + ")" ;

 if( needsParAround( f2 ) == true )
 f2 = "(" + f2 + ")" ;  

 diff_Table.addDiff( expr1 + "+" + expr2 ,  f1 + "+" + f2 ) ;        
 return f1 + "+" + f2  ;
    
}



}


private static String proddiff( String expr1 , String expr2 )
{

System.out.println( "got to proddiff " + expr1 + "  " + expr2 ) ;
String f1 = diff( expr1 ) ;
System.out.println( "got to proddiff " + expr1 + "  " + expr2 + "  " + f1) ;
String f2 = diff( expr2 ) ;
System.out.println( expr1 + "  " + expr2 + "   " + f1 + "    " + f2 + "   " + "in Product method!!! " ) ;
if( needsParAround( f1 ) == true )
f1 = "(" + f1 + ")" ;

if( needsParAround( f2 ) == true )
f2 = "(" + f2 + ")" ;


if( f1.equals( "0" ) == true && f2.equals( "0" ) == true )
{

diff_Table.addDiff( expr1 + "*" + expr2 , "0"  ) ;    
return "0" ;  
   
}
else
if( f1.equals( "0" ) == true && f2.equals( "0" ) != true )
{

try{
 int d_value = Integer.parseInt( f2 ) * Integer.parseInt( expr1 ) ;  
 diff_Table.addDiff( expr1 + "*" + expr2 , "" + d_value  ) ;    
 return "" + d_value ;     

}
catch( Exception e ){}
    

diff_Table.addDiff( expr1 + "*" + expr2 , expr1 + "*" + f2  ) ;    
return expr1 + "*" + f2 ;  


}
else
if( f1.equals( "0" ) != true && f2.equals( "0" ) == true )
{

try{
 int d_value = Integer.parseInt( f1 ) * Integer.parseInt( expr2 ) ;  
 diff_Table.addDiff( expr1 + "*" + expr2 , "" + d_value  ) ;    
 return "" + d_value ;     
}
catch( Exception e ){}
    
    

diff_Table.addDiff( expr1 + "*" + expr2 , f1 + "*" + expr2  ) ;    
return f1 + "*" + expr2 ;  


}    


if( expr1.equals(expr2) == true )
{
    
if( f1.equals( "1" ) == true )
{
if( expr1.charAt(0) == '-' ||  needsParAround( expr1 ) == true )
{
diff_Table.addDiff( expr1 + "*" + expr2 , "2*" + "(" + expr1 + ")" ) ;    
return "2*" + "(" + expr1 + ")" ;    
}

diff_Table.addDiff( expr1 + "*" + expr2 , "2*" + expr1 ) ;    
return "2*" + expr1 ;      

}
    
if( needsParAround( f1 ) == true )
f1 = "(" + f1 + ")" ;

if( expr1.charAt(0) == '-' ||  needsParAround( expr1 ) ) //don't think I need to check if Par around expr1 are needed!
{
diff_Table.addDiff( expr1 + "*" + expr2 , "2*" + f1 + "*(" + expr1 + ")" ) ;    
return "2*" + f1 + "*(" + expr1 + ")" ;    
}

diff_Table.addDiff( expr1 + "*" + expr2 , "2*" + f1 + "*" + expr1 ) ;    
return "2*" + f1 + "*" + expr1 ;  
    
}


//Should put in simplification like x*x = not 1*x+1*x but 2*x

diff_Table.addDiff( expr1 + "*" + expr2 , f1 + "*" + expr2 + "+" + f2 + "*" + expr1  ) ;    
return f1 + "*" + expr2 + "+" + f2 + "*" + expr1 ;


}


private static String subdiff( String expr1 , String expr2 )
{


String f1 = diff( expr1 ) ;
String f2 = diff( expr2 ) ;
System.out.println( "in subdiff this is its parmeters input " + expr1 + "   " + expr2 ) ;
System.out.println( f1 + " -in subdiff --- " + f2 ) ;
if( isConstant( f1 ) == true && isConstant( f2 ) == true )
{
 try{
     
     int d_value = Integer.parseInt( f1 ) - Integer.parseInt( f2 ) ;
     diff_Table.addDiff( expr1 + "-" + expr2 , "" + d_value ) ;    
     return "" + d_value ;   
 }
 catch(Exception e ){}
 
}
//System.out.println( f1 + "----" + f2 ) ;
if( needsParAround( f1 ) == true )
f1 = "(" + f1 + ")" ;

if( needsParAround( f2 ) == true )
f2 = "(" + f2 + ")" ;

//Probably still need for some reason

if( f1.equals( "0" ) == true && f2.equals( "0" ) != true )
{
//if( needsParAround( f2 ) == true )
//f2 = "(" + f2 + ")" ;
diff_Table.addDiff( expr1 + "-" + expr2 , "-1*" + f2 ) ; 
return "-1*" + f2 ;
}
else
if( f1.equals( "0" ) != true && f2.equals( "0" ) == true )
{
diff_Table.addDiff( expr1 + "-" + expr2 , f1 ) ; 
return f1 ;
}
else
{

  
if( f1.equals( f2 ) == true )
{
    
diff_Table.addDiff( expr1 + "-" + expr2 , "0" ) ;        
return "0" ;          

}

//if( needsParAround( f1 ) == true )
//f1 = "(" + f1 + ")" ;

//if( needsParAround( f2 ) == true )
//f2 = "(" + f2 + ")" ;    
    System.out.println( "Gottttt here in subdiff at end " ) ;
diff_Table.addDiff( expr1 + "-" + expr2 , f1 + "-" + f2 ) ;        
return f1 + "-" + f2   ;       
       
}



}


private static String powdiff( String expr , String power )
{

System.out.println( "in Power method!!! " + expr + "  " + power ) ;

if( power.equals( "0" ) == true || expr.equals( "0" ) == true )
{
 diff_Table.addDiff( expr + "^" + power , "0" ) ;
 return "0" ;    
}

if( isConstantExpression( power ) == true && isConstantExpression( expr ) == true )
{
System.out.println( "in Power method!!! is a Constant " ) ;
diff_Table.addDiff( expr + "^" + power , "0" ) ;
return "0" ;
}

if( isConstantExpression( power ) == true && isConstantExpression( expr ) == false )
{
    

    
System.out.println( "in Power method!!! " + expr + "  " + power ) ;
String f1 = diff( expr ) ;
System.out.println( "in Power method!!! " + expr + "  " + power + "   " + f1 ) ;
String resultpow = subtractOne( power ) ;
System.out.println( "heyyyyyyyyyyyyyyyyyy   needs Par " + f1 ) ;
//if( needsParAround( f1 ) == true )
//f1 = "(" + f1 + ")" ;

System.out.println( f1 ) ;
if( f1.equals( "1" ) == true )
{
 
 if( resultpow.equals("1") == true )
 {
 diff_Table.addDiff( expr + "^" + power , power + "*" + expr ) ; 
 return power + "*" + expr ;    
    
 }   
    
 if( resultpow.equals("0") == true )
 {
     
 diff_Table.addDiff( expr + "^" + power , power ) ; 
 return power ;    
    
 }      
    
 diff_Table.addDiff( expr + "^" + power , power + "*" + expr + "^" + resultpow ) ; 
 return power + "*" + expr + "^" + resultpow ;

}
else{
System.out.println( "TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT" ) ;

 if( resultpow.equals( "0" ) == true && power.equals( "1" ) == true ) //I think I only need to check for resultpower => power=1
 {
     
   diff_Table.addDiff( expr + "^" + power , f1  ) ; 
   return f1 ;    
     
 }

 if( resultpow.equals( "1" ) == true )
 {
     
  if( needsParAround( f1 ) == true )
  f1 = "(" + f1 + ")" ;   
  diff_Table.addDiff( expr + "^" + power , power + "*" + expr + "*" + f1  ) ; 
  return power + "*" + expr + "*" + f1 ;   
     
 }

 
  if( needsParAround( f1 ) == true )
  f1 = "(" + f1 + ")" ;
 
  diff_Table.addDiff( expr + "^" + power , power + "*" + expr + "^" + resultpow + "*" + f1  ) ; 
  return power + "*" + expr + "^" + resultpow + "*" + f1 ; 
}


}

if( isConstantExpression( power ) == false && isConstantExpression( expr ) == true )
{
  
if( expr.equals( "1" ) == true || expr.equals( "0" ) == true )
{
diff_Table.addDiff( expr + "^" + power , "0" ) ; 
return "0" ;      
}    
    
String f2 = diff( power ) ;


if( f2.equals( "1" ) == true )
{
diff_Table.addDiff( expr + "^" + power , expr + "^" + power + "*" + "Ln[" + expr + "]" ) ; 
return expr + "^" + power + "*" + "Ln[" + expr + "]" ;    
}


if( needsParAround( f2 ) == true )
f2 = "(" + f2 + ")" ;

if( f2.charAt(0) == '-' )
{
diff_Table.addDiff( expr + "^" + power , expr + "^" + power + "*" + "Ln[" + expr + "]*(" + f2 + ")" ) ; 
return expr + "^" + power + "*" + "Ln[" + expr + "]*(" + f2 + ")" ;   
}


diff_Table.addDiff( expr + "^" + power , expr + "^" + power + "*" + "Ln[" + expr + "]*" + f2 ) ; 
return expr + "^" + power + "*" + "Ln[" + expr + "]*" + f2 ; 
   
    
}

//May Needs to be cleaned up and finalized.... 
if( isConstantExpression( power ) == false && isConstantExpression( expr ) == false )
{
    
 String f1 = diff( expr ) ;   
 String f2 = diff( power ) ;  
 
 if( expr.equals( power ) == true )
 {
     
   if( f1.equals( "1" ) == true && f2.equals( "1" ) == true )
   {
   
     diff_Table.addDiff( expr + "^" + power , expr + "^" + power + "*(" + "(Ln[" + expr + "])" + "+" + "(" + power + "/" + expr + ")" + ")" ) ; 
     return expr + "^" + power + "*(" + "(Ln[" + expr + "])" + "+" + "(" + power + "/" + expr + ")" + ")" ;     
     
   }
   else
   if( f1.equals( "1" ) == true )
   {
   
     diff_Table.addDiff( expr + "^" + power , expr + "^" + power + "*(" + "(Ln[" + expr + "])*(" + f2 + ")+" + f1 + ")" ) ; 
     return expr + "^" + power + "*(" + "(Ln[" + expr + "])*(" + f2 + ")+" + f1 + ")" ;
     
   }
   else
   if( f2.equals( "1" ) == true )
   {
       
     diff_Table.addDiff( expr + "^" + power , expr + "^" + power + "*(" + "(Ln[" + expr + "])" + "+" + "(" + f1 + ")" + ")" ) ; 
     return expr + "^" + power + "*(" + "(Ln[" + expr + "])" + "+" + "(" + f1 + ")" + ")" ;  
       
   }
     
     
     
  diff_Table.addDiff( expr + "^" + power , expr + "^" + power + "*(" + "(Ln[" + expr + "])*(" + f2 + ")+" + "(" + f1 + ")" + ")" ) ; 
  return expr + "^" + power + "*(" + "(Ln[" + expr + "])*(" + f2 + ")+" + "(" + f1 + ")" + ")" ;
        
 }
 
 
 
 diff_Table.addDiff( expr + "^" + power , expr + "^" + power + "*(" + "(Ln[" + expr + "])*(" + f2 + ")+" + "(" + power + "/" + expr + ")*(" + f1 + ")" + ")" ) ; 
 return expr + "^" + power + "*(" + "(Ln[" + expr + "])*(" + f2 + ")+" + "(" + power + "/" + expr + ")*(" + f1 + ")" + ")" ;   
    
    
    
}




return "Errrrrrrrroooorrrrr" ;

}


private static String subtractOne( String func )
{
  
  try{
  return "" + (Integer.parseInt( func ) - 1) ;
  }
  catch( Exception e )
  {
  
    return "("+ func + "-1)" ;
      
  }
    
     
    
}

//needs to improve simplification algorithm....ssssss
private static String quotdiff( String expr1 , String expr2 )
{
    
    
    if( expr1.equals( expr2 ) == true )
    {
        
       diff_Table.addDiff( expr1 + "/" + expr2 , "0" ) ; 
       return "0" ;         
    
    }
    
        
    String f1 = diff( expr1 ) ;
    String f2 = diff( expr2 ) ;
    
    if( f1.equals( "0" ) == true && f2.equals( "0" ) == true ) //May not need!!!!
    {
        
     diff_Table.addDiff( expr1 + "/" + expr2 , "0" ) ; 
     return "0" ;      
        
    }
    
    
     
   
   if( f1.equals( "0" ) == true )
   {
      
     if( needsParAround( f2 ) == true )
     f2 = "(" + f2 + ")" ;
     
     if( needsParAround( expr2 ) == true ) 
     {
     diff_Table.addDiff( expr1 + "/" + expr2 , "(" + "-1*" + f2 + "*" + expr1 + ")/(" + expr2 + ")^2" ) ; 
     return "(" + "-1*" + f2 + "*" + expr1 + ")/(" + expr2 + ")^2" ;      
         
     }
     
     diff_Table.addDiff( expr1 + "/" + expr2 , "(" + "-1*" + f2 + "*" + expr1 + ")/" + expr2 + "^2" ) ; 
     return "(" + "-1*" + f2 + "*" + expr1 + ")/" + expr2 + "^2" ;   
       
   }
   
   
   
   if( f2.equals( "0" ) == true ) 
   {
            
      if( needsParAround( f1 ) == true )
      f1 = "(" + f1 + ")" ;
      
      if( needsParAround( expr2 ) == true )
      {
       
      diff_Table.addDiff( expr1 + "/" + expr2 , "(" + f1 + "*" + expr2 + ")/(" + expr2 + ")^2" ) ; 
      return "(" + f1 + "*" + expr2 + ")/(" + expr2 + ")^2"  ;         
          
      }
      
      diff_Table.addDiff( expr1 + "/" + expr2 , "(" + f1 + "*" + expr2 + ")/" + expr2 + "^2" ) ; 
      return "(" + f1 + "*" + expr2 + ")/" + expr2 + "^2"  ;         
       
   }
    
   
      
    if( needsParAround( f1 ) == true )
    f1 = "(" + f1 + ")" ;
    
    if( needsParAround( f2 ) == true )
    f2 = "(" + f2 + ")" ;
    
    if( needsParAround( expr2 ) == true )
    {
        
     diff_Table.addDiff( expr1 + "/" + expr2 , "(" + f1 + "*" + expr2 + "-(" + f2 + "*" + expr1 + "))/(" + expr2 + ")^2" ) ; 
     return "(" + f1 + "*" + expr2 + "-(" + f2 + "*" + expr1 + "))/(" + expr2 + ")^2" ;      
        
    }
       
   
   diff_Table.addDiff( expr1 + "/" + expr2 , "(" + f1 + "*" + expr2 + "-(" + f2 + "*" + expr1 + "))/" + expr2 + "^2" ) ; 
   return "(" + f1 + "*" + expr2 + "-(" + f2 + "*" + expr1 + "))/" + expr2 + "^2" ;   
     
    
    
    
}





private static String expdiff( String expr )
{

    
String f1 = diff( expr ) ; 

if( f1.equals( "1" ) == true )
{
    
diff_Table.addDiff( "Exp[" + expr + "]" , "Exp[" + expr + "]" ) ;    
return "Exp[" + expr + "]" ;  
    
}

    
if( needsParAround( f1 ) == true )
f1 = "(" + f1 + ")" ;

diff_Table.addDiff( "Exp[" + expr + "]" , "Exp[" + expr + "]*" + f1 ) ;    
    
return "Exp[" + expr + "]*" + f1 ;  

}


private static String logdiff( String expr )
{

String f1 = diff( expr ) ;

if( needsParAround( f1 ) == true )
f1 = "(" + f1 + ")" ;

diff_Table.addDiff( "Log[" + expr + "]" , f1 + "/" + expr ) ;     
return f1 + "/" + expr ; 

}

private static String sindiff( String expr )
{

String f1 = diff( expr ) ;

if( f1.equals( "1" ) == true )
{
diff_Table.addDiff( "Sin[" + expr + "]" , "Cos[" + expr + "]" ) ;     
return "Cos[" + expr + "]"  ;     
    
}

if( needsParAround( f1 ) == true )
f1 = "(" + f1 + ")" ;   

diff_Table.addDiff( "Sin[" + expr + "]" , "Cos[" + expr + "]*" + f1  ) ;     
return "Cos[" + expr + "]*" + f1  ;

}


private static String cosdiff( String expr )
{

String f1 = diff( expr ) ;

if( f1.equals( "1" ) == true )
{
diff_Table.addDiff( "Cos[" + expr + "]" , "-1*Sin[" + expr + "]" ) ;     
return "-1*Sin[" + expr + "]"  ;     
    
}

if( needsParAround( f1 ) == true )
{

diff_Table.addDiff( "Cos[" + expr + "]" , "-1*Sin[" + expr + "]*(" + f1 + ")"  ) ;      
return "-1*Sin[" + expr + "]*(" + f1 + ")" ;    
    
}

diff_Table.addDiff( "Cos[" + expr + "]" , "-1*Sin[" + expr + "]*" + f1  ) ;      
return "-1*Sin[" + expr + "]*" + f1 ;

}


private static String tandiff( String expr )
{

String f1 = diff( expr ) ;

if( f1.equals( "1" ) == true )
{
diff_Table.addDiff( "Tan[" + expr + "]" , "Sec[" + expr + "]^2" ) ;      
return "Sec[" + expr + "]^2" ;        
}


if( needsParAround( f1 ) == true )
{
    
diff_Table.addDiff( "Tan[" + expr + "]" , "Sec[" + expr + "]^2*(" + f1 + ")"  ) ;      
return "Sec[" + expr + "]^2*(" + f1 + ")" ;    
    
}

diff_Table.addDiff( "Tan[" + expr + "]" , "Sec[" + expr + "]^2*" + f1  ) ;      
return "Sec[" + expr + "]^2*" + f1 ;

} 


private static String secdiff( String expr )
{
 
  
String f1 = diff( expr ) ;

if( f1.equals( "1" ) == true )
{
    
diff_Table.addDiff( "Sec[" + expr + "]" , "Sec[" + expr + "]" + "*Tan[" + expr + "]" ) ; 
return "Sec[" + expr + "]" + "*Tan[" + expr + "]"  ;      
    
}

if( needsParAround( f1 ) == true )
{
    
diff_Table.addDiff( "Sec[" + expr + "]" , "Sec[" + expr + "]" + "*Tan[" + expr + "]" + "*(" + f1 + ")" ) ; 
return "Sec[" + expr + "]" + "*Tan[" + expr + "]" + "*(" + f1 + ")"  ;    
    
}

diff_Table.addDiff( "Sec[" + expr + "]" , "Sec[" + expr + "]" + "*Tan[" + expr + "]" + "*" + f1  ) ; 
return "Sec[" + expr + "]" + "*Tan[" + expr + "]" + "*" + f1 ;    
    
    
}


private static String cotdiff( String expr ) 
{
 
    
String f1 = diff( expr ) ;

if( f1.equals( "1" ) == true )
{
    
diff_Table.addDiff( "Cot[" + expr + "]" , "-1*Csc[" + expr + "]^2" ) ; 
return "-1*Csc[" + expr + "]^2" ;
        
    
}



if( needsParAround( f1 ) == true )
{
  
    diff_Table.addDiff( "Cot[" + expr + "]" , "-1*Csc[" + expr + "]^2" + "*(" + f1 + ")"  ) ; 
    return "-1*Csc[" + expr + "]^2" + "*(" + f1 + ")" ;
    
}

diff_Table.addDiff( "Cot[" + expr + "]" , "-1*Csc[" + expr + "]^2" + "*" + f1  ) ; 
    
    return "-1*Csc[" + expr + "]^2" + "*" + f1 ;
    
    
}



private static String cscdiff( String expr )
{
  
String f1 = diff( expr ) ;

if( f1.equals( "1" ) == true )
{
    
diff_Table.addDiff( "Csc[" + expr + "]" , "-1*Csc[" + expr + "]*Cot[" + expr + "]" ) ;    
return "-1*Csc[" + expr + "]*Cot[" + expr + "]" ;     
    
}


if( needsParAround( f1 ) == true )
{
    
diff_Table.addDiff( "Csc[" + expr + "]" , "-1*Csc[" + expr + "]*Cot[" + expr + "]" + "*(" + f1 + ")" ) ;    
return "-1*Csc[" + expr + "]*Cot[" + expr + "]" + "*(" + f1 + ")" ;   
    
}

diff_Table.addDiff( "Csc[" + expr + "]" , "-1*Csc[" + expr + "]*Cot[" + expr + "]" + "*" + f1   ) ;    
return "-1*Csc[" + expr + "]*Cot[" + expr + "]" + "*" + f1 ;   
    
    
}






private static String diff( String func )
{
System.out.println( "Top of diff " + func ) ;
abcdefg++ ;
String f = isKNOWN_diff( func ) ;

if( f.equals( "NO" ) != true )
return f ;

int i = 0 ; 
String operands[] = new String[2] ;

int j = skipPar( func , 0 ) ;

while( i < function.length )
{
if( func.startsWith( function[i] , j ) == true )
break ;
i++ ;
}
if( i != function.length )
{
    
   
 int indexs = getMatching( "]" , func.indexOf( "[" ) , "SEARCH RIGHT" , func ) ;   
 System.out.println( "*********************GGGGGGGGGGGGGOOOOOOOOOOOOOTTTTTTTTTTTTTTTT*************** " + indexs + "  " + func.length() + "  " + skipPar(func, indexs + 1 )) ; 
 if( indexs == (func.length() - 1 ) || ( indexs + skipPar(func, indexs + 1 )) == (func.length() - 1 ) )
 return diff_function( function[i] , func.substring( func.indexOf( "[" ) + 1, indexs ) ) ;
    
  System.out.println( "HHHHHHGYYTYTUYUYGJHKJHKJH" ) ;     

 //    if( diff_Table.getDeriv_OF( funct ).equals( "NOT IN TABLE" ) == false )
 //    return diff_Table.getDeriv_OF( funct ) ;


}


i = 0 ;
String deriv_func = "" ;

while( i < func.length() )
{
   System.out.println( "function in diff is " + func ) ;
   System.out.println( "Table is " + diff_Table ) ;
    if( diff_Table.getDeriv_OF( func.substring(0,func.length()-1) ).equals( "NOT IN TABLE" ) == false )
    {
       System.out.println( "Got in weirdstuff " ) ;
        return diff_Table.getDeriv_OF( func.substring(0,func.length()-1)  ) ;   
    
    }
    
    
    System.out.println( "In while start " + i ) ;
    System.out.println( "function in diff is " + deriv_func ) ;
    if( diff_Table.getDeriv_OF( func ).equals( "NOT IN TABLE" ) == false )
    return diff_Table.getDeriv_OF( func  ) ;   
    
   System.out.println( "FFFFUUUUUUUCCCCCKKKKKKKKKK!!!!" ) ;
  
//if( diff_Table.getDeriv_OF( funct ).equals( "NOT IN TABLE" ) == false )
//return diff_Table.getDeriv_OF( funct ) ;   
    System.out.println( "In while start2 " + i ) ;
   
if( ( "" + func.charAt(i)).equals( "^" ) == true )    
{
operands = getOperands( func , i , "^" ) ;
 System.out.println( "got to ^ " + func ) ;
deriv_func = powdiff( operands[0] , operands[1] ) ; 
System.out.println( "got to ^ " + operands[0] + "  " + operands[1] + "  " + deriv_func + "  " + func ) ;
diff_Table.addDiff( operands[0] + "^" + operands[1] , deriv_func ) ; 
//if( diff_Table.getDeriv_OF( funct ).equals( "NOT IN TABLE" ) == false )
//return diff_Table.getDeriv_OF( funct ) ;

}

if( ( "" + func.charAt(i)).equals( "*" ) == true )    
{
   
      
    System.out.println( "got to * " + func ) ;
operands = getOperands( func , i , "*" ) ;
deriv_func = proddiff( operands[0] , operands[1] ) ; 
System.out.println( "got to * " + operands[0] + "  " + operands[1] + "  " + deriv_func + "  " + func ) ;
diff_Table.addDiff( operands[0] + "*" + operands[1] , deriv_func ) ; 
//if( diff_Table.getDeriv_OF( "(" + func + ")" ).equals( "NOT IN TABLE" ) == false )
//return diff_Table.getDeriv_OF( "(" + func + ")" ) ;
}

if( ( "" + func.charAt(i)).equals( "/" ) == true )    
{
operands = getOperands( func , i , "/" ) ;
deriv_func = quotdiff( operands[0] , operands[1] ) ; 
diff_Table.addDiff( operands[0] + "/" + operands[1] , deriv_func ) ; 
//if( diff_Table.getDeriv_OF( funct ).equals( "NOT IN TABLE" ) == false )
//return diff_Table.getDeriv_OF( funct ) ;
}

if( ( "" + func.charAt(i)).equals( "+" ) == true )    
{System.out.println( "got ++++++++++++++++++++++++++++++++++++++ " ) ;
operands = getOperands( func , i , "+" ) ;
deriv_func = adddiff( operands[0] , operands[1] ) ; 
diff_Table.addDiff( operands[0] + "+" + operands[1] , deriv_func ) ; 
//if( diff_Table.getDeriv_OF( func ).equals( "NOT IN TABLE" ) == false )
//return diff_Table.getDeriv_OF( func ) ;
}


if( ( "" + func.charAt(i)).equals( "-" ) == true )    
{System.out.println( "got ------------------------------------ " ) ;
operands = getOperands( func , i , "-" ) ;
//check for null first operand
deriv_func = subdiff( operands[0] , operands[1] ) ; 
diff_Table.addDiff( operands[0] + "-" + operands[1] , deriv_func ) ; 
//if( diff_Table.getDeriv_OF( func ).equals( "NOT IN TABLE" ) == false )
//return diff_Table.getDeriv_OF( func ) ;
}

i++ ;

}
  
  System.out.println( "Bottom of diff" ) ;
  System.out.println( func + "%%%%%%%%%%%%%%%%%%%%" + deriv_func ) ;
  //System.out.println( "Table is " + diff_Table ) ;
 
  if( diff_Table.getDeriv_OF( func ).equals( "NOT IN TABLE" ) == true && diff_Table.getDeriv_OF( func.substring( 1 , func.length() - 1 ) ).equals( "NOT IN TABLE" ) == true )
  {
   
      
      
      System.out.println( "hfgljhfgkjhfhgidguirururuinderv" + func + "   " + deriv_func ) ;
      return deriv_func ;   
  
  }
  
  if( diff_Table.getDeriv_OF( func.substring( 1 , func.length() - 1 ) ).equals( "NOT IN TABLE" ) != true )
  {
   System.out.println( "hfgljhfgkjhfhgidguirururuinderv" ) ;
   return diff_Table.getDeriv_OF( func.substring( 1 , func.length() - 1 ) ) ;   
  
  }
  
  System.out.println( "#################################################" + deriv_func ) ;
  return diff_Table.getDeriv_OF( func ) ;


}



private static String diff_function( String func_name , String argument )
{
    
   System.out.println( "In diff_function " ) ;
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
   /*
   if( func_name.equals( "Sin" ) == true )
   return sindiff( argument ) ;
   
   if( func_name.equals( "Sin" ) == true )
   return sindiff( argument ) ;
   
   if( func_name.equals( "Sin" ) == true )
   return sindiff( argument ) ;
   
   if( func_name.equals( "Sin" ) == true )
   return sindiff( argument ) ;
   
   if( func_name.equals( "Sin" ) == true )
   return sindiff( argument ) ;
   
   if( func_name.equals( "Sin" ) == true )
   return sindiff( argument ) ;
   
   if( func_name.equals( "Sin" ) == true )
   return sindiff( argument ) ;
   
   if( func_name.equals( "Sin" ) == true )
   return sindiff( argument ) ;
   
   
   if( func_name.equals( "Sin" ) == true )
   return sindiff( argument ) ;
   
   
   if( func_name.equals( "Sin" ) == true )
   return sindiff( argument ) ;
   
   
   if( func_name.equals( "Sin" ) == true )
   return sindiff( argument ) ;
   
   if( func_name.equals( "Sin" ) == true )
   return sindiff( argument ) ;
   
   if( func_name.equals( "Sin" ) == true )
   return sindiff( argument ) ;
   
   if( func_name.equals( "Sin" ) == true )
   return sindiff( argument ) ;
   
   if( func_name.equals( "Sin" ) == true )
   return sindiff( argument ) ;
   
   
   if( func_name.equals( "Sin" ) == true )
   return sindiff( argument ) ;
   */
   
   //This is where you add new function calls 
   
   System.out.println( "ERRORRRRRRRRRRRRRRRR!!!!" ) ;
   System.out.println( "This error occured because function " + func_name + " does not exist" ) ;
   
   return null ;
}







private static String[] getOperands( String func , int operator_position , String operator_sign )
{
    //System.out.println( "In getOperands " ) ;
 //parathesis and bracket i.e () and [] should have their own function for computing the
    //next matching [ , ] , ( , ) .........
    
  String operands[] = new String[2] ;  
    
  if( operator_sign.equals( "^" ) == true )
  {
   
      int startBaseIndex = -1 ;
      int endPowerIndex = -1 ;
      
      if( ( "" + func.charAt(operator_position - 1)).equals( ")" ) == true )
      {
        startBaseIndex = getMatching( "(" , operator_position - 1 , "SEARCH LEFT" , func ) ;
        operands[0] =  func.substring( startBaseIndex , operator_position ) ;//func.substring( startBaseIndex + 1 , operator_position - 1 ) ;
        
      }
      else    
      if( ( "" + func.charAt(operator_position - 1)).equals( "]" ) == true )
      {
      startBaseIndex = getMatching( "[" , operator_position - 1 , "SEARCH LEFT" , func ) ;    
      startBaseIndex-- ;
      while( startBaseIndex >= 0 )
      {
        if( ( "" + func.charAt(startBaseIndex)).equals( "[" ) == true || ("" + func.charAt(startBaseIndex)).equals( "(" ) == true || ( "" + func.charAt(startBaseIndex)).equals( "/" ) == true || ( "" + func.charAt(startBaseIndex)).equals( "*" ) == true || ( "" + func.charAt(startBaseIndex)).equals( "+" ) == true || ( "" + func.charAt(startBaseIndex)).equals( "-" ) == true || ( "" + func.charAt(startBaseIndex)).equals( "^" ) == true)
        break ; 
       
        startBaseIndex-- ;
        
      }
      operands[0] = func.substring( startBaseIndex + 1 , operator_position ) ; // Need to find when function starts not correct yet!
      
      
      }
      else
      {
                  
         int i = operator_position - 1 ;
         while( i >= 0 )
         {
         if( ( "" + func.charAt(i)).equals( "[" ) == true || ("" + func.charAt(i)).equals( "(" ) == true || ( "" + func.charAt(i)).equals( "/" ) == true || ( "" + func.charAt(i)).equals( "*" ) == true || ( "" + func.charAt(i)).equals( "+" ) == true || ( "" + func.charAt(i)).equals( "-" ) == true || ( "" + func.charAt(i)).equals( "^" ) == true)
         break ;
          
         i-- ;
         }
          
         operands[0] = func.substring( i + 1 , operator_position ) ;
          
      }
      
      
      
       endPowerIndex = operator_position + 1 ;


       while( endPowerIndex < func.length() )
       {

         if( ( "" + func.charAt(endPowerIndex)).equals( "^" ) == true )
         endPowerIndex++ ;
         else
         {

         if( ( "" + func.charAt(endPowerIndex)).equals( "(" ) == true )  
         endPowerIndex = getMatching( ")" , endPowerIndex , "SEARCH RIGHT" , func ) ;   
         else
         if( isStartOf_SpecialFunction( endPowerIndex , func ) == true )
         endPowerIndex = getMatching( "]" , func.indexOf( "[" , endPowerIndex ) , "SEARCH RIGHT" , func ) ;   
         else
            if( ( "" + func.charAt(endPowerIndex)).equals( "]" ) == true || ( "" + func.charAt(endPowerIndex)).equals( ")" ) == true || ( "" + func.charAt(endPowerIndex)).equals( "/" ) == true || ( "" + func.charAt(endPowerIndex)).equals( "*" ) == true || ( "" + func.charAt(endPowerIndex)).equals( "+" ) == true || ( "" + func.charAt(endPowerIndex)).equals( "-" ) == true ) 
            break ;
         
         endPowerIndex++ ;
         
         }

       }

        operands[1] = func.substring( operator_position + 1 , endPowerIndex ) ;
        return operands ;


  
   }
  
  
  

  //***********************************************************************************************
  //************************************************************************************************
  if( operator_sign.equals( "*" ) == true || operator_sign.equals( "/" ) == true )
{
      
      int operand1 = -1 ;
      int operand2 = -1 ;

            
      operand1 = operator_position - 1 ;
      
while( operand1 >= 0 )
{

if( ( "" + func.charAt(operand1)).equals( "^" ) == true || ( "" + func.charAt(operand1)).equals( "/" ) == true || ( "" + func.charAt(operand1)).equals( "*" ) == true )
operand1-- ;
else
{
if( ("" + func.charAt( operand1 )).equals( ")" ) == true )
operand1 = getMatching( "(" , operand1 , "SEARCH LEFT" , func ) ; 
else
if( ("" + func.charAt( operand1 )).equals( "]" ) == true )
operand1 = getMatching( "[" , operand1 , "SEARCH LEFT" , func ) ; 
else
if( ( "" + func.charAt(operand1)).equals( "(" ) == true || ( "" + func.charAt(operand1)).equals( "[" ) == true || ( "" + func.charAt(operand1)).equals( "+" ) == true || ( "" + func.charAt(operand1)).equals( "-" ) == true ) 
break ;

operand1-- ;

}


}

operands[0] = func.substring( operand1 + 1 , operator_position ) ;


operand2 = operator_position + 1 ;
      
while( operand2 < func.length() )
{

if( ( "" + func.charAt(operand2)).equals( "^" ) == true )
operand2++ ;
else
{
if( ("" + func.charAt( operand2 )).equals( "(" ) == true )
operand2 = getMatching( ")" , operand2 , "SEARCH LEFT" , func ) ; 
else
if( ("" + func.charAt( operand2 )).equals( "[" ) == true )
operand2 = getMatching( "]" , operand2 , "SEARCH LEFT" , func ) ; 
else
if( ( "" + func.charAt(operand2)).equals( "]" ) == true || ( "" + func.charAt(operand2)).equals( ")" ) == true || ( "" + func.charAt(operand2)).equals( "+" ) == true || ( "" + func.charAt(operand2)).equals( "-" ) == true || ( "" + func.charAt(operand2)).equals( "/" ) == true || ( "" + func.charAt(operand2)).equals( "*" ) == true ) 
break ;


operand2++ ;

}


}


operands[1] = func.substring( operator_position + 1 , operand2 ) ;
return operands ;

}
  
  
  
  
  //*****************************************************************************************************
  //****************************************************************************************************
  
  
  
  
if( operator_sign.equals( "+" ) == true || operator_sign.equals( "-" ) == true )
{
      
      int operand1 = -1 ;
      int operand2 = -1 ;

            
      operand1 = operator_position - 1 ;
      
while( operand1 >= 0 )
{

if( ( "" + func.charAt(operand1)).equals( "^" ) == true || ( "" + func.charAt(operand1)).equals( "/" ) == true || ( "" + func.charAt(operand1)).equals( "*" ) == true || ( "" + func.charAt(operand1)).equals( "+" ) == true || ( "" + func.charAt(operand1)).equals( "-" ) == true )
operand1-- ;
else
{
if( ("" + func.charAt( operand1 )).equals( ")" ) == true )
operand1 = getMatching( "(" , operand1 , "SEARCH LEFT" , func ) ; 
else
if( ("" + func.charAt( operand1 )).equals( "]" ) == true )
operand1 = getMatching( "[" , operand1 , "SEARCH LEFT" , func ) ; 
else
if( ( "" + func.charAt(operand1)).equals( "(" ) == true || ( "" + func.charAt(operand1)).equals( "[" ) == true ) 
break ;

operand1-- ;

}


}


operands[0] = func.substring( operand1 + 1 , operator_position ) ;


operand2 = operator_position + 1 ;
      
while( operand2 < func.length() )
{

if( ( "" + func.charAt(operand2)).equals( "^" ) == true || ( "" + func.charAt(operand2)).equals( "/" ) == true || ( "" + func.charAt(operand2)).equals( "*" ) == true)
operand2++ ;
else
{
if( ("" + func.charAt( operand2 )).equals( "(" ) == true )
operand2 = getMatching( ")" , operand2 , "SEARCH LEFT" , func ) ; 
else
if( ("" + func.charAt( operand2 )).equals( "[" ) == true )
operand2 = getMatching( "]" , operand2 , "SEARCH LEFT" , func ) ; 
else
if( ( "" + func.charAt(operand2)).equals( "]" ) == true || ( "" + func.charAt(operand2)).equals( ")" ) == true || ( "" + func.charAt(operand2)).equals( "+" ) == true || ( "" + func.charAt(operand2)).equals( "-" ) == true ) 
break ;

operand2++ ;

}


}


operands[1] = func.substring( operator_position + 1 , operand2 ) ;
return operands ;
 
      
      
}
  
  
  
  System.out.println( "Error in get operands " ) ;
  System.out.println( "Error was caused by the operator given wasn't + * / - ^ probably function was incorrectly entered " ) ;
  return null ; // throw any error invalid operator argument!!!
    
}



private static boolean isStartOf_SpecialFunction( int index , String expr )
{
    
   String func_names[] = getFunctions() ;
   int i = 0 ;
   while( i < func_names.length )
   {
       
     if( expr.startsWith( func_names[i] , index ) == true ) 
     return true ; 
      
     i++ ;
   }
    
   return false ; 
    
}




private static int getMatching( String operator_to_match , int start_position , String direction , String function ) 
{
    //Think about changeing this function to the same function but only two parameters the int start_position ,String function
    //the other terms direction and operator_to_match can be determined by the other two parameters.
    //Must think about this !!!!!!!!!!!! important
  if( ( "" + function.charAt( start_position )).equals( ")" ) == true )
  {
      //get the matching (
       
        int open_par = 0 ;  // (
        int close_par = 1 ; // )
        int i = start_position - 1 ;
        while( i >= 0 )
        {
            
                       
           if( ("" + function.charAt( i )).equals( ")" ) == true )
           close_par++ ;
            
           if( ("" + function.charAt( i )).equals( "(" ) == true )
           open_par++ ;
         
           if( close_par == open_par )
           break ;
          
           i-- ;
       
        
    
       } 
    
       return i ;
        
        
  }
 
  
  if( ( "" + function.charAt( start_position )).equals( "(" ) == true )
  {
      
     //get the matching )
       
        int open_par = 1 ;  // (
        int close_par = 0 ; // )
        int i = start_position + 1 ;
        while( i < function.length() )
        {
            
                       
           if( ("" + function.charAt( i )).equals( ")" ) == true )
           close_par++ ;
            
           if( ("" + function.charAt( i )).equals( "(" ) == true )
           open_par++ ;
         
           if( close_par == open_par )
           break ;
          
           i++ ;
       
        
    
        } 
    
   
        return i ;
  
  
  }
  
  
 
  
 if( ( "" + function.charAt( start_position )).equals( "]" ) == true )
 {
      //get the matching [
       
        int open_bracket = 0 ;  // [
        int close_bracket = 1 ; // ]
        int i = start_position - 1 ;
        while( i >= 0 )
        {
            
                       
           if( ("" + function.charAt( i )).equals( "]" ) == true )
           close_bracket++ ;
            
           if( ("" + function.charAt( i )).equals( "[" ) == true )
           open_bracket++ ;
         
           if( close_bracket == open_bracket )
           break ;
          
           i-- ;
       
        
    
       } 
    
       return i ;
        
        
 }
 
  
  if( ( "" + function.charAt( start_position )).equals( "[" ) == true )
  {
      
     //get the matching ]
       
        int open_bracket = 1 ;  // [
        int close_bracket = 0 ; // ]
        int i = start_position + 1 ;
        while( i < function.length() )
        {
            
                       
           if( ("" + function.charAt( i )).equals( "]" ) == true )
           close_bracket++ ;
            
           if( ("" + function.charAt( i )).equals( "[" ) == true )
           open_bracket++ ;
         
           if( close_bracket == open_bracket )
           break ;
          
           i++ ;
       
        
    
        } 
    
   
        return i ;
  
  
  }
  
  
  return -1 ;  
}



private static String isKNOWN_diff( String func ) 
{
    System.out.println( "In  isKNOWN_diff " ) ;
   // String func = expr.trim() ; // don't think I need to trim 99.9999999999999999...% sure of that :)
    
    String derivative = diff_Table.getDeriv_OF( func ) ;
    
    if( derivative.equals( "NOT IN TABLE" ) != true )
    return derivative ;
   //need to erase!!! /////////////////////////////
    //derivative = diff_Table.getDeriv_OF( func.substring( 1 , func.length() ) ) ; 
    
    //if( derivative.equals( "NOT IN TABLE" ) != true )
    //return derivative ;
    
    
    //derivative = diff_Table.getDeriv_OF( "(" + func + ")" ) ;
    
    //if( derivative.equals( "NOT IN TABLE" ) != true )
    //return derivative ;
    
    
  ////////////////////////////////////////  
    if( isConstantExpression( func ) == true ) // May need to change back to isConstant dont think so though!
    return "0" ;
    
    if( isPrimitiveVarible( func ) == true ) 
    return "1" ;    
    
    String basic_functions[] = getFunctions() ;
    
    int i = 0 ;
    
    int j = skipPar( func , 0 ) ; // needed if user enters in expression like (Cos[x]) need to fix not fully correct
    
    
    while( i < basic_functions.length )
    {
       if( func.startsWith( basic_functions[i] , j ) == true )   
       break ; 
    
       i++ ; 
        
    }
    System.out.println( "iiiiiiiiiiiiiiiiiiiiiiiii " + i ) ;
    if( i == basic_functions.length )
    {

        //Here is where you check the diff array for a given functions derivative!!!
       // if not found return "NO" else return the derivative!!!        
        //New Comment is that the above to lines are not true because the else can be not elementary
        //but the Table could contain it !!!!
        //System.out.println( "Not an elementary exp , trig , log , hypertrigfunc , inver trig , or any other basic transcendental function " ) ;    
        //System.out.println( "Also is Not a constant and  not a primitive varible" ) ;
        //System.out.println( "Also not already calculated so, is either a new non-basic expression or an ERROR Has Occur some where! " ) ;    
       
        return "NO" ;
    
    }
    else
    {
        //is correct but may want to code better !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      int index_bracket = getMatching( "]" , func.indexOf( "[" , j ) , "SEARCH RIGHT" , func ) ;
      if( index_bracket != func.length()-1  )
      {
       int lastBracket = skipPar( func , index_bracket + 1 ) ;
       if( (index_bracket + lastBracket) != func.length()-1 )   
       return "NO" ;
      }
      
      /*
      int index_bracket = func.lastIndexOf( "]" ) ;
      
      
      if( index_bracket + 1 < func.length() - 1 )
      {
        int lastBracket = skipPar( func , index_bracket + 1 ) ;
        if( lastBracket != func.length() - 1 )
        return "NO" ;
      
      }
       
      */
      
      String arg = func.substring( func.indexOf( "[" ) + 1, index_bracket ) ; 
      
      switch( i )
      {
          case 0: 
              if( isPrimitiveArgument( arg ) == true )
              return "Cosh[" + var + "]" ; 
              else
              return "NO" ;
             
           
          case 1: 
              if( isPrimitiveArgument( arg ) == true )
              return "Sinh[" + var + "]" ; 
              else
              return "NO" ;
        
          case 2: 
              if( isPrimitiveArgument( arg ) == true )
              return "Sech[" + var + "]^2" ; 
              else
              return "NO" ;
              
          case 3: 
              if( isPrimitiveArgument( arg ) == true )
              return "-1*Sech[" + var + "]*" + "Tanh[" + var + "]" ; 
              else
              return "NO" ;
              
              
          case 4: 
              if( isPrimitiveArgument( arg ) == true )
              return "-1*Csch[" + var + "]^2" ; 
              else
              return "NO" ;
              
              
          case 5: 
              if( isPrimitiveArgument( arg ) == true )
              return "-1*Csch[" + var + "]*" + "Coth[" + var + "]" ; 
              else
              return "NO" ;
           //******************************************* Trig function start here  
          case 6: 
              if( isPrimitiveArgument( arg ) == true )
              return "Cos[" + var + "]" ; 
              else                  
              return "NO" ;
              
              
              
           case 7: 
              if( isPrimitiveArgument( arg ) == true )
              return "-1*Sin[" + var + "]" ; 
              else
              return "NO" ;
              
              
           case 8: 
              if( isPrimitiveArgument( arg ) == true )
              return "Sec[" + var + "]^2" ; 
              else
              return "NO" ;
              
              
           case 9: 
              if( isPrimitiveArgument( arg ) == true )
              return  "Sec[" + var + "]*" + "Tan[" + var + "]" ; 
              else
              return "NO" ;
              
              
           case 10: 
              if( isPrimitiveArgument( arg ) == true )
              return "-1*Csc[" + var + "]^2" ; 
              else
              return "NO" ;
              
              
           
           case 11: 
              if( isPrimitiveArgument( arg ) == true )
              return "-1*Csc[" + var + "]*" + "Cot[" + var + "]" ;
              else
              return "NO" ;
              
              
        //Arctrig and hyper  and archyper    
           case 12: 
              if( isPrimitiveArgument( arg ) == true )
              return "Cos[" + var + "]" ; 
              else
              return "NO" ;
              
           case 13: 
              if( isPrimitiveArgument( arg ) == true )
              return "Cos[" + var + "]" ; 
              else
              return "NO" ;
              
           case 14: 
              if( isPrimitiveArgument( arg ) == true )
              return "Cos[" + var + "]" ; 
              else
              return "NO" ;
              
           case 15: 
              if( isPrimitiveArgument( arg ) == true )
              return "Cos[" + var + "]" ; 
              else
              return "NO" ;
              
           case 16: 
              if( isPrimitiveArgument( arg ) == true )
              return "Cos[" + var + "]" ; 
              else
              return "NO" ;
              
           case 17: 
              if( isPrimitiveArgument( arg ) == true )
              return "Cos[" + var + "]" ; 
              else
              return "NO" ;
              
           case 18: 
              if( isPrimitiveArgument( arg ) == true )
              return "Cos[" + var + "]" ; 
              else
              return "NO" ;
              
           case 19: 
              if( isPrimitiveArgument( arg ) == true )
              return "Cos[" + var + "]" ; 
              else
              return "NO" ;
              
              
           case 20: 
              if( isPrimitiveArgument( arg ) == true )
              return "Cos[" + var + "]" ; 
              else
              return "NO" ;
              
              
           case 21: 
              if( isPrimitiveArgument( arg ) == true )
              return "Cos[" + var + "]" ; 
              else
              return "NO" ;
              
              
           case 22: 
              if( isPrimitiveArgument( arg ) == true )
              return "Cos[" + var + "]" ; 
              else
              return "NO" ;
              
              
           case 23: 
              if( isPrimitiveArgument( arg ) == true )
              return "Cos[" + var + "]" ; 
              else
              return "NO" ;
              
              //End of arc trig hyper ... 
             //begining of exp log ... 
          case 24: 
              if( isPrimitiveArgument( arg ) == true )
              return "Exp[" + var + "]" ; 
              else
              return "NO" ;
              
              
           case 25: 
              if( isPrimitiveArgument( arg ) == true )
              return "1/" + var  ; 
              else
              return "NO" ;
              
              
           case 26: 
              if( isPrimitiveArgument( arg ) == true )
              return "1/" + var  ; 
              else
              return "NO" ;
      
          default:
              System.out.println( "Error Occured varible i is not correct in code " ) ;
              System.out.println( "Probably someone tampered with the switch statment incorrectly when trying to modify" ) ;
      }
      
    }
   
    
    //Here is where you would add code to check for user defined functions!!!
    //Or special functions like gamma beta zeta and ....on and on ...
    return "NO" ;
    
       
}


private static int skipPar( String expr , int startpos )
{
    
    int i = startpos ;
    int j = 0 ;
    
    while( i < expr.length() )
    {
        
       if( expr.charAt(i) == '(' || expr.charAt(i) == ')' )
       j++ ; 
       else
       return j ;
       
       i++ ;
    }
    
    return j ;
    
}





private static boolean isConstantExpression( String expr )
{
 
 int i = 0 ;   
  
 if( expr.length() == 1 )
 {
  if( expr.equals( var ) == true )
  return false ;
 }
 
 //to make it so you can define your own varables must check $name$ (i.e check to see $
 //must code later!!!
 
 while( i < expr.length() )   
 {
   i = expr.indexOf( var , i ) ;  
   
   if( i == -1 )
   return true ;
   
   if( i - 1 >= 0 && i + 1 < expr.length() )
   {
   if( Character.isLetter( expr.charAt( i - 1 ) ) != true && Character.isLetter( expr.charAt( i + 1 ) ) != true ) 
   return false ;
   }
   else
   if( i == 0 && Character.isLetter( expr.charAt( i + 1 ) ) != true )
   return false ;
   else
   if( i == expr.length() - 1 && Character.isLetter( expr.charAt( i - 1 ) ) != true )
   return false ;   
       
   i++ ; 
     
 }
    
 return true ; 
 
 
}




private static boolean isConstant( String expr )
{
   
 try{
     Integer.parseInt( expr ) ; 
 }
 catch( Exception e )
 {
  return false ;   
 }
 return true ;
    
}


private static boolean isPrimitiveVarible( String expr ) 
{
    
     String arg = expr.trim() ;
     return isPrimitiveArgument( arg ) ;
    
}

//Need to make this alot better not sure if full correct and optimal yet
private static boolean isPrimitiveArgument( String arg )
{
    
  //arg = arg.trim() ; 
  
  if( arg.equals( var ) == true )
  return true ;
    
  if( arg.equals("") == true )
      return false ;
  
  
  if( justParInExpression( arg ) == true )
  return true ;
  
  return false ;
  
 /*
  
 StringTokenizer st1 = new StringTokenizer( arg , var );
 StringTokenizer st2 = new StringTokenizer( arg , "^" );
 StringTokenizer st3 = new StringTokenizer( arg , "[" );
 StringTokenizer st4 = new StringTokenizer( arg , "]" );
 StringTokenizer st = new StringTokenizer( arg );
 StringTokenizer st5 = new StringTokenizer( arg , "*" );
 StringTokenizer st6 = new StringTokenizer( arg , "/" );
 StringTokenizer st7 = new StringTokenizer( arg , "+" );
 StringTokenizer st8 = new StringTokenizer( arg , "-" );
 
 System.out.println( arg + " sddf f " + st1.countTokens() ) ;
 if( st1.countTokens() > 2 )
     return false ;

 
 if( st2.countTokens() > 1 || st3.countTokens() > 1 || st4.countTokens() > 1 || st5.countTokens() > 1)
     return false ;
 
 if( st6.countTokens() > 1 || st7.countTokens() > 1 || st8.countTokens() > 1 )
 return false ;
 
 if( st1.countTokens() != 0 ) //|| st1.countTokens() != 1 )
     return false ;
 
 return true ; 
 
*/
}


private static boolean justParInExpression( String arg )
{
 
int i = 0 ;    
while( i < arg.length() )
{
    
if( arg.charAt(i) == '*' || arg.charAt(i) == '+' || arg.charAt(i) == '-' || arg.charAt(i) == '/' || arg.charAt(i) == '[' || arg.charAt(i) == ']' || arg.charAt(i) == '^' ) 
return false ;

i++ ;    
    
}
    
return true ;    
    
    
}



private static class DiffTable {

private Hashtable d_table ;
 
public DiffTable()
{
  d_table = new Hashtable() ;  
}

public DiffTable( int initialCapacity )
{
  
    d_table = new Hashtable( initialCapacity ) ;
    
}

public DiffTable( int initialCapacity , float loadFactor )
{

   d_table = new Hashtable( initialCapacity , loadFactor ) ;
    
}

public void addDiff( String func , String derivative )
{
    
    
 d_table.put( func , derivative ) ;   
    
    
}
    

public String getDeriv_OF( String func )
{
   Object obj = d_table.get( func ) ;
   if( obj == null )
       return "NOT IN TABLE" ;
   
   return (String)d_table.get( func ) ;
    
}


public void removeDeriv( String func )
{
   d_table.remove( func ) ; 
}


public void clearTable()
{
  
   d_table.clear() ;
    
}


public int size()
{
 return d_table.size() ;    
}

public String toString()
{
    
 return d_table.toString() ;   
    
}


}

/*

private static class BasicSimplifer {
    
    
public boolean isPolynomialExpression( String expr )
{
    
 return true ;   
}
    
  
public String polySimplify( String poly )
{
    
  int i = 0 ;  
  while( i < poly.length() )
  {
   
      if( poly.charAt( i ) == '
         
      
  }
    
       
    
    
}


public static String SimplifyExpression( String expr )
{
   
  String operands[] = new String[2] ; 
  Sin[x]+7/(Sin[x]+7)^7  
  int i = 0 ;  
  while( i < expr.length() )
  {
   if( expr.charAt(i) == '*' )
   {
       
    getOperands( expr , i , "*" ) ;     
       
   }
    
   if( expr.charAt(i) == '/' )
   {
       
    operands = getOperands( expr , i , "/" ) ;     
    
    if( hasNoPowerSymbol(operands[1]) == true )
    {
     
     if( hasParAround( operands[1] ) == true )
     {
         
       trimPar( operands[1] )  
         
         
     }
        
        
    }
    
    
   }
   
   if( expr.charAt(i) == '+' )
   {
       
    getOperands( expr , i , "*" ) ;     
       
   }
   
   
   if( expr.charAt(i) == '-' )
   {
       
    getOperands( expr , i , "*" ) ;     
       
   }
   
  }
  
  
  
  return "jkfjf" ;
    
    
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

*/

public static void main( String args[] )
{

//Differentiation.parseExpression( " Sin[x] + Tan[x/4] / 5x * 9 - ( 5*(cos[x] - 7)^( 1/2) ) * (7+5) " ) ;
//System.out.println( Differentiation.diff( "Sin[ x ] + Cos[x] " ) ) ;  
//String opers[] = Differentiation.getOperands( "(4+Cos[Sin[(x+1)*cos[x]+1/4]])+4^Sin[6+7*Sin[x]^78+25/77*Cos[x]*x^(4+1/2)+9]" , 32 , "^" ) ;
//String opers1[] = Differentiation.getOperands( "6*Sin[x]^7/(Tan[x]+7)/Sin[x]*8" , 21 , "/" ) ;//Differentiation.getOperands( "5+Cos[x]^6^(Sin[x^7])^Cos[x]+7" , 8 , "^" ) ;
//String opers2[] = Differentiation.getOperands( "9+Cos[Sin[x+7^6+8]]+ArcSech[x^(Sin[x]+5)]^9" , 19 , "+" ) ;
//System.out.println( opers[0] + " <---> " + opers[1] ) ;
//System.out.println( opers1[0] + " <---> " + opers1[1] ) ;
//System.out.println( opers2[0] + " <---> " + opers2[1] ) ;
//System.out.println( Differentiation.adddiff( "Sin[ x ]" , "Cos[x] " ) ) ;
//System.out.println( Differentiation.powdiff( "x" , "6" ) ) ;
//System.out.println( diff("Sin[x]-Cos[x+7]*Exp[x]" ) ) ; 
//System.out.println( diff("x^x" ) ) ;
//System.out.println( diff( "x^2+x+23+7*x" ) );
//System.out.println( diff( "7+9+Cos[Sin[x+7]+x]" ) );
System.out.println( diff("(Sin[x]+2)^7" ) ) ;
System.out.println( diff( "Log[Log[x^2]]" ) );
//System.out.println( diff( "2*x^7" ) ) ;
//System.out.println( diff( "Sin[x^(5+4+9/7)]" ) ) ;
//System.out.println( diff( "Sin[x]+Tan[x]/5x-(((9*Sin[x^7*Cos[x]])*6)*8)*(5*(Cos[x]-7)^( 1/2))" ) ) ;
  //funct = "(Cos[x])+Tan[x]+Sec[x]+Sin[x]^6+(Cos[x])+Tan[x]+Sec[x]+Sin[x]^6" ;
  //System.out.println( diff( "(Cos[x])+Tan[x]+Sec[x]+Sin[x]^6+(Cos[x])+Tan[x]+Sec[x]+Sin[x]^6" ) ) ;
//System.out.println( diff( "(x^x^x+x+(Cos[Cot[x^Log[x]+7*x/Cos[x]]+7]^27)+(2/7)*x/8+Csc[x]^t+t^2/(t+1))^x/(t^8+7)+x^78+x^77+x^190+x^7+x^1+1" ) ) ;
//System.out.println( diff( "Cot[x]^Sec[x]+7*x" ) ) ;
//System.out.println( diff( "Cot[x]^(Sec[t]+7*t)" ) ) ;
//System.out.println( diff( "(Sin[x]+7)+8*x" ) ) ;
//System.out.println( diff( diff( diff( "100*99*x^98)" ) ) ) ) ;
 //System.out.println( diff( "((x^77)*(44+7)*x)^7+(Sin[x]^1)" ) ) ;
 //System.out.println( diff( "((x^7+(((8*x+7))*x)))" ) ) ;
// System.out.println( diff( "1-(-1*Sin[x])" ) ) ;
   // System.out.println( diff( "Sin[x-7]*Cos[(-1)*x]+7" ) ) ;
   ///System.out.println( diff( "Sin[t-7]^t+7777" ) ) ;
   //System.out.println( diff( "Cos[x]*Cot[x]^2" ) ) ;
   //System.out.println( diff( "1-(Cos[x+7]+8)" ) ) ;
     // System.out.println( diff( "(((Sin[x+7])))" ) ) ;
    //System.out.println( diff( "Cos[(-1)*x]" ) ) ; // take a look back at this later
    //System.out.println( diff( "Cos[-1*x]" ) ) ; // take a look back at this later
    //System.out.println( diff( "7-6+(((Sin[x+7])))" ) ) ;
    //System.out.println( diff( "Cos[(((-1)*x))+5+x]" ) ) ; 
   // System.out.println( diff( "(((((x)))))+" ) ) ; 
    //System.out.println( diff( "((x*7*6))+8*x*(x*x)*((x))" ) ) ; 
    //System.out.println( diff( "Sinh[x]" ) ) ;
    //try this function need to make less Par Come up!! x^7/9+Cos[x^t+x*t]
    System.out.println( diff( "Sin[x^(1/2)-x^2]" ) ) ;
    System.out.println( diff_Table ) ;
    //System.out.println( diff( diff( "3*x^2+Cos[x]^t" )) ) ;
    //System.out.println( diff( diff( diff( "100*x^99" ) ) ) ) ;    
    try{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String input_text = "" ;
    while( input_text.equalsIgnoreCase( "quit" ) == false )
    {
     
     System.out.println( "Enter a function or Type quit to exit the program" ) ;
     input_text = in.readLine() ;   
     System.out.println( "\n\nThe Derivitive of " + input_text + " is ---> " + diff( input_text ) ) ;   
         
            
    }
    
    }
    catch( Exception e ) 
    {
     System.out.println( "Error Occur while reading text! " ) ;   
    }
    
    
    
/*    
int j =0 ;
funct = "x^200" ;
String diff_s = diff( "x^200" ) ;
funct = diff_s ; 

while( j < 10 )
{
 diff_s = diff( diff_s ) ; 
 funct = diff_s ;
 j++ ;  
 
 
 System.out.println( "\n"+ j + " derivitive is "  + diff_s ) ;  
    
}
*/
System.out.println( "abcdefg varible is " + abcdefg ) ;
//System.out.println( diff( diff( "x^100+77*x^77+Sinh[x]+t^7" ) ) ) ;

System.exit(0) ;

} 



}
