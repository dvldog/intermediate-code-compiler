import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class ParenthesizedFunExp extends Exp{

	FunExp funexp;
	
	ParenthesizedFunExp(FunExp fe)
	{
		funexp = fe;
		
	}
	/* (non-Javadoc)
	 * @see Exp#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		String indent1 = indent + " ";
		
		
		Lexical_Analyzer.displayln(indent + indent.length() + " <exp> ");
		Lexical_Analyzer.displayln(indent1 + indent1.length() + " <fun exp> ");
		funexp.printParseTree(indent1+" ");
		
		
	}
	/* (non-Javadoc)
	 * @see Exp#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		return funexp.TypeEval(funType, parameterVar, variablePos);
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
	 * @see Exp#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
		funexp.emitInstructions();
		
	}

}
