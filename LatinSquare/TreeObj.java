import java.util.Vector ;

public abstract class TreeObj {

protected TreeObj parent = null ; //if its the root else parent
protected Vector<TreeObj> objs ; //child nodes for this treeobj
protected boolean valid = true ;
protected Object value = null ;
protected boolean visit = false ;

public TreeObj(TreeObj pobj , Object valueofObj )
{
parent = pobj ;
objs = new Vector<TreeObj>() ;
valid = true ;
value = valueofObj ;
this.visit = false ;
}

public void addChild( TreeObj obj ) 
{
obj.parent = this ;
objs.addElement(obj) ;

}

public TreeObj getChildAt(int childIndex) 
{
int sz = this.getChildCount() ;
if( sz == 0 || childIndex + 1 > sz)
return null ;

return objs.elementAt( childIndex ) ;

}

public int getChildCount() 
{
return objs.size() ;

}

public TreeObj getParent() 
{
return parent ;
}

public boolean isLeaf() 
{
if( objs.size() == 0 )
return true ;

return false ;
}


public boolean isValidObject()
{
return valid ;
}

public void setifValidObject( boolean b ) 
{
valid = b ;
}

public void trashInvalidObject()
{
if( this.isValidObject() == false )
{
objs.removeAllElements() ;
return ;
}

for( int i = 0 ; i < objs.size() ; i++ )
if( objs.elementAt(i).isValidObject() == false )
objs.remove(i) ; 

}

public Object getValue() 
{
return this.value ;
}

public void setValue( Object val )
{
this.value = val ;
}

public void setVisited( boolean b )
{
	this.visit = b ;
	
}

public boolean getVisited()
{
	return visit ;
}

//public abstract void computeifValidObj( Object val ) ;
public abstract void printTreeObj() ;
// how deep / amount of levels / how many elements in tree. maybe create a method for this to keep track of!!!
//uses instanceOf to convert from the proper type be it arrays , other classes ,...etc
}