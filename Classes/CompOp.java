import java.util.HashMap;

/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
public abstract class CompOp extends Parser{
	
	abstract void printParseTree(String indent);
	abstract String TypeEval(HashMap<String,String> funType,HashMap<String,String> parameterVar,HashMap<String,Integer> variablePos );
	abstract String getComp();
	abstract void emitInstructions();

}
