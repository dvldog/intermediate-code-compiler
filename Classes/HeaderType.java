/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class HeaderType extends Type{

	String type;
	
	HeaderType(String t)
	{
		type = t;
	}
	
	/* (non-Javadoc)
	 * @see Type#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		
		Lexical_Analyzer.display(type);
		
	}	
	
	String getType()
	{
		return type;
	}

}
