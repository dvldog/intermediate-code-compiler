import java.util.HashMap;


/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public abstract class FunDefList extends Parser{
	
	abstract void printParseTree(String indent);
	abstract String TypeEval(HashMap<String,String> funType,HashMap<String,String> parameterVar,HashMap<String,Integer> variablePos );
	abstract void buildTypeTable();
	abstract void emitInstructions();
}
