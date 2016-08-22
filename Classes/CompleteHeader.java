/**
 * 
 */

/**
 * @author dilanderoger
 *
 */
class CompleteHeader extends Header{

	Type type;
	FunName funname;
	ParameterList parameterlist;
	boolean print = false;
	
	CompleteHeader(Type t, FunName fn, ParameterList pl, boolean pe)
	{
		type = t;
		funname = fn;
		parameterlist = pl;
		print = pe;
	}
	/* (non-Javadoc)
	 * @see Header#printParseTree(java.lang.String)
	 */
	@Override
	void printParseTree(String indent) {
		// TODO Auto-generated method stub
		String indent1 = indent + " ";
		Lexical_Analyzer.displayln(indent + indent.length() + " <header> ");	
		Lexical_Analyzer.display(indent1 + indent1.length() + " <type> ");	
		type.printParseTree(indent1);
		
		Lexical_Analyzer.display("\n");
		Lexical_Analyzer.display(indent1 + indent1.length() + " <fun name> ");
		funname.printParseTree(indent1);
		
		Lexical_Analyzer.displayln("");
		if(print == true)
		{
		Lexical_Analyzer.display(indent1 + indent1.length() + " <parameter list>");
		Lexical_Analyzer.displayln("");
		}
		parameterlist.printParseTree(indent1);		
		
	}

}
