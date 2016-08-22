import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class ExpSingleId extends Exp{

	Id id;
	
	ExpSingleId(Id i)
	{
		id = i;
	}
	/* (non-Javadoc)
	 * @see Exp#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		
		Lexical_Analyzer.displayln(indent + indent.length() + " <exp>");
		id.printParseTree(indent+" ");
		
	}
	/* (non-Javadoc)
	 * @see Exp#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		return id.TypeEval(funType, parameterVar, variablePos);
	}
	/* (non-Javadoc)
	 * @see ExpList#getString()
	 */
	@Override
	String getString() {
		// TODO Auto-generated method stub
		return id.getId();
	}
	/* (non-Javadoc)
	 * @see Exp#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
		id.emitInstructions();
	}

}
