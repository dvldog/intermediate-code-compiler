/**
 * 
 */

/**
 * @author dilanderoger
 * 
 * The FunctionDefinition class contains all relevant information about the function, such as function name, type,
 * variables, and number of variables
 *
 */
public class FunctionDefinition {
	
	String name;// Name of the function
	String type;// Type if the function
	String [][] variables;// Array to hold all function variables
	int index;// Variable used to traverse the first column of the variables array, which contains the variable name
	int index2;// Variable used to traverse the second column of the variables array, which contains the variable type
	int size;// Total number of variables
	final int DEFAULT_SIZE = 20;
	static int listSize = 0;// Total number of function classes created
	
	/*
	 * Constructor. Initialize variables
	 */
	FunctionDefinition()
	{
		index = 0;
		index2 = 0;
		name = "";
		type = "";
		size = 0;
	}// end FunctionDefinition
	
	/*
	 * Constructor. Initialize variables
	 * 
	 * @param n
	 * @param t
	 */
	FunctionDefinition(String n, String t)
	{
		name = n;
		type = t;
		index = 0;
		index2 = 0;
		variables = new String[DEFAULT_SIZE][2];
		listSize++;
	}// end FunctionDefinition
	
	/*
	 * Set the variable name and type
	 * 
	 * @param v
	 * @param t
	 */
	public void setVariables(String v,String t)
	{
		variables[index][index2++] = v;
		variables[index][index2] = t;
		index++;
		index2 = 0;
		size++;
	}// end setVariables
	
	/*
	 * Return the array of variables
	 */
	public String [][] getArray()
	{		
		return variables;
	}// end getArray
	
	/*
	 * Return a list of variables and their types for display to the user
	 */
	public String getVariables()
	{
		String list = "{";
		
		for(int i = 0; i < index; i++)
		{
			
			for(int j = 0; j < 2; j++)
			{
				list = list + variables[i][j];
				
				if( j == 0)
					list = list + "=";
				
			}
			
			if(index > 1 && (i+1) < index)
				list = list + ", ";
		}
		
		list = list + "}";
		
		return list;
	}// end getVariables
	
	/*
	 * Return the FunctionDefinition name
	 */
	public String getName()
	{
		return name;
	}// end getName
	
	/*
	 * Return the FunctionDefinition type
	 */
	public String getType()
	{
		return type;
	}// end getType
	
	/*
	 * Return the FunctionDefinition's amount of total variables
	 */
	public int getSize()
	{
		return size;
	}// end getSize
	
	/*
	 * Return the FunctionDefinition's total size of classes
	 */
	public int getListSize()
	{
		return listSize;
	}// end getListSize

}
