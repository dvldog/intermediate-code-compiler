import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class CompleteFunExp extends FunExp{

	FunOp funop;
	ExpList explist;
	
	CompleteFunExp(FunOp fo,ExpList el)
	{
		funop = fo;
		explist = el;
	}
	/* (non-Javadoc)
	 * @see FunExp#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		if(funop != null)
		funop.printParseTree(indent);
		explist.printParseTree(indent);
		
	}
	/* (non-Javadoc)
	 * @see Exp#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		String tempTypeEval = explist.TypeEval(funType, parameterVar, variablePos);
		String tempFunop = funop.TypeEval(funType, parameterVar, variablePos);	
		currentOperator = tempFunop;// Keep track of the current operator for TypeEval evaluation
		String returnVal = tempTypeEval;
	
		if(!tempTypeEval.equals("Type Error: incompatible types found in expression"))
		{
			if(tempFunop != null)
			{	
						
				if(tempFunop.equals("<")||tempFunop.equals(">")||tempFunop.equals("=")||tempFunop.equals("<=")||tempFunop.equals(">="))
				{
					
					returnVal = "boolean";
					
				}else
					if(tempFunop.equals("or")||tempFunop.equals("and") || tempFunop.equals("not"))
					{
						
						returnVal = "boolean";					
					}
					else
						returnVal = tempTypeEval;
				
			}
				
		}
		else
		{
			returnVal = "Type Error: some arguments of " + tempFunop + " operator have incompatible types";
			errors[errorsIndex++] = returnVal;
		}
				
		return returnVal;
	}
	/* (non-Javadoc)
	 * @see FunExp#getString()
	 */
	@Override
	String getString() {
		// TODO Auto-generated method stub
		
		return funop.getString();
	}
	/* (non-Javadoc)
	 * @see Exp#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
		explist.emitInstructions();
		funop.emitInstructions();
		
	}

}
