

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;


public class ConvexHull_GUI extends JFrame implements ActionListener , MouseListener , MouseMotionListener
{

private Vector points ;
private boolean calculating_ButtonPressed ;
private boolean alreadyCalculated ;
private boolean refreshing ;
private Vector results ;
private Container container;
private ConvexHull hull = new ConvexHull() ;

public ConvexHull_GUI()
{

   super( "ConvexHull Solver" ) ;
   container = getContentPane() ;
 
   container.setLayout( new FlowLayout() ) ;

   JButton calculate_button = new JButton( "Calculate Hull" ) ;
   calculate_button.addActionListener( this ) ;
   container.add( calculate_button ) ;


   JButton refresh_button = new JButton( "ReSet" ) ;
   refresh_button.addActionListener( this ) ;
   container.add( refresh_button ) ;


   container.addMouseListener( this ) ;
   //container.addActionListener( this ) ;
   
   points = new Vector() ;
   results = new Vector() ;
   calculating_ButtonPressed = false ;
   alreadyCalculated = false ;
   refreshing = false ;
   
   setSize( 300 , 400 ) ;
   show() ;

}

public void actionPerformed( ActionEvent e )
{

   if( e.getActionCommand() == "Calculate Hull" )
   {
    System.out.println( "Got in ActionPerformed pushed button calculate " ) ;
    results.clear() ;
    Point points_to_array[] = new Point[points.size()];
    //((JButton)e.getSource()).setEnabled(false);
    
    
    for( int i = 0 ; i < points.size() ; i++ )
     points_to_array[i] = (Point)points.get(i) ;   
    
    Point result[] = hull.calculate_ConvexHull( points_to_array ) ;
    //Must have some way to get results from graphics!!!!
    
    for( int i = 0 ; i < result.length ; i++ )
        System.out.println( "Printing Results array [ " + result[i].get_x() + "   " + result[i].get_y() + " ] " ) ;
    
    for( int i = 0 ; i < result.length ; i++ )
    results.addElement( result[i] ) ;    

    alreadyCalculated = true ; 
    calculating_ButtonPressed = true ;

    repaint() ;

   }


   if( e.getActionCommand() == "ReSet" )
   {
      System.out.println( "Got in ActionPerformed pushed button reset " ) ;
      alreadyCalculated = false ;
      calculating_ButtonPressed = false ;
      points.clear() ;
      refreshing = true ;
      repaint() ;

   }



}


public void mouseClicked( MouseEvent e )
{
    System.out.println( "Got in MouseClicked coordinates are " +  e.getX() + "    " + e.getY() ) ;
   if( alreadyCalculated == false )
   { 
   System.out.println( "Got in here in@@@@@@@ " ) ;
   Point newpoint = new Point( e.getX() , e.getY() ) ; 
   if( inPointSet( newpoint ) == false )
   { 
       points.addElement( newpoint );
       repaint() ;
   }
   
   }
   

}


public boolean inPointSet( Point p ) 
{

   Point temp_p = null ;

   for( int i = 0 ; i < points.size() ; i++ )
   {
     
     temp_p = (Point)points.elementAt( i ) ;
     
     if( temp_p.get_x() == p.get_x() && temp_p.get_y() == p.get_y() )
     return true ;


   }

   return false ;

}




public void paint( Graphics g )
{

  super.paint(g) ;
  g.setColor(Color.RED) ;  
  
  
  if( alreadyCalculated == true )
  {
     System.out.println( "Got in Paint at AlreadyCalculated" ) ;
     for( int i = 0 ; i < points.size() ; i++ )  
     g.fillOval( ((Point)points.get( i )).get_x() , ((Point)points.get(i)).get_y() , 7 , 7 ) ; 

     for( int i = 0 ; i < results.size() ; i += 2 )
     g.drawLine( ((Point)results.get(i)).get_x() , ((Point)results.get(i)).get_y() , ((Point)results.get(i + 1)).get_x() , ((Point)results.get(i + 1)).get_y() ) ;
     //g.drawLine( 55 , 30 ,  22 , 5 ) ;
  
    
  
  }



  if( alreadyCalculated == false && calculating_ButtonPressed == false && refreshing == true )
  {
    for( int i = 0 ; i < results.size() ; i++ )
        System.out.println( "" + ((Point)results.get(i)).get_x() + " " + ((Point)results.get(i)).get_y() ) ;
    //g.clearRect( 70  , 70 , 70  , 70 ) ;
    refreshing = false ;
    
  }
  

  if( alreadyCalculated == false && calculating_ButtonPressed == false && refreshing == false )
  {

    for( int i = 0 ; i < points.size() ; i++ )  
    g.fillOval( ((Point)points.get( i )).get_x() , ((Point)points.get(i)).get_y() , 7 , 7 ) ; 

  }




}




public void mousePressed(MouseEvent e){}

public void mouseReleased(MouseEvent e){}
public void mouseEntered(MouseEvent e){}
public void mouseExited(MouseEvent e){}
public void mouseDragged(MouseEvent e){}

public void mouseMoved(MouseEvent e){}

public static void main( String args[] )
{
    
      ConvexHull_GUI convexhull = new ConvexHull_GUI() ;

      convexhull.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
    
    
    
}




}
