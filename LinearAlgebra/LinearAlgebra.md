
The Matrix_Operations.java needs a little more work in adding functionality for cool things but eventually its on its way.
<br>
<br>
Now support in Matrix_String.java for computing resultant and determinants. Resultants give rise to being able to compute Discriminants of a polynomials as well.
These are important quantities not usually talked about in most courses discriminats in the case of quadratic polynomials is just b^2-4ac. But knowing how to compute the discriminant generally for any polynomial is neat. Discriminants usually are computed by computing the resultant and the resultant from the determinant of the sylvestries matrix. So Determinants , Resultants and Discriminants are very closely linked however most courses only focus on Determinants of matrices
<br>
<br>
This code allows one to explore computing these 3 quantities and the numbers these quantities yield.
<br>
I admit this is old code that i add-on to and there more efficient ways i should have done stuff.
Like for determinants instead of gaussians elimination i could have used LU decomposition to yields slightly better runtime in computing big determinants.
<br>
I may do a redo of some linear algebra code to focus just on different decompositions LU , QR , singular value decomposition , jordans , ...etc someday to name a few.
