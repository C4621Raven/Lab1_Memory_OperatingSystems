/**
* exception for when the memory initialization has been done wrong. this includes: total memory > Max integer, negative or 0 memory
*/
public class InvalidMemoryItialization extends Exception
{
	/**
	* exception for when the memory initialization has been done wrong. this includes: total memory > Max integer, negative or 0 memory
	*/
	public InvalidMemoryItialization()
	{
    }
	
	/**
	* exception for when the memory initialization has been done wrong. this includes: total memory > Max integer, negative or 0 memory
	*/
    public InvalidMemoryItialization(String message)
    {
       super(message);
    }
}
