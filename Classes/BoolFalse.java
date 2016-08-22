import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class BoolFalse extends BoolLiteral{

	String boolF;
	
	BoolFalse(String bf)
	{
		boolF = bf;
	}
	/* (non-Javadoc)
	 * @see BoolLiteral#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		
		Lexical_Analyzer.displayln(indent + indent.length() + " " + boolF);
	}
	/* (non-Javadoc)
	 * @see BoolLiteral#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		return "boolean";
	}
	/* (non-Javadoc)
	 * @see BoolLiteral#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
		displayln("	push " + boolF);
		
	}

}
