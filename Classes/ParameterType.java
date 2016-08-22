/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class ParameterType extends Type{

	String type;
	
	ParameterType(String t)
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
	/* (non-Javadoc)
	 * @see Type#getType()
	 */
	@Override
	String getType() {
		// TODO Auto-generated method stub
		return type;
	}

}
