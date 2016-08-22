import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class CompOpLe extends CompOp{

	String lessE;
	
	CompOpLe(String le)
	{
		lessE = le;
	}
	/* (non-Javadoc)
	 * @see CompOp#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		String newIndent = indent.substring(0, indent.length()-1);
		Lexical_Analyzer.display(newIndent + newIndent.length() + " " + lessE+ "\n");

	}
	/* (non-Javadoc)
	 * @see CompOp#getComp()
	 */
	@Override
	String getComp() {
		// TODO Auto-generated method stub
		return lessE;
	}
	/* (non-Javadoc)
	 * @see CompOp#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		return lessE;
	}
	/* (non-Javadoc)
	 * @see CompOp#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
		displayln("	le");

		
	}

}
