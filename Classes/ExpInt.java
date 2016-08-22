import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class ExpInt extends Int{

	 long intVal;
	
	ExpInt(long i)
	{
		intVal = i;
	}
	/* (non-Javadoc)
	 * @see Int#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		
		Lexical_Analyzer.displayln(indent + indent.length() + " " + intVal); 
		
	}
	/* (non-Javadoc)
	 * @see Int#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		return "int";
	}
	/* (non-Javadoc)
	 * @see Int#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
			
		displayln("	push " + intVal);
		numOfVariables++;
	}
	

}
