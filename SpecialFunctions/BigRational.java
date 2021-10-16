import java.math.BigInteger;

/**
 * This class is an add on to java.math package in the sdk of java
 * One has a BigInteger and BigDecimal but not an in between for rational numbers in symbolic form.
 * BigRational helps when having a "Big" version for rationals which is inbetween BigInteger and BigDecimal classes
 *
 */

public class BigRational {

	private BigInteger num ;
	private BigInteger den ;
	private int sign = 0 ;
	
	public BigRational(BigInteger num , BigInteger den )
	{
		if( num.signum() == -1 && den.signum() == -1 )
		sign = 1 ;
		else if( num.signum() == 1 && den.signum() == 1 )
		sign = 1 ;
		else 
		sign = -1 ;
			
		this.num = num.abs() ;
		this.den = den.abs() ;
		
	}
	
	public BigInteger getNum()
	{
		return num ;
	}
	
	public BigInteger getDen()
	{
		return den ;
	}
	
	public int getSign()
	{
		return sign ;
	}
	
	public BigRational add( BigRational r2 )
	{
		
		
		BigInteger lcm = ( this.getDen().multiply(r2.getDen()) ).divide( this.getDen().gcd(r2.getDen()) )  ;
		BigInteger a = lcm.divide(this.getDen()) ;
		BigInteger b = lcm.divide(r2.getDen()) ;
		BigInteger numresult = null ;
		if( sign == r2.getSign() && sign == 1 )
		numresult = ( a.multiply(this.getNum()) ).add( b.multiply(r2.getNum()) ) ;
		else if( sign == r2.getSign() && sign == -1 )
		numresult = ( ( a.multiply(this.getNum()) ).add( b.multiply(r2.getNum()) ) ).multiply( new BigInteger("-1") )  ;
		else if( sign == 1 && r2.getSign() == -1 )
		numresult = ( a.multiply(this.getNum()) ).subtract( b.multiply(r2.getNum()) ) ;
		else if( sign == -1 && r2.getSign() == 1 )
		numresult = ( (a.multiply(this.getNum())).multiply(new BigInteger("-1") ) ).add( b.multiply(r2.getNum()) ) ;
			
			
		BigInteger denresult = lcm ;
		
		return new BigRational(numresult , denresult ) ;
		
	}
	
	
	public BigRational mult( BigRational r2 )
	{
		
		BigInteger numresult = this.getNum().multiply(r2.getNum()) ;
		BigInteger denresult = this.getDen().multiply(r2.getDen()) ;
		BigInteger gcd = numresult.gcd(denresult) ;
		numresult = numresult.divide( gcd ) ;
		denresult = denresult.divide( gcd ) ;
		
		if( sign == r2.getSign())
		return new BigRational(numresult , denresult ) ;
		
		if( sign != r2.getSign())
		return new BigRational( numresult.multiply( new BigInteger("-1" ) ) , denresult ) ;	
		
		return null ;
	}
	
	
	public BigRational div( BigRational r2 )
	{
		
		BigInteger numresult = this.getNum().multiply(r2.getDen()) ;
		BigInteger denresult = this.getDen().multiply(r2.getNum()) ;
		
		BigInteger gcd = numresult.gcd(denresult) ;
		numresult = numresult.divide( gcd ) ;
		denresult = denresult.divide( gcd ) ;
		
		if( sign == r2.getSign())
		return new BigRational(numresult , denresult ) ;
		
		if( sign != r2.getSign() )
		return new BigRational( numresult.multiply( new BigInteger("-1" ) ) , denresult ) ;		
		
		return null ;
		
	}
	
	public String toString()
	{
		if( sign == 1 )
		return this.getNum() + " / " + this.getDen() ; 
		else
		return "-" + this.getNum() + " / " + this.getDen() ;
		
	}
	
	//This is a debugging main dont use just import this class and use in your main!!!
	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*
		BigRational r = new BigRational(new BigInteger("10") ,new BigInteger("1") ) ;
		BigRational r2 = new BigRational(new BigInteger("-3") , new BigInteger("2") ) ;
		BigRational r3 = r.mult(new BigRational(new BigInteger("1") , new BigInteger("6") ) ) ;
		System.out.println( r3) ;
		System.out.println( r2.add(r3)) ;
		//BigRational rr = new BigRational(new BigInteger("-3") , new BigInteger("2") ) ;
		
	//	System.out.println( r.add(rr) ) ;
	//	BigRational rrr = r.add(rr) ;
	//	System.out.println( rrr.div(new BigRational(new BigInteger("-5") , new BigInteger("1") ) ) ) ;
		*/
	}

}
