import java.util.HashMap;


/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class CompleteFunDefList extends FunDefList{

	FunDef fundef;
	
	CompleteFunDefList(FunDef fd)
	{
		fundef = fd;
	}
	/* (non-Javadoc)
	 * @see FunDefList#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		String indent1 = indent + " ";
		Lexical_Analyzer.displayln(indent + indent.length() + " <fun def>");
		fundef.printParseTree(indent1);
	}

	/* (non-Javadoc)
	 * @see FunDefList#buildTypeTable()
	 */
	@Override
	void buildTypeTable() {
		// TODO Auto-generated method stub
		
		fundef.buildTypeTable();
		
	}
	/* (non-Javadoc)
	 * @see FunDefList#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
		String result = fundef.TypeEval(funType, parameterVar, variablePos);
		
		return result;
	}
	/* (non-Javadoc)
	 * @see FunDefList#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
	
		fundef.emitInstructions();
		
	}

}
