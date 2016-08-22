import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class ArithOpMinus extends ArithOp{

	String minus;
	
	ArithOpMinus(String m)
	{
		minus = m;
	}
	/* (non-Javadoc)
	 * @see ArithOp#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		Lexical_Analyzer.displayln(indent + indent.length() + " " + minus);
	}
	/* (non-Javadoc)
	 * @see ArithOp#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		return minus;
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
				else
					displayVar = numOfVariables;
		
		if(numOfVariables == 1)
			displayVar = 3;
		
		if(emitVarCount >= 2)
		{
				
			displayln("	sub " + displayVar);
			
			emitVarCount = emitVarCount - 1;
			
		}
		
	}

}
