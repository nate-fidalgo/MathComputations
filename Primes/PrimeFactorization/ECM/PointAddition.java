
/**
 * 
 * @author nate
 * This is an interfaces for any mathematical object that has a point addition operation defined on it.
 * This is the case for elliptic curves but doesn't necessarily stop there.
 * One could discover Arithmetic on higher deg algebraic curves or even more general object that have point arithmetic defined on it.
 *
 * Elliptic curves just being the most important so far for defining a group operation on.
 * Though other higher deg algebraic curves other then elliptic curves may exist with neat group operations defined on them.
 * But the question is still is there any out there that are more useful then elliptic curves ?
 * And if so which ones are they and how do you define there group point arithmetic operation ???
 * 
 * Neat questions :)
 */


public interface PointAddition {

	//Define how you had two points with this mathematical object
	//See class EllipticCurve for an example
	public Point $(Point M , Point N) ;
	
}
