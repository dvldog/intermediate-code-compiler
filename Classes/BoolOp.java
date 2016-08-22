import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public abstract class BoolOp extends Parser{

	abstract void printParseTree(String indent);
	abstract String getString();
	abstract String TypeEval(HashMap<String,String> funType,HashMap<String,String> parameterVar,HashMap<String,Integer> variablePos );
	abstract void emitInstructions();
}
