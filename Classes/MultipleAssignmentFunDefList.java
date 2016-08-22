import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public class MultipleAssignmentFunDefList extends FunDefList{

	FunDef fundef;
	FunDefList fundeflist;
	
	MultipleAssignmentFunDefList(FunDefList fdl, FunDef fd)
	{
		fundeflist = fdl;
		fundef = fd;
	}
	/* (non-Javadoc)
	 * @see FunDefList#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		String indent1 = indent + " ";
		Lexical_Analyzer.displayln(indent + indent.length() + " <fundef>");
		fundef.printParseTree(indent1);
		fundeflist.printParseTree(indent);
	}

	/* (non-Javadoc)
	 * @see FunDefList#buildTypeTable()
	 */
	@Override
	void buildTypeTable() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see FunDefList#TypeEval(java.util.HashMap, java.util.HashMap, java.util.HashMap)
	 */
	@Override
	String TypeEval(HashMap<String, String> funType,
			HashMap<String, String> parameterVar,
			HashMap<String, Integer> variablePos) {
		// TODO Auto-generated method stub
		
			fundef.TypeEval(funType, parameterVar, variablePos);
			String result2 = fundeflist.TypeEval(funType, parameterVar, variablePos);
		
		return result2;
	}
	/* (non-Javadoc)
	 * @see FunDefList#emitInstructions()
	 */
	@Override
	void emitInstructions() {
		// TODO Auto-generated method stub
		
		fundef.emitInstructions();
		fundeflist.emitInstructions();
		
		
	}

}
