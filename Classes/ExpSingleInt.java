import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class ExpSingleInt extends Exp{

	Int intVal;
	
	ExpSingleInt(Int i)
	{
		intVal = i;
	}
	/* (non-Javadoc)
	 * @see Int#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		
		Lexical_Analyzer.displayln(indent + indent.length() + " <exp> ");
		intVal.printParseTree(indent+" ");
		
	}
	/* (non-Javadoc)
	 * @see Exp#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		return intVal.TypeEval(funType, parameterVar, variablePos);
	}
	/* (non-Javadoc)
	 * @see ExpList#getString()
	 */
	@Override
	String getString() {
		// TODO Auto-generated method stub
		return intVal.toString();
	}
	/* (non-Javadoc)
	 * @see Exp#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
		intVal.emitInstructions();
		
	}
	
}
