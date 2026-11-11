/**
 * thrown when the a stack frame is attempted to be edited but no stack frame exists
 */
public class stackFrameDoesNotExist extends Exception
{
	/**
	 * thrown when the a stack frame is attempted to be edited but no stack frame exists
	 */
	public stackFrameDoesNotExist()
	{
	}
	
	/**
	 * thrown when the a stack frame is attempted to be edited but no stack frame exists
	 */
	public stackFrameDoesNotExist(String message)
	{
		super(message);
	}
}