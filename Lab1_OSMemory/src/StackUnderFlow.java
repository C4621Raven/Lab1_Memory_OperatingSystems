/**
 * exception for when a stack frame is attempted to be popped when no stack frame exists
 */
public class StackUnderFlow extends Exception
{
	/**
	 * exception for when a stack frame is attempted to be popped when no stack frame exists
	 */
	public StackUnderFlow()
	{
	}
	
	/**
	 * exception for when a stack frame is attempted to be popped when no stack frame exists
	 */
	public StackUnderFlow(String message)
	{
		super(message);
	}
}