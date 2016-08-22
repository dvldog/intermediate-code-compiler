import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class ExpFloat extends FloatClass{

	float floatVal;
	
	ExpFloat(float f)
	{
		floatVal = f;
	}
	/* (non-Javadoc)
	 * @see FloatClass#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		
		Lexical_Analyzer.displayln(indent + indent.length() + " " +floatVal);
		
	}
	/* (non-Javadoc)
	 * @see FloatClass#getFloat()
	 */
	@Override
	String getFloat() {
		// TODO Auto-generated method stub
		
		String newFloat = floatVal + "";
		return newFloat;
	}
	/* (non-Javadoc)
	 * @see FloatClass#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		return "float";
	}
	/* (non-Javadoc)
	 * @see FloatClass#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
					
			displayln("	push  " + floatVal);
			numOfVariables++;
	}
	

}
