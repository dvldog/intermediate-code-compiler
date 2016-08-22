/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class MultipleAssignmentParameter extends ParameterList{

	ParameterList parameterlist;
	Parameter parameter;
	
	MultipleAssignmentParameter(ParameterList pl, Parameter p)
	{
		parameterlist  = pl;
		parameter = p;
	}
	
	/* (non-Javadoc)
	 * @see ParameterList#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		
		parameter.printParseTree(indent);
		parameterlist.printParseTree(indent);
	}

}
