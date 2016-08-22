import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class EmptyId extends Id{

	String emptyVal;
	
	EmptyId(String v)
	{
		emptyVal = v;
	}
	/* (non-Javadoc)
	 * @see Id#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
	}
	/* (non-Javadoc)
	 * @see Id#getId()
	 */
	@Override
	String getId() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see Id#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see Id#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
	}

}
