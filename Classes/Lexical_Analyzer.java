import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * The Lexical_Analyzer class accepts 26 token categories by reading an input file, extracting
 * the tokens and storing them in array.  In this version of the Lexical_Analyzer, all extracted
 * tokens are stored in an array whether they are invalid or not.  All tokens will then be processed by
 * the Parser class. 
 *    
 *
 * @author dilanderoger
 *
 */
public class Lexical_Analyzer {
	

	public enum State
	{
		//non-final states
		Start,
		
		//final states			
		Plus,
		Minus,
		Times,
		Div,
		LParen,
		RParen,
		Equal,
		Le,
		Ge,	
		Id,
		Int,
		Keyword_int,
		Float,
		Keyword_float,
		FloatE,
		Keyword_boolean,
		Keyword_if,
		Keyword_then,
		Keyword_else,
		Keyword_and,
		Keyword_or,
		Keyword_not,
		Keyword_false,
		Keyword_true,
		Gt,
		Lt,		
		Undef
	}
	
	private static BufferedReader inStream;
	public static String outFile;
	public static PrintWriter dataOut;
	public static State state;
	private static int i;// Integer value of a single token read from the input file 
	private static char c;// Character value of i
	private static String str;// Token(s)
	private static String newString;// Place holder for potential invalid tokens
	private static boolean hasExponent = false;// Whether or not the float string contains an exponent
	private static boolean hasSign = false;// Whether or not the float string contains a sign
	private static final int DEFAULT_SIZE = 1000;
	private static int size;// Current size of the tokens array
	private static int iterator;// Integer used for index location of the tokens array
	public static int iterator_2;// Integer used for index location of the tokens array used by the Parser class
	protected static Tokens[] tokens = new Tokens[DEFAULT_SIZE];// Array for token storage
		
