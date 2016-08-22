/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class Parameter extends ParameterList{

	Type type;
	Id id;
	
	Parameter(Type t, Id i)
	{
		type = t;
		id = i;
	}
	/* (non-Javadoc)
	 * @see ParameterList#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		String indent1 = indent + " ";
		Lexical_Analyzer.display(indent1 + indent1.length() + " ");
		type.printParseTree(indent);id.printParseTree(indent);
		
	}

}
