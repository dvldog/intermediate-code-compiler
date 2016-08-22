import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class HeaderFunName extends FunName{

	Id id;
	
	HeaderFunName(Id i)
	{
		id = i;
	}
	
	/* (non-Javadoc)
	 * @see FunName#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		
		id.printParseTree(indent);
		
	}

	/* (non-Javadoc)
	 * @see FunName#getId()
	 */
	@Override
	String getId() {
		// TODO Auto-generated method stub
		return id.getId();
	}

	/* (non-Javadoc)
	 * @see FunName#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		return id.TypeEval(funType, parameterVar, variablePos);
	}

	/* (non-Javadoc)
	 * @see FunName#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
		id.emitInstructions();
		
	}

}
