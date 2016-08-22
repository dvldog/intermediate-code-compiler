import java.util.HashMap;


/**
 * 
 */

/**
 * The Parser class is a top-down parser for the BNF language specified for this project.  The Lexical_Analyzer class 
 * reads the information from the file, stores the relevant information in the Tokens class, and the Parser constructs
 * an explicit parse tree in linearly indented form.  Class objects are used to construct the parse tree.  An appropriate 
 * error message is displayed and the program terminates when the first syntax error is found.
 * 
 * @author dilanderoger
 *
 */
abstract class Parser extends Lexical_Analyzer{

	public static HashMap<String,String> funType = new HashMap<String,String>();// Hash Map of the function name mapped to its type
	public static HashMap<String,String> parameterVarType = new HashMap<String,String>();// Hash Map of the parameter name mapped to its type
	public static int hashVarPos = 1;// Variable that represents the variable position
	public static boolean funNameSet = false; // Condition to test if the <fun name> id has been set already
	public static boolean parameterExists = false; // Condition to test if variables exist for the <parameter list> class
	public static boolean expression = false; // Condition to test if the parser is on the right hand side of the equation; <header> = <exp>
	public static int lParenCount = 0;
	public static int rParenCount = 0;
	public static int variableCount = 0; // variables contained inside a parenthesis
	public static String[] errors = new String[50];// List of errors produced while "TypeEval" is invoked
	public static int errorsIndex = 0;// Variable used for the "errors" array
	public static String currentOperator = "";// Current  operation being performed  
	public static FunctionDefinition[] listOfFunDef = new FunctionDefinition[30];// Array of FunctionDefinitions
	public static int listOfFunDefIndex = 0;// Variable used to traverse through the array FunctionDefinitios
	public static int listOfFunDefIndex_2 = 0;// Variable used to traverse through the array FunctionDefinitios in the CompleteFunDef class when comparing return types of the function and the TypeEval of the variables
	
	public static int labelVal = 2;// Label number
	public static int labelVal_2 = 3;// "if_f goto" and "goto" label number
	public static int tempLabelVal = 0;// Variable used to determine if a function contains an "if" statement.  Used to determine the "goto" label number
	public static int emitVarCount = 0;// Variable count for a current function
	public static boolean assignment = false;
	public static boolean finalExpression = false;// Boolean flag to determine if the <fun def list and exp> category is complete
	public static boolean sameFunction = false;// Boolean to test if tempLabelVal should add 1 to itself
	public static int previousLabelVal = 2;// Variable used to determine the remaining "goto" out label numbers
	public static int [] finalOutIntegers = new int[30];// Array that holds the "goto" values that are not visited in sequential order
	public static int finalOutIndex = 0;// Integer used to traverse through the "finalOutIntegers" array
	public static int numOfVariables = 0;// Number of variables in the expression for any current function
	public static int operatorCount = 0;// Number of arithmetic operators
	public static HashMap<String,Integer> startLabel = new HashMap<String,Integer>();// Hash Map of the function name mapped to its start label
	public static HashMap<String,Integer> funTypeVariables = new HashMap<String,Integer>();// Hash Map of the function name mapped to the total number of variables it contains
	public static HashMap<String,Integer> variablePos = new HashMap<String,Integer>();// Hash Map of the name mapped to its variable position
	
