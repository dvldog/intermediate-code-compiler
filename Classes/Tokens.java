/**
 * The Tokens class holds the token value and state after the token has been
 * processed by the lexAnalyzer.
 * 
 * @author dilanderoger
 *
 */
public class Tokens extends Lexical_Analyzer{
	
	
	private String token;
	private State state;
	
	/*
	 * Constructor to initialize variables
	 */
	public Tokens()
	{
		token = "";
		state = State.Undef;
	}// end Tokens
	
	/*
	 * Sets the value of the token and the state
	 * 
	 * @param String s
	 * @param State st
	 */
	public void setValues(String s,State st)
	{
		token = s;
		state = st;
	}// end setValues
	
	/*
	 * return the stored token
	 */
	public String getToken()
	{
		return token;
	}// end getToken
	
	/*
	 * return the stored token state
	 */
	public State getTokenState()
	{
		return state;
	}//end getTokenState

}
