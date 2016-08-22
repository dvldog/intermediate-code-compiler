import java.util.HashMap;


/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class CompleteFunDef extends FunDef{

	Header head;
	Exp exp;
	
	CompleteFunDef(Header h,Exp ex)
	{
		head = h;
		exp = ex;
	}
	
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		
		head.printParseTree(indent);
		exp.printParseTree(indent);
	}
	

	/* (non-Javadoc)
	 * @see FunDefList#buildTypeTable()
	 */
	@Override
	void buildTypeTable() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see FunDef#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		String result = exp.TypeEval(funType, parameterVar, variablePos);
		String error = "Type Error: incompatible return type and body expression type in function: " + listOfFunDef[listOfFunDefIndex_2].getName();		
		
		if(result.equals(funType.get(listOfFunDef[listOfFunDefIndex_2].getName())))
		{
			listOfFunDefIndex_2++;
			return result;		
		}
		else
			if(currentOperator.equals("*") && funType.get(listOfFunDef[listOfFunDefIndex_2].getName()).equals("float"))
			{
				listOfFunDefIndex_2++;
				return "float";
			}
		else
		{
			result = error;
			errors[errorsIndex++] = result;
			listOfFunDefIndex_2++;
			
			return result;
		}
		
	}

	/* (non-Javadoc)
	 * @see FunDef#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
	
		
		displayln(labelVal++ + ":");
		
		previousLabelVal++;
		
		exp.emitInstructions();
		
		displayln("	return");
		numOfVariables = 0;
		
	}
}
