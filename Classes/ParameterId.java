import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class ParameterId extends Id{

	String identifier;
	
	ParameterId(String id)
	{
		identifier = id;
	}
	/* (non-Javadoc)
	 * @see Id#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		
	Lexical_Analyzer.display(" " +identifier+"\n");
		
	}
	/* (non-Javadoc)
	 * @see Id#getId()
	 */
	@Override
	String getId() {
		// TODO Auto-generated method stub
		return identifier;
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
		
		System.out.println("pushing var: " + identifier);
		System.out.println("		push	#" + variablePos.get(identifier));
		
	}
}
