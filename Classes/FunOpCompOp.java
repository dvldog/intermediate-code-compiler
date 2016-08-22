import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class FunOpCompOp extends FunOp{

	CompOp compop;
	
	FunOpCompOp(CompOp cop)
	{
		compop = cop;
	}
	/* (non-Javadoc)
	 * @see FunOp#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		compop.printParseTree(indent + " ");
	}
	/* (non-Javadoc)
	 * @see FunOp#getString()
	 */
	@Override
	String getString() {
		// TODO Auto-generated method stub
		
		return compop.getComp();
	}
	/* (non-Javadoc)
	 * @see FunOp#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		return compop.TypeEval(funType, parameterVar, variablePos);
	}
	/* (non-Javadoc)
	 * @see FunOp#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
		compop.emitInstructions();
		
	}

}
