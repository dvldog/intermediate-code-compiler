import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class BoolOpAnd extends BoolOp{

	String bool;
	
	BoolOpAnd(String and)
	{
		bool = and;
	}
	/* (non-Javadoc)
	 * @see BoolOp#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		
		Lexical_Analyzer.displayln(indent + indent.length() + " " + bool);
	}
	/* (non-Javadoc)
	 * @see BoolOp#getString()
	 */
	@Override
	String getString() {
		// TODO Auto-generated method stub
		
		return bool;
	}
	/* (non-Javadoc)
	 * @see BoolOp#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		return bool;
	}
	/* (non-Javadoc)
	 * @see BoolOp#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
		displayln("	and 2");
		
	}

}