	public static FunDefListAndExp fundeflistandexp()
	{
		// <fun def list and exp> --> <fun def list> "/" <exp>
		
		FunDefList fundeflist = fundeflist();
		
		// Advance the integer to the next index that contains a left parenthesis
		iterator_2++;
		funNameSet = true;
		expression = true;
		
		labelVal_2 = labelVal - tempLabelVal;
				
		labelVal = 2;
		
		Exp exp = exp();
		
		return new CompleteFunDefListAndExp(fundeflist,exp);
	}
	public static FunDefList fundeflist()
	{
		// <fun def list> --> <fun def> | <fun def><fun def list>
		
		FunDef fundef = fundef();
		
		if((iterator_2 + 1) < getSizeOfTokensArray())
		{
			getNextToken();
					
		if(tokens[iterator_2].getTokenState() == State.Keyword_int || tokens[iterator_2].getTokenState() == State.Keyword_float
				|| tokens[iterator_2].getTokenState() == State.Keyword_boolean)
		{
			
			// Reset the variable position to 1 for the new function definition
			hashVarPos = 1;
			assignment = false;
			sameFunction = false;
			
			FunDefList fundeflist = fundeflist();	
			
			return new MultipleAssignmentFunDefList(fundeflist,fundef);
			
		}	
		else if(tokens[iterator_2].getTokenState() == State.Div)
		{		
			return new CompleteFunDefList(fundef);
		}
		else
	
			errorMsg(2);
			System.exit(1);
			
			return null;
		
		}else
		{	
			return new CompleteFunDefList(fundef);
		}
		
	}// end fundeflist
	
	public static FunDef fundef()
	{
		// <fun def> --> <header> = <exp>
	
		Header header = header();
		expression = true;
		
		// Change to the variable count for the current function
		hashVarPos = hashVarPos - 1;
		
		// Create a hash map that contains the variable count of the function
		funTypeVariables.put(listOfFunDef[listOfFunDefIndex].getName(), hashVarPos);
		listOfFunDefIndex++;
		
		
		Exp exp = exp();
		
		expression = false;
		parameterExists = false;
		funNameSet = false;
		variableCount = 0;
		rParenCount = 0;
		
		return new CompleteFunDef(header,exp);
		
	}// end fundef
	
	public static Header header()
	{
		// <header> --> <type> <fun name> <parameter list>
		
		Type type = type();	
		FunName  funname = funname();
		
		// Set the function name
		listOfFunDef[listOfFunDefIndex] = new FunctionDefinition(funname.getId(),type.getType());
		
		/////////////////////////************************
		//System.out.println("Funname: " + funname.getId() + " " + "labelVal: " + labelVal);
		startLabel.put(funname.getId(), labelVal++);
		/////////////////////////*************************
		
		ParameterList parameterlist = parameterlist();
		
		// Set the function type
		funType.put(funname.getId(),type.getType() );
		
		return new CompleteHeader(type,funname,parameterlist,parameterExists);
		
	}// end header
	
	public static ParameterList parameterlist() {
		
		// <parameter list> --> null string | <parameter> <parameter list>
		
		Parameter parameter = parameter();
		
		getNextToken();
		if(tokens[iterator_2].getTokenState() == State.RParen)
			getNextToken();
		
		if(tokens[iterator_2].getTokenState() == State.LParen)
		{
			ParameterList parameterlist = parameterlist();
			return new MultipleAssignmentParameter(parameterlist,parameter);
		}
		else
			return parameter;
	}// end parameterlist
	
	public static Parameter parameter()
	{
		// <parameter> --> "(" <type> <id> ")"
		
		getNextToken();	
		Type type = type();
		Id id = id();

		if(tokens[iterator_2].getTokenState()  == State.Equal)
		{
			assignment = true;
			
			return new EmptyParameter(type,id);
		}
		else
		{
			parameterExists = true;
			
			if(parameterVarType.containsKey(id.getId()) == true)
			{
				
				// Put the variable and position in the hashmap
				variablePos.put(id.getId(),hashVarPos++);
				
				// Set the variable and type of the function definition
				listOfFunDef[listOfFunDefIndex].setVariables(id.getId(),type.getType());
			
				return new Parameter(type,id);
			
			}
		
			// Put the variable and type into the hashmap
			parameterVarType.put(id.getId(),type.getType());
						
			// Put the variable and position in the hashmap
			variablePos.put(id.getId(),hashVarPos++);
			
			// Set the variable and type of the function definition
			listOfFunDef[listOfFunDefIndex].setVariables(id.getId(),type.getType());
		
			return new Parameter(type,id);
		}
	}// end parameter
	
	/**
	 * @return
	 */
	public static FunName funname() {
	
		// <fun name> --> <id>
		
		Id id = id();
		
		return new HeaderFunName(id);
	}// end funname
	
