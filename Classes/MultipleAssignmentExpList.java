import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class MultipleAssignmentExpList extends ExpList{

	ExpList explist;
	Exp exp;
	
	MultipleAssignmentExpList(ExpList el,Exp e)
	{
		explist = el;
		exp = e;
	}
	/* (non-Javadoc)
	 * @see ExpList#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		
		if(exp != null)
		exp.printParseTree(indent);
		explist.printParseTree(indent);
	}
	/* (non-Javadoc)
	 * @see ExpList#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
			
		
		// Evaluate both types of the variables. If they don't match, return an error value
		String tempTypeExplist = explist.TypeEval(funType, parameterVar, variablePos);
		String tempTypeExp = exp.TypeEval(funType, parameterVar, variablePos);
		String error = "Type Error: incompatible types found in expression";
				
		// Compare types and evaluate if they match or if the expression is a boolean comparison with different types
		if(tempTypeExp.equals(tempTypeExplist))
		{
			return tempTypeExp;
		}else
			if((tempTypeExplist.equals("float") && tempTypeExp.equals("int") || tempTypeExplist.equals("int") && tempTypeExp.equals("float")) && 
					(currentOperator.equals("=") || currentOperator.equals("<") || currentOperator.equals("<=") ||
							currentOperator.equals(">") || currentOperator.equals(">=")))
			{
				return "boolean";
											
			}
		
			else 
				if(tempTypeExplist.equals("float") && tempTypeExp.equals("int") && currentOperator.equals("*") && funType.get(listOfFunDef[listOfFunDefIndex_2].getName()).equals("float")
						|| tempTypeExplist.equals("int") && tempTypeExp.equals("float") && currentOperator.equals("*") && funType.get(listOfFunDef[listOfFunDefIndex_2].getName()).equals("float"))
					{
					
						return "float";
	
					}
				else
					{
						// Record the error if they exist
						errors[errorsIndex++] = error;
						return error;
					}
							
	}
	/* (non-Javadoc)
	 * @see ExpList#getString()
	 */
	@Override
	String getString() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see ExpList#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
		exp.emitInstructions();
		explist.emitInstructions();
		
	}
	
}
