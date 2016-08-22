import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
abstract class FloatClass extends Parser{
	
	abstract void printParseTree(String indent);
	abstract String TypeEval(HashMap<String,String> funType,HashMap<String,String> parameterVar,HashMap<String,Integer> variablePos );
	abstract String getFloat();
	abstract void emitInstructions();
}
