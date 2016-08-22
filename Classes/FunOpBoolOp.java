import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class FunOpBoolOp extends FunOp{

	BoolOp boolop;
	
	FunOpBoolOp(BoolOp bo)
	{
		boolop = bo;
	}
	/* (non-Javadoc)
	 * @see FunOp#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		
		boolop.printParseTree(indent);
		
	}
	/* (non-Javadoc)
	 * @see FunOp#getString()
	 */
	@Override
	String getString() {
		// TODO Auto-generated method stub
		return boolop.getString();
	}
	/* (non-Javadoc)
	 * @see FunOp#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		return boolop.TypeEval(funType, parameterVar, variablePos);
	}
	/* (non-Javadoc)
	 * @see FunOp#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
		boolop.emitInstructions();
	}

}
