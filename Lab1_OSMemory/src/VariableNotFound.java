/**
 * exception for when searching through the stack frame for a variable and it does not exist
 */
public class VariableNotFound extends Exception
{
	/**
	 * exception for when searching through the stack frame for a variable and it does not exist
	 */
	public VariableNotFound()
	{
	}
	
	/**
	 * exception for when searching through the stack frame for a variable and it does not exist
	 */
	public VariableNotFound(String message)
	{
		super(message);
	}
}
