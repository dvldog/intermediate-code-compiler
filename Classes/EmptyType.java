/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class EmptyType extends Type{

	String emptyVal;
	
	EmptyType(String n)
	{
		emptyVal = n;
	}
	/* (non-Javadoc)
	 * @see Type#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see Type#getType()
	 */
	@Override
	String getType() {
		// TODO Auto-generated method stub
		return emptyVal;
	}

}
