import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class ExpId extends Id{

	String identifier;
	
	ExpId(String id)
	{
		identifier = id;
	}
	/* (non-Javadoc)
	 * @see Id#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		
		Lexical_Analyzer.displayln(indent + indent.length() + " " + identifier); 
		
	}
	/* (non-Javadoc)
	 * @see Id#getId()
	 */
	@Override
	String getId() {
		// TODO Auto-generated method stub
		return identifier;
	}
	/* (non-Javadoc)
	 * @see Id#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		// Get the return type of the variable or assignment statement
		String evaluation = parameterVar.get(identifier);
		
		if(evaluation == null)
		{
			evaluation = funType.get(identifier);
		}
		
		return evaluation;
	}
	/* (non-Javadoc)
	 * @see Id#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
		if(funType.get(identifier) == null)
		{
			displayln("	push  #" + variablePos.get(identifier));
			emitVarCount++;
			numOfVariables++;
		}else
		{			
			if(labelVal < listOfFunDefIndex || labelVal == listOfFunDefIndex)
				displayln("	call " + " " + startLabel.get(identifier) + ", " + funTypeVariables.get(identifier));
			else
			{				
				if(finalExpression == false)
					startLabel.put(identifier, labelVal - 2);
				
				displayln("	call " + " " + startLabel.get(identifier) + ", " + funTypeVariables.get(identifier));

			}
		
		}
		
	}

}
