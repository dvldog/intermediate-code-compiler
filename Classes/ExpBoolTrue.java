import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class ExpBoolTrue extends Exp{

	BoolLiteral booltrue;
	
	ExpBoolTrue(BoolLiteral bf)
	{
		booltrue = bf;
	}
	
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		
		booltrue.printParseTree(indent + " ");

	}

	/* (non-Javadoc)
	 * @see Exp#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		return booltrue.TypeEval(funType, parameterVar, variablePos);
	}

	/* (non-Javadoc)
	 * @see ExpList#getString()
	 */
	@Override
	String getString() {
		// TODO Auto-generated method stub
		return booltrue.toString();
	}

	/* (non-Javadoc)
	 * @see Exp#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
		booltrue.emitInstructions();
		
	}

	
}
