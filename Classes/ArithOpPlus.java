import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class ArithOpPlus extends ArithOp{

	String plus;
	
	ArithOpPlus(String p)
	{
		plus = p;
	}
	/* (non-Javadoc)
	 * @see ArithOp#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		
		Lexical_Analyzer.displayln(indent + indent.length() + " " + plus);
		
	}
	/* (non-Javadoc)
	 * @see ArithOp#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		return plus;
	}
	/* (non-Javadoc)
	 * @see ArithOp#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
		int displayVar = 0;
		
		if(numOfVariables == 2)
		{
			displayVar = 2;
		}
		else
			if(numOfVariables % 2 == 0 && numOfVariables > 2)
			{
				displayVar = 2;
				numOfVariables = numOfVariables - 2;
			}
			else
				if(numOfVariables % 2 != 0 && numOfVariables > 2)
				{
					displayVar = 2;
					numOfVariables = numOfVariables - 2;
				}
				
		if(numOfVariables == 1)
			displayVar = 3;
		
		if(emitVarCount >= 1)
		{
				
			displayln("	add " + displayVar);
			
			emitVarCount = emitVarCount - 1;
			
		}
	}

}