	/**
	 * @return
	 */
	public static Type type() {
		
		// <type> --> "int" | "float" | "boolean" 
		
		if(tokens[iterator_2].getTokenState() == State.LParen)
		{
			getNextToken();

			return new ParameterType(tokens[iterator_2].getToken());
			
		}else
			if(tokens[iterator_2].getTokenState() == State.Equal)
			{
				return new EmptyType("");
				
			}
			else
			{
				return new HeaderType(tokens[iterator_2].getToken());			
			}
				
	}// end type
	
	public static Id id()
	{
		if(funNameSet == false)
		{
			getNextToken();
			funNameSet = true;
			variableCount++;
			
				return new FunNameId(tokens[iterator_2].getToken());
			
		}else
			if(tokens[iterator_2].getTokenState() == State.LParen)
			{
				getNextToken();
				return new ParameterId(tokens[iterator_2].getToken());
			}
			else
				if(tokens[iterator_2].getTokenState() == State.Equal)
				{
					return new EmptyId("");
				}
				else 
					if(expression == true)
					{
						variableCount++;
						String id = tokens[iterator_2].getToken();
																
						return new ExpId(id);
					}
					else{
						
						variableCount++;
						getNextToken();
												
						return new ParameterId(tokens[iterator_2].getToken());
						
						}
	}// end id
	
	public static Exp exp()
	{
		// <exp> --> <id> | <int> | <float> | <floatE> | <boolLiteral> | "(" <fun exp> ")" | "if" <exp> "then" <exp> "else" <exp>
				
		if(tokens[iterator_2].getTokenState() == State.Equal)
			getNextToken();
		 
		
		if(tokens[iterator_2].getTokenState() == State.Id)
		{
			Id id = id();
		
			return new ExpSingleId(id);
		
		}
		if(tokens[iterator_2].getTokenState() == State.Int)
		{
			Int newInt = newInt();
			
			return new ExpSingleInt(newInt);
		}
		if(tokens[iterator_2].getTokenState() == State.Float || tokens[iterator_2].getTokenState() == State.FloatE)
		{
			FloatClass floatclass = floatclass();
			
			return new ExpSingleFloat(floatclass);
		}
		if(tokens[iterator_2].getTokenState() == State.Keyword_false || tokens[iterator_2].getTokenState() == State.Keyword_true)
		{
			
			BoolLiteral boolliteral = boolliteral();
						
			if(tokens[iterator_2].getToken().equals("true"))
			{
				return new ExpBoolFalse(boolliteral);
				
			}else
				return new ExpBoolTrue(boolliteral);
		}
		if(tokens[iterator_2].getTokenState() == State.LParen)
		{
			variableCount = 0;
			lParenCount++;
			
			FunExp funexp = funexp();
			
			return new ParenthesizedFunExp(funexp);
		}
		if(tokens[iterator_2].getToken().equals("if"))
		{
			getNextToken();
			ConditionalExp conditionalexp = conditionalexp();
			
			return conditionalexp;
		}
		else 
		{
			errorMsg(3);
			System.exit(1);
			return null;
		}
	}// end exp
	
	public static ConditionalExp conditionalexp()
	{
		// Test to determine if this function contains multiple if statements
		if(sameFunction == false)
		{
			tempLabelVal++;
			sameFunction = true;
		}
		
		Exp exp = exp();		
		Exp exp2 = null;
		Exp exp3 = null;
		
		getNextToken();
		
		if(tokens[iterator_2].getTokenState() == State.Keyword_then)
		{
			 getNextToken();
			 exp2 = exp();
			 
			 // Prevent null exception. Check for anymore tokens
			 if((iterator_2 +1) < getSizeOfTokensArray())
			 getNextToken();
							 			 
		}else
		{
			errorMsg(1);
			System.exit(1);
			return null;
		}
		
		if(tokens[iterator_2].getTokenState() == State.Keyword_else)
		{
			 getNextToken();
			 exp3 = exp();
			 			 
			 return new ConditionalExp(exp,exp2,exp3);
		}else
		{
			errorMsg(1);
			System.exit(1);
			return null;
		}		
		
	}// end conditionalexp
	
