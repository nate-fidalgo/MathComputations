/*
 * 
 * The purpose of this thread is to allow a mechanism to dump the where in the computation
 * it is for the last Lychrel calculation 
 * It is nice to beable to pick up where you left off :)
 * 
 * 
 */
public class DumpWork extends Thread {

	public void run()
	{
		if( Lychrel.currentLychrel == null )
		return ;
		System.out.println("dggdgdg");
		System.out.println( "For Number = " + Lychrel.currentLychrel[2]) ;
		System.out.println( "Cycles taken = " + Lychrel.currentLychrel[1]) ;
		System.out.print("Lychrel value = " + Lychrel.currentLychrel[0]) ;
		
	}
	
}
