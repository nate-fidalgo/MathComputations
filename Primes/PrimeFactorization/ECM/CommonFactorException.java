import java.math.BigInteger;


//A custom RuntimeException used in the EllipticCurve class when one cannot find the modinverse of the slope expression
//This indicates usually we had successfully found a nontrivial common factor for are given number
public class CommonFactorException extends RuntimeException {

	/**
	 * 
	 */
	private BigInteger commonfactor = null ; //used to pass the common factor back thru the elliptic curve methods
	public CommonFactorException(String errorMessage) {
        super(errorMessage);
    }
	
	public void setVal( BigInteger cfactor )
	{
		commonfactor = cfactor ;
	}
	
	public BigInteger getVal()  //used to get the common factor from this runtime exception
	{
		return commonfactor ;
	}
	
	private static final long serialVersionUID = 380533724623851284L;

}