	public static Int newInt()
	{
		if(tokens[iterator_2].getTokenState() == State.Int)
		{
			String newLong = tokens[iterator_2].getToken();
			char pore = newLong.charAt(0);
			
			if(pore == '+' )
			{
				newLong = newLong.substring(1,newLong.length());
			}
			
			long temp = Long.parseLong(newLong);
			return new ExpInt(temp);
		}
		
		return null;
	}// end newInt
	
	public static FloatClass floatclass()
	{
		if(tokens[iterator_2].getTokenState() == State.Float || tokens[iterator_2].getTokenState() == State.FloatE)
		{
			float temp = Float.parseFloat(tokens[iterator_2].getToken());
			return new ExpFloat(temp);
		}
		
		return null;
	}// end floatclass
	
	public static BoolLiteral boolliteral()
	{
		// <boolLiteral> --> "false" | "true" 
		
		if(tokens[iterator_2].getToken().equals("false"))
		{
			return new BoolFalse("false");
			
		}else
		{
			return new BoolTrue("true");
		}
		
	}// end boolliteral
	
	public static FunExp funexp()
	{
		//<fun exp> --> <fun op> <exp list> 
		
		FunOp funop = funop();	
		
		ExpList explist = explist();		
		
		return new CompleteFunExp(funop,explist);
		
	}// end funexp
	
	public static ExpList explist()
	{
		//<exp list> --> null string | <exp> <exp list> 
		
		if(tokens[iterator_2].getTokenState() == State.RParen && variableCount == 1)
		{
			// Extract the token before the "RParen"
			int index = iterator_2-1;
			
			return new EmptyExpList(tokens[index].getToken());
		}
						
		Exp exp = exp();
		
		// Prevent null exception from tokens array
		if((iterator_2 + 1)< getSizeOfTokensArray())
		getNextToken();
		
		if(tokens[iterator_2].getTokenState() != State.RParen && (iterator_2 + 1) != getSizeOfTokensArray())
		{
			
			ExpList explist = explist();
	
			return new MultipleAssignmentExpList(explist,exp);
			
		}else
			{
				return exp;
			}
		
	}// end explist
	
	public static FunOp funop()
	{
		// <fun op> --> <fun name> | <arith op> | <bool op> | <comp op> 
		
		getNextToken();
		
		if(tokens[iterator_2].getTokenState() == State.Id)
		{
		
			FunName funname = funname();
			
			getNextToken();	
					
				return new FunOpFunName(funname);
			
		}
		if(tokens[iterator_2].getTokenState() == State.Plus || tokens[iterator_2].getTokenState() == State.Minus || 
				tokens[iterator_2].getTokenState() == State.Times || tokens[iterator_2].getTokenState() == State.Div)
		{			
			ArithOp arithop = arithop();
			getNextToken();
			
			return new FunOpArithOp(arithop);
		}
		if(tokens[iterator_2].getTokenState() == State.Keyword_and || tokens[iterator_2].getTokenState() == State.Keyword_or || 
				tokens[iterator_2].getTokenState() == State.Keyword_not)
		{
			BoolOp boolop = boolop();
			getNextToken();
			
			return new FunOpBoolOp(boolop);
		}
		if(tokens[iterator_2].getTokenState() == State.Equal || tokens[iterator_2].getTokenState() == State.Lt || 
				tokens[iterator_2].getTokenState() == State.Le || tokens[iterator_2].getTokenState() == State.Ge 
				|| tokens[iterator_2].getTokenState() == State.Gt)
		{			
			CompOp compop = compop();		
			getNextToken();
			
			return new FunOpCompOp(compop);
			
		}
		return null;
	}// end funop
	
	public static BoolOp boolop()
	{
		// <bool op> --> "and" | "or" | "not" 
		
		switch(tokens[iterator_2].getTokenState())
		{
			case Keyword_and:
				
				return new BoolOpAnd("and");
				
			case Keyword_or:
				
				return new BoolOpOr("or");
				
			case Keyword_not:
				
				return new BoolOpNot("not");
		
		}
		return null;
	}// end boolop
	
