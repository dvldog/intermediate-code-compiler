import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class EmptyExpList extends ExpList{
	
	String identifier;
	
	EmptyExpList(String id)
	{
		identifier = id;
	}
	/* (non-Javadoc)
	 * @see ExpList#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
	}
	/* (non-Javadoc)
	 * @see ExpList#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		// Get the return type of the variable or assignment statement
		String evaluation = parameterVar.get(identifier);
		if(evaluation == null)
		{
			evaluation = funType.get(identifier);
		}
		
		return evaluation;
	}
	/* (non-Javadoc)
	 * @see ExpList#getString()
	 */
	@Override
	String getString() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see ExpList#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
	}
	
}