	/*Sets the input file from which the tokens will be extracted.
	 * Reads the first token of the file and sets the state to "Start"
	 * 
	 * @param String inFile
	 */
	public static void setInStream(String inFile,String oF)
	{
		str = "";
		newString = "";
		iterator_2 = 0;
		
		setOutStream(oF);
		
		try
		{
			state = State.Start;
			inStream = new BufferedReader(new FileReader(inFile));
				
			i = inStream.read();
	
			nextState(state,i);
			
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}// end setInStream
	
	/*
	 * Set the output file
	 */
	public static void setOutStream(String oF)
	{
		try {
			
			dataOut = new PrintWriter(oF);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//end setOutStream
	
	/*
	 * Close the out put file
	 */
	public static void closeOutputFile()
	{
		dataOut.close();
	}//end closeOutPutFile
	
	/*Extracts the next token from the input file.
	 * 
	 * @return i
	 */
	public static int getNextChar()
	{
		try{
							
			//Compare the ordinal number to determine whether it is a final state or not.
			//If it is a final state, reset the state to the "Start" state in order to evaluate
			//the next token that is read
			if(compareOrdinal(state) == true)
			{
				state = State.Start;
				str = "";
			}
				
				i = inStream.read();
						
			}catch(IOException e)
			{
				e.printStackTrace();
				i = -1;
				
			}
			
			nextState(state,i);
		
		return i;
	}// end getNextChar
	
	/*
	 * Determine the next state of the analyzer by using switch statements
	 * 
	 * @param State s
	 * @param int i
	 * @ return state
	 */
	public static State nextState(State s, int i)
	{
		int i2 = i;
		
		switch(state)
		{
		case Start:
			
			if((char)i2 == '+')
			{
				c = (char)i2;
				str = "" + c;
				// Check for an integer that might follow this token
				// If the next token is an integer or float, return the corresponding state
				if(checkForValidToken() == true)
				{
					return state;
				}else
				{
				
				// Token evaluated after "+" is not an integer, therefore, "Plus" is the
				// current state.
				state = State.Plus;
				print(state,str);
				
				// Reset the state in order to evaluate next token
				state = State.Start;
				i2 = getC();
				str = "";

				return nextState(state,i2);
				
				}
			}else if((char)i2 == '-')
			{
				c = (char)i2;
				str = "" + c;
				
				if(checkForValidToken() == true)
				{
					return state;
				}else
				{				
				
				// Token evaluated after "-" is not an integer, therefore, "Minus" is the
				// current state.
				state = State.Minus;
				print(state,str);
				
				// Reset the state in order to evaluate next token
				state = State.Start;
				i2 = getC();
				str = "";
				
				return nextState(state,i2);
				
				}
			}else if((char)i2 == '*')
			{
				c = (char)i2;
				str = "" + c;
				state = State.Times;
				print(state,str);
				
				return state;
			}else if((char)i2 == '/')
			{
				c = (char)i2;
				str = "" + c;
				state = State.Div;
				print(state,str);
				
				return state;
			}else if((char)i2 == '(')
			{
				c = (char)i2;
				str = "" + c;
				state = State.LParen;
				print(state,str);
				
				return state;
			}else if((char)i2 == ')')
			{
				c = (char)i2;
				str = "" + c;
				state = State.RParen;
				print(state,str);
				
				return state;
			}else if((char)i2 == '=')
			{
				c = (char)i2;
				str = "" + c;
				state = State.Equal;
				print(state,str);
				
				return state;
			}else if((char)i2 == '<')
			{
				c = (char)i2;
				str = "" + c;
				state = State.Lt;
				
				return state;
			}else if((char)i2 == '>')
			{
				c = (char)i2;
				str = "" + c;
				state = State.Gt;
				
				return state;
			}else if((char)i2 == '.')
			{
				c = (char)i2;
				state = State.Float;
				str = str + c;
				
				return state;
			}else if(Character.isLetter((char)i2))
			{
				str = str + (char)i2;
				state = State.Id;
				
				return state;
			}else if(Character.isDigit((char)i2))
			{
				str = str + (char)i2;
				state = State.Int;
				
				return state;
			}else if(Character.isWhitespace((char)i2))
			{
				try {
					
					
					i = inStream.read();
					i2 = i;
					c = (char)i2;
					state = State.Start;
					
					return nextState(state,i2);
					
				} catch (IOException e) {
		
					e.printStackTrace();
				}
			}else
			{
				state = State.Undef;
				
				return nextState(state,i2);
			}
		case Lt:
				
				if((char)i2 == '=')
				{
					c = (char)i2;
					str = str + c;
					state = State.Le;
					
					return nextState(state,i2);
					
				}else
				{
					// Print token held in str and change state to Start to evaluate
					// the next token
					print(state,str);
					state = State.Start;
					c = (char)i2;
					str = "";
					// Evaluate current token 
					return nextState(state,i2);
				}
		case Le:
			
			print(state,str);
			
			return state;
		case Gt:
			if((char)i2 == '=')
			{
				c = (char)i2;
				str = str + c;
				state = State.Ge;
				
				return nextState(state,i2);
				
			}else
			{
				// Print token held in str and change state to Start to evaluate
				// the next token
				print(state,str);
				state = State.Start;
				str = "";
				c = (char)i2;
				// Evaluate current token
				return nextState(state,i2);
			}
		case Ge:
			
			print(state,str);
			
			return state;
		case Id:
			
			if(Character.isLetter((char)i2))
			{
				c = (char)i2;
				str = str + c;
				
				if(str.equals("int"))
				{
					state = State.Keyword_int;
					return state;			
				}else if(str.equals("float"))
				{
					state = State.Keyword_float;
					return state;
				}else if (str.equals("boolean"))
				{
					state = State.Keyword_boolean;
					return state;
				}else if (str.equals("if"))
				{
					state = State.Keyword_if;
					return state;
				}else if (str.equals("then"))
				{
					state = State.Keyword_then;
					return state;
				}else if (str.equals("else"))
				{
					state = State.Keyword_else;
					return state;
				}else if (str.equals("and"))
				{
					state = State.Keyword_and;
					return state;
				}else if (str.equals("or"))
				{
					state = State.Keyword_or;
					return state;
				}else if (str.equals("not"))
				{
					state = State.Keyword_not;
					return state;
				}else if (str.equals("false"))
				{
					state = State.Keyword_false;
					return state;
				}else if (str.equals("true"))
				{
					state = State.Keyword_true;
					return state;
				}
				
				return state;
				
			}else if(Character.isDigit((char)i2))
			{
				c = (char)i2;
				str = str + (char)i2;
				
				return state;
			}else if((char)i2 == '_')
			{
				c = (char)i2;
				str = str + c;
				i = nextChar();
				i2 = i;
				c = (char)i2;
				
				// After the underscore token is extracted, a letter or digit should follow
				if(Character.isLetter((char)i2) || Character.isDigit((char)i2))
				{
					str = str + c;
					return state;
				}else
				{
					str = str + c;
					//dataOut.println("--Invalid Token: " + str);
					state = State.Undef;
					print(state,str);
					state = State.Start;
					str = "";
					return state;
				}
				
			}else if(Character.isWhitespace((char)i2))
			{
				print(state,str);
				state = State.Start;
				str = "";
				
				return state;
				
			}else
			{
				print(state,str);
				state = State.Start;
				
				return nextState(state,i2);
			}
			
		case Int:
			
			if(Character.isDigit((char)i2))
			{
				c = (char)i2;
				str = str + c;
				
				return state;
			}
			else if((char)i2 == '.')
			{
				if(isFloat(str,(char)i2) == true);
				{
					c = (char)i2;
					state = State.Float;
					str = str + c;
					
					return state;
				}
				
			}else if((char)i2 == 'e' || (char)i2 == 'E')
			{
				c = (char)i2;
				state = State.Float;
				
				return nextState(state,i2);
				
			}else
			{
				print(state,str);
				state = State.Start;
				str = "";
				
				return nextState(state,i2);
				
			}
		case Keyword_int:
			
			if(Character.isWhitespace((char)i2))
			{
				print(state,str);
				str = "";
				state = State.Start;
				c = (char)i2;
				return state;
				
			}else
			{
				c = (char)i2;
				state = State.Id;
				
				return nextState(state,i2);
			}
		case Keyword_boolean:
			
			if(Character.isWhitespace((char)i2))
			{
				print(state,str);
				str = "";
				state = State.Start;
				c = (char)i2;
				return state;
				
			}else
			{
				c = (char)i2;
				state = State.Id;
				
				return nextState(state,i2);
			}
		case Keyword_if:
			
			if(Character.isWhitespace((char)i2))
			{
				print(state,str);
				str = "";
				state = State.Start;
				c = (char)i2;
				return state;
				
			}else
			{
				c = (char)i2;
				state = State.Id;
				
				return nextState(state,i2);
			}
		case Keyword_then:
			
			if(Character.isWhitespace((char)i2))
			{
				print(state,str);
				str = "";
				state = State.Start;
				c = (char)i2;
				return state;
				
			}else
			{
				c = (char)i2;
				state = State.Id;
				
				return nextState(state,i2);
			}
		case Keyword_else:
			
			if(Character.isWhitespace((char)i2))
			{
				print(state,str);
				str = "";
				state = State.Start;
				c = (char)i2;
				return state;
				
			}else
			{
				c = (char)i2;
				state = State.Id;
				
				return nextState(state,i2);
			}
		case Keyword_and:
			
			if(Character.isWhitespace((char)i2))
			{
				print(state,str);
				str = "";
				state = State.Start;
				c = (char)i2;
				return state;
				
			}else
			{
				c = (char)i2;
				state = State.Id;
				
				return nextState(state,i2);
			}
		case Keyword_or:
			
			if(Character.isWhitespace((char)i2))
			{
				print(state,str);
				str = "";
				state = State.Start;
				c = (char)i2;
				return state;
				
			}else
			{
				c = (char)i2;
				state = State.Id;
				
				return nextState(state,i2);
			}
		case Keyword_not:
			
			if(Character.isWhitespace((char)i2))
			{
				print(state,str);
				str = "";
				state = State.Start;
				c = (char)i2;
				return state;
				
			}else
			{
				c = (char)i2;
				state = State.Id;
				
				return nextState(state,i2);
			}
		case Keyword_false:
			
			if(Character.isWhitespace((char)i2))
			{
				print(state,str);
				str = "";
				state = State.Start;
				c = (char)i2;
				return state;
				
			}else
				if((char)i2 == ')')
				{
					print(state,str);
					str = "";
					state = State.Start;
					c = (char)i2;
					
					return nextState(state,i2);
				}
				else
					{
						c = (char)i2;
						state = State.Id;
				
						return nextState(state,i2);
					}
		case Keyword_true:
			
			if(Character.isWhitespace((char)i2))
			{
				print(state,str);
				str = "";
				state = State.Start;
				c = (char)i2;
				
				return state;
				
			}else
				if((char)i2 == ')')
				{
					print(state,str);
					str = "";
					state = State.Start;
					c = (char)i2;
					
					return nextState(state,i2);
				}
				else
				{
					c = (char)i2;
					state = State.Id;
				
					return nextState(state,i2);
				}
		case Float:
						
			if(Character.isDigit((char)i2))
			{
				c = (char)i2;
				str = str + c;
				
				return state;
			// Evaluate the string to determine whether it is positive or negative	
			}else if( str.equals(".") && Character.isDigit((char)i2) || 
					str.equals("+.") && Character.isDigit((char)i2)	|| 
					str.equals("-.") && Character.isDigit((char)i2))
			{
				c = (char)i2;
				str = str + c;
				
				return state;
				
			}else if( (str.equals(".")||str.equals("+.")||str.equals("-.")) && 
					!(Character.isDigit((char)i2))	){
				
				if(str.equals("."))
				{
					state = State.Undef;
					print(state,str);
					state = State.Start;
					str = "";
					
					return nextState(state,i2);
					
				}else if(str.equals("+."))
				{
					state = State.Plus;
					newString = str.charAt(0) + "";
					print(state,newString);
					
					newString = str.charAt(1) + "";
					state = State.Undef;
					print(state,str);
					state = State.Start;
					str = "";
					newString = "";
					
				}else if(str.equals("-."))
				{
					state = State.Minus;
					newString = str.charAt(0) + "";
					print(state,newString);
					
					newString = str.charAt(1) + "";
					state = State.Undef;
					print(state,str);
					state = State.Start;
					str = "";
					newString = "";
				}
				state = State.Start;
				return nextState(state,i2);
				
			}else if( ( (char)i2 == 'e'||(char)i2 == 'E') && hasExponent == false)
			{
				hasExponent = true;
				str = str + (char)i2;
				
				//newString is used to evaluate any other characters that are not 
				//valid float characters.
				newString = newString + (char)i2;
				
				//get the next character from the input file to determine whether 
				//the next token is a '+' or '-'.  If it is, read the next character
				//to determine whether the next character is an integer.  Else,
				//print the float value, evaluate all other characters that newString
				//contains individually
				i2 = nextChar();
				
				newString = newString + (char)i2;
																
				if(checkSign((char)i2) == true) 
				{
					str = str + (char)i2;
					
					i2 = nextChar();
					
					
					if(Character.isDigit((char)i2))
					{
						str = str + (char)i2;
						
						state = State.FloatE;
						newString = "";
						
						return state;
						
					//At this stage, the float value contains an 'e' or 'E' and
					//maybe a '-' or '+' but all other tokens that followed are invalid
					//float tokens
					}else
					{
						newString = newString + (char)i2;
						str = str.substring(0, str.length() - 2);	
						print(state,str);
						hasExponent = false;
						str = "";
						state = State.Start;
									
						//Loop that evaluates all the invalid tokens extracted after
						//the float value that are stored in newString
						for(int j = 0; j < newString.length(); j++)
						{
							
							nextState(state,newString.charAt(j));
							
							//If the token is a character that is in the final state,
							//reset the state, set str = "", and evaluate the next
							//character
							if(compareOrdinal(state) == true)
							{
								state = State.Start;
								str = "";
							}							
						
						}
						
						newString = "";
						
						return state;
						
						
					}
					
				}else if(Character.isDigit((char)i2) == true){
					
					str = str +(char)i2;
					newString = "";
					state = State.FloatE;
					
					return state;
				
				}else
				{
					
					str = str.substring(0, str.length() - 1);
					print(state,str);
					hasExponent = false;
					str = "";
					state = State.Start;
					
					//Loop that evaluates all the invalid tokens extracted after
					//the float value that are stored in newString
					for(int j = 0; j < newString.length(); j++)
					{
						
						nextState(state,newString.charAt(j));					
					
					}
					
					newString = "";
					
					return state;
				}
					
			}else
			{	
				//At this stage, this float value contains no exponents 
				print(state,str);
				state = State.Start;
				str = "";
				newString = "";
				hasExponent = false;
				nextState(state,(char)i2);
				
				return state;
					
			}
			case Keyword_float:
				
				if(Character.isWhitespace((char)i2))
				{
					print(state,str);
					str = "";
					state = State.Start;
					c = (char)i2;
					return state;
					
				}else
				{
					c = (char)i2;
					state = State.Id;
					
					return nextState(state,i2);
				}
			
			case FloatE:
				
				if(Character.isDigit((char)i2))
				{
					str = str + (char)i2;		
					
					return state;
					
				}else
				{	
					//Store the token that isn't a digit in newString
					newString = newString + (char)i2;
					print(state,str);
					hasExponent = false;
					str = "";
					state = State.Start;
					
					//Loop that evaluates all the invalid tokens extracted after
					//the float value that are stored in newString
					for(int j = 0; j < newString.length(); j++)
					{
						
						nextState(state,newString.charAt(j));					
					
					}
					
					newString = "";
					
					return state;
			
				}
			
						
			case Undef:
			
				str = "";
				str = "" + (char)i2;
				state = State.Start;
				str = "";
				
				return state;
				
		}
		
		return state;
		
	}// end nextState(State s, int i)
	
	/*Return the integer value i, which is used to detrmine whether the end of file has been
	 * reached
	 * 
	 * @return i
	 */
	public static int returnI()
	{
		return i;
	}// end returnI()
	
	/*
	 * Determine whether the current ordinal state number is a final state  other than 
	 * the "Id" state and the "Int" state
	 * 
	 * @param State s
	 * @return state.ordinal() > State.Start.ordinal() && state.ordinal() < State.Id.ordinal()
	 */
	public static boolean compareOrdinal(State s)
	{		
		
		return(state.ordinal() > State.Start.ordinal() && state.ordinal() < State.Id.ordinal());
	}// end compareOrdinal(State s)
	
	/*
	 * Print the current state and the token extracted
	 * 
	 * @param State ste
	 * @param String strg
	 */
	public static void print(State ste, String strg )
	{
		tokens[iterator] = new Tokens();
		tokens[iterator].setValues(strg, ste);
		iterator++;
		size = iterator;
		
	}// end print(State ste, String strg)

/*
 * This method evaluates the current string to determine if it is a float
 * 
 * @param String strg
 * @param Character c
 */
	public static boolean isFloat(String strg, Character c)
	{
		boolean hasDecimal = false;
		int decimalCount = 0;
		
		for(int i = 0; i < strg.length(); i++)
		{
			if(strg.charAt(i) == '.')
			{
				decimalCount += 1;
			}
		}
		
		if( decimalCount == 1)
			hasDecimal = true;
		
		return hasDecimal;
	}//end isFloat(String strg, Character c)
	
	/*
	 * This method checks the extracted token from the float string to determine 
	 * if the exponent is positive or negative
	 * 
	 * @param int k
	 */
	public static boolean checkSign(int k)
	{
		
		if((char)k == '+' || (char)k == '-')
		{
			hasSign = true;
			
		}else
		{
			hasSign = false;

		}
		
		
		return hasSign;
	}//end checkSign(int k)
	
	/*
	 * Read the next Character from the input file
	 */
	public static int nextChar()
	{
		try {
			i = inStream.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return i;
	}// end nextChar()
	/*
	 * Close the input file
	 */
	public static void closeInputFile()
	{
		try {
			
			inStream.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end closeInputFile()
	
	/*
	 * Return the character c
	 */
	public static char getC()
	{
		return c;
	}// end getC()
	
	/*
	 * Check the next token after the current token '+' or '-' to determine
	 * whether the current string will possibly be an int or float
	 */
	public static boolean checkForValidToken()
	{
		boolean isValid = false;
		
		i = nextChar();
		c = (char)i;
		
		if(Character.isDigit((char)i))
		{
			c = (char)i;
			str = str + c;
			isValid = true;
			state = State.Int;
		}else if((char)i == '.')
		{
			c = (char)i;
			str = str + c;
			state = State.Float;
			isValid = true;
			
		}
		
		return isValid;
	}// end checkForValidToken()
	
	/*
	 * Return the current State
	 */
	public static State getState()
	{
		return state;
	}// end getState
	
	/*
	 * Return size of the Tokens array
	 */
	public static int getSizeOfTokensArray()
	{
		return size;
	}//end getSizeOfArray
	
	public static void getContentsOfTokensArray()
	{
		for(int i = 0; i < getSizeOfTokensArray(); i++)
			System.out.println(tokens[i].getToken() + " " + tokens[i].getTokenState());
	}
	/*
	 * Return the array of token(s)
	 */
	
	public static Tokens[] getArray()
	{
		return tokens;
	}// end getArray
	
	/*
	 * Print the current String
	 */
	public static void displayln(String s)
	{
		dataOut.println(s);
	}// end displayln
	
	public static void display(String s)
	{
		dataOut.print(s);
	}// end displayln
	
	/*
	 * Iterate array to next token index
	 */
	public static void getNextToken()
	{
		iterator_2++;
		
	}// end getToken
}