	public static ArithOp arithop()
	{
		// <arith op> --> "+" |"-" | "/" | "*"
		
		switch(tokens[iterator_2].getTokenState())
		{
			case Plus:
				
				return new ArithOpPlus("+");
				
			case Minus:
				
				return new ArithOpMinus("-");
				
			case Div:
				
				return new ArithOpDiv("/");
				
			case Times:	
				operatorCount++;
				return new ArithOpTimes("*");
		
		}
		return null;
	}// end arithop
	
	public static CompOp compop()
	{
		// <comp op> --> "<" | "<=" | ">" | ">=" | "=" 
		
		switch(tokens[iterator_2].getTokenState())
		{
			case Equal:
				variableCount++;
				return new CompOpEqual("=");
				
			case Gt:
				variableCount++;
				return new CompOpGt(">");
				
			case Ge:
				variableCount++;		
				return new CompOpGe(">=");
				
			case Lt:	
				variableCount++;	
				return new CompOpLt("<");
				
			case Le:
				variableCount++;	
				return new CompOpLe("<=");
		}
		
		
		return null;
	}// end compop
	
	static boolean errorFound = true;
	
	public static void errorMsg(int i)
	{
		
		
		display(tokens[iterator_2].getToken() + ": unexpected symbol ");

		switch( i )
		{
		case 1:	displayln(" where \"then\" or \"else\" expected"); return;
		case 2: displayln(" where \"int\", \"float\", or \"boolean\" expected"); return;
		case 3:	displayln(" "); return;
		}
	}// end errorMsg
	
	public static void printTypeCheckResults()
	{
		displayln("\n");
		displayln("Display return types of functions:\n");
		display("{");
		
		for(int i = 0; i < listOfFunDefIndex; i++)
		{
			display(listOfFunDef[i].getName() + "=" + listOfFunDef[i].getType());
			
			if(listOfFunDef[i].getListSize() > 1 && (i+1) < listOfFunDef[i].getListSize())
				display(", ");	
		}
		display("}");
		display("\n");
		display("\n");
		displayln("Display parameter types of functions:\n");
		
		display("{");
		for(int i = 0; i < listOfFunDefIndex; i++)
		{
			if(listOfFunDef[i].getSize() > 0)
			{
					
				display(listOfFunDef[i].getName() + "=" + listOfFunDef[i].getVariables());
			
				// print a comma when appropriate
				if(listOfFunDef[i].getListSize() > 1 && (i+1) < listOfFunDef[i].getListSize())
				{
					if((i+1) < listOfFunDef[i].getListSize())
						display(", ");	
				}
			
			}
			
		}
		display("}");
		display("\n");
		display("\n");
		
		String [][]tempArray;
		
		displayln("Display parameter types of functions by position:\n");
		
		display("{");
		int count = 0;
		for(int i = 0; i < listOfFunDefIndex; i++)
		{
			tempArray = listOfFunDef[i].getArray();
			
			if(listOfFunDef[i].getSize() > 0)
			{
			
				display(listOfFunDef[i].getName() + "{");
			
				for(int j = 0; j < listOfFunDef[i].getSize(); j++)
				{
				
					display(variablePos.get(tempArray[j][0]) + "=" + tempArray[j][1]);
				
					// print a comma when appropriate
					if(listOfFunDef[i].getSize() > 1 && (count+1) < listOfFunDef[i].getSize())
					{
						display(", ");	
					}		
					count++;

				}
				count = 0;
				display("}");
			
				// print a comma when appropriate
				if(listOfFunDef[i].getListSize() > 1 && (i+1) < listOfFunDef[i].getListSize())
				{
					if((i+1) < listOfFunDef[i].getListSize())
						display(", ");	
				}
			}
			
			
		}
		display("}");
		displayln("\n");
		
		// Print Errors if they exist
		if(errorsIndex > 0)
		{
			for(int i = 0; i < errorsIndex; i++)
				displayln(errors[i]);
		}else
			displayln("All function definitions have passed type checking.");
		
	}	
	
}// end Parser
