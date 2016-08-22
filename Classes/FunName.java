import java.util.HashMap;


/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
abstract class FunName {
	
	abstract void printParseTree(String indent);
	abstract String TypeEval(HashMap<String,String> funType,HashMap<String,String> parameterVar,HashMap<String,Integer> variablePos );
	abstract String getId();
	abstract void emitInstructions();
}
