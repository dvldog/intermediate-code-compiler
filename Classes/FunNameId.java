import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class FunNameId extends Id{

	String identifier;
	
	FunNameId(String i)
	{
		identifier = i;
	}
	/* (non-Javadoc)
	 * @see Id#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		
		Lexical_Analyzer.display(identifier);
		
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
		
		return parameterVar.get(identifier);
	}
	/* (non-Javadoc)
	 * @see Id#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
		System.out.println("		push	#" + variablePos.get(identifier));
		
	}

}
