import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class CompleteFunDefListAndExp extends FunDefListAndExp{

	FunDefList fundeflist;
	Exp exp;
	
	CompleteFunDefListAndExp(FunDefList fdl, Exp e)
	{
		fundeflist = fdl;
		exp = e;
	}
	/* (non-Javadoc)
	 * @see FunDefListAndExp#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		return fundeflist.TypeEval(funType, parameterVar, variablePos);
	}

	/* (non-Javadoc)
	 * @see FunDefListAndExp#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
		displayln("	goto 1");
		fundeflist.emitInstructions();
		displayln(1 + ":");
		finalExpression = true;
		exp.emitInstructions();
		
	}

	/* (non-Javadoc)
	 * @see FunDefListAndExp#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		
		fundeflist.printParseTree(indent);

	}

}
