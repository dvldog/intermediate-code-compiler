import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class CompOpGt extends CompOp{

	String greaterT;
	
	CompOpGt(String gt)
	{
		greaterT = gt;
	}
	/* (non-Javadoc)
	 * @see CompOp#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		String newIndent = indent.substring(0, indent.length()-1);
		Lexical_Analyzer.display(newIndent + newIndent.length() + " " + greaterT + "\n");

	}
	/* (non-Javadoc)
	 * @see CompOp#getComp()
	 */
	@Override
	String getComp() {
		// TODO Auto-generated method stub
		return greaterT;
	}
	/* (non-Javadoc)
	 * @see CompOp#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		return greaterT;
	}
	/* (non-Javadoc)
	 * @see CompOp#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
		displayln("	gt");

		
	}

}
