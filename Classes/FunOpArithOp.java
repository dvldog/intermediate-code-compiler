import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class FunOpArithOp extends FunOp{

	ArithOp arithop;
	
	FunOpArithOp(ArithOp a)
	{
		arithop = a;
	}
	/* (non-Javadoc)
	 * @see Exp#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub

		arithop.printParseTree(indent);
		
	}
	/* (non-Javadoc)
	 * @see FunOp#getString()
	 */
	@Override
	String getString() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see FunOp#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		return arithop.TypeEval(funType, parameterVar, variablePos);
	}
	/* (non-Javadoc)
	 * @see FunOp#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
		arithop.emitInstructions();
		
	}

}
