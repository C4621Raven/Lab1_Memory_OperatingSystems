import java.util.ArrayList;

public class Memory
{
	int[] memory; //the stack will start first. so the stack will exist from 0 to stackSize-1 and the heap will exist stackSize to stackSize+heapSize-1
	int stackSize, heapSize;
	
	int curStackFrame; //starting index of the current stack frame
	int curStackEnd; //ending index of the current stack frame
	
	//for the heap
	ArrayList<mallocedMem> usedMemory = new ArrayList<>();//this will always be in the order of start least to greatest
	
	//stack frame index 0: previous stack frame starting index
	//stack frame index 1: return value of the frame
	//stack frame index evens: name of variable in the stack (char)
	//stack frame index odds: value of the variable at index-1 example: variable 'x' at index 70 has value 9 at index 71
	
	public int getStackSize()
	{
		return stackSize;
	}
	
	public int getHeapSize()
	{
		return heapSize;
	}
	
	public int[] getMemory()
	{
		return memory;
	}
	
	public int getCurStackFrame()
	{
		return curStackFrame;
	}
	
	public int getCurStackEnd()
	{
		return curStackEnd;
	}
	
	public ArrayList<mallocedMem> getUsedMemory()
	{
		return usedMemory;
	}
	
	/**
	 * Initializes the size of the memory
	 * @param stackSize is the amount of the memory array allocated for the stack
	 * @param heapSize is the amount of the memory array allocated for the heap
	 * @throws InvalidMemoryItialization thrown when the initialization of the memory has been done incorrectly
	 */
	public Memory(int stackSize, int heapSize) throws InvalidMemoryItialization
	{
		if(stackSize <= 0)
		{
			throw new InvalidMemoryItialization("stackSize <= 0");
		}
		else if(heapSize <= 0)
		{
			throw new InvalidMemoryItialization("heapSize <= 0");
		}
		else if(stackSize+heapSize < 0)
		{
			throw new InvalidMemoryItialization("stackSize + heapSize exceeds the maximum integer limit");
		}
		
		memory = new int[stackSize+heapSize];
		this.stackSize = stackSize;
		this.heapSize = heapSize;
		
		curStackFrame = -1; //-1 represents 0 stack frames on the stack
		curStackEnd = -1;
		
		//adds a mallocedMem at the start of the heap so that we can tell where the start of the heap is
		usedMemory.add(new mallocedMem(stackSize-1,1));
		
		//adds a mallocedMem at the end of the heap so that we can tell where the end of the heap is
		usedMemory.add(new mallocedMem(stackSize+heapSize,0));
		
		sortUsedMemory();
	}
	 
	//STACK FUNCTIONS
	//--------------------------------------------------------------------------------------------------------------------------
	/**
	 * creates a new stack frame on the stack
	 * @throws StackOverFlow when their is not enough memory left in the stack for a new stack frame to be added
	 */
	public void pushStackFrame() throws StackOverFlow 
	{
		if(!(curStackEnd+2<stackSize))
		{
			throw new StackOverFlow("not enough memory left in the stack for a new stack frame to be added");
		}
		
		memory[curStackEnd+1]=curStackFrame;
		curStackFrame = curStackEnd+1;
		curStackEnd = curStackFrame+1;
		
		try
		{
			setReturnInt(0);
		}
		catch (stackFrameDoesNotExist e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * removes the current stack frame, returning to the previous stack frame
	 * @return returns the return value of the popping stack frame
	 * @throws StackUnderFlow when their a frame is attempted to be popped when their are no stack frames in the stack
	 */
	public int popStackFrame() throws StackUnderFlow
	{
		if(curStackFrame == -1)
		{
			throw new StackUnderFlow("no frames in the stack");
		}
		
		int returnVal = memory[curStackFrame+1];
		
		curStackEnd = curStackFrame-1;
		curStackFrame = memory[curStackFrame];
		
		return returnVal;
	}
	
	/**
	 * adds a name and value to the stack frame
	 * @param name is the name of the variable, char
	 * @param value is the associated value with the name, int
	 * @throws StackOverFlow thrown when their is not enough memory left in the stack for new data to the stack frame
	 * @throws DuplicateVariableName thrown when name already exists in the stack frame
	 * @throws stackFrameDoesNotExist thrown when no stack frame exists on the stack
	 */
	public void pushValueOnStack(char name, int value) throws StackOverFlow, DuplicateVariableName, stackFrameDoesNotExist
	{
		if(curStackFrame == -1)
		{
			throw new stackFrameDoesNotExist();
		}
		
		if(!(curStackEnd+2<stackSize))
		{
			throw new StackOverFlow("not enough memory left in the stack for data to be added to the current stack frame");
		}
		
		//loops through the stack frame, ignoring the first 2 values, checking only the even numbers, ie the names of the variables
		for(int i = 2; i <= curStackEnd-curStackFrame; i+=2)
		{
			if(memory[curStackFrame+i] == name)
			{
				throw new DuplicateVariableName("'"+name+"' already exists in the stack frame");
			}
		}
		
		memory[curStackEnd+1] = name;
		memory[curStackEnd+2] = value;
		
		curStackEnd+=2;
	}
	
	/**
	 * Attempts to find name and returns the associated value, throws an exception if the variable doesn't exist in the stack frame
	 * @param name is the name of the variable you want to find, char
	 * @return returns the value associated with name, int
	 * @throws VariableNotFound thrown when the variable name does not exist in the stack frame
	 * @throws stackFrameDoesNotExist thrown when no stack frame exists on the stack
	 */
	public int getValueOnStack(char name) throws VariableNotFound, stackFrameDoesNotExist
	{
		if(curStackFrame == -1)
		{
			throw new stackFrameDoesNotExist();
		}
		
		//loops through the stack frame, ignoring the first 2 values, checking only the even numbers, ie the names of the variables
		for(int i = 2; i <= curStackEnd-curStackFrame; i+=2)
		{
			if(memory[curStackFrame+i] == name)
			{
				return memory[curStackFrame+i+1];
			}
		}
		throw new VariableNotFound();
	}
	
	/**
	 * set the value of the variable associated with name to value in the current stack frame, throws an exception if the name does not exist in the stack frame
	 * @param name of the variable you want to set the value of, char
	 * @param value is the value you want to change the value name is associated with, int
	 * @throws VariableNotFound thrown when the variable name does not exist in the stack frame
	 * @throws stackFrameDoesNotExist thrown when no stack frame exists on the stack
	 */
	public void setValueOnStack(char name, int value) throws VariableNotFound, stackFrameDoesNotExist
	{
		if(curStackFrame == -1)
		{
			throw new stackFrameDoesNotExist();
		}
		
		//loops through the stack frame, ignoring the first 2 values, checking only the even numbers, ie the names of the variables
		for(int i = 2; i <= curStackEnd-curStackFrame; i+=2)
		{
			if(memory[curStackFrame+i] == name)
			{
				memory[curStackFrame+i+1] = value;
				return;
			}
		}
		throw new VariableNotFound();
	}
	
	/**
	 * Sets the return value of the current stack frame
	 * @param returnVal is the value you want to set the return value of the current stack frame to
	 * @return returns returnVal
	 * @throws stackFrameDoesNotExist thrown when no stack frame exists on the stack
	 */
	public int setReturnInt(int returnVal) throws stackFrameDoesNotExist
	{
		if(curStackFrame == -1)
		{
			throw new stackFrameDoesNotExist();
		}
		
		memory[curStackFrame+1] = returnVal;
		return returnVal;
	}
	
	//HEAP----------------------------------------------------------------------------------------------------------------------------
	/**
	 * internal class the is a touple of the starting index of a malloced piece of memory in the heap and the size of the memory
	 * start and size are public varibles so no need to uses get and set
	 */
	private class mallocedMem
	{
		public int start; //starting index of he malloced memory in the total memory
		public int size; //size of the memory
		
		/**
		 * touple of the memory that is in use in the heap
		 * @param start is the starting index of the memory
		 * @param size is the size allocated for this memory
		 */
		public mallocedMem(int start, int size)
		{
			this.start = start;
			this.size = size;
		}
		
		public String toString()
		{
			return "("+start + ":" + size+")";
		}
		
	} 

	/**
	 * sorts usedMemory by start from least to greatest
	 * always call after messing with usedMemory
	 */
	private void sortUsedMemory()
	{
		usedMemory.sort((a,b)->{return a.start-b.start;});
	}
	
	/**
	 * allocates memory in the heap
	 * @param size is the size of the memory you want to allocate
	 * @return is the index location in memory where the beginning of the malloced memory is
	 * @throws OutOfMemory is thrown if their is not enough room in the contiguous heap memory
	 */
	public int malloc(int size) throws OutOfMemory
	{			
		for(int i = 0; i < usedMemory.size()-1;i++)
		{
			int avalSize = usedMemory.get(i+1).start - usedMemory.get(i).start - usedMemory.get(i).size;
			
			if(avalSize>=size)
			{
				usedMemory.add(new mallocedMem((usedMemory.get(i).start + usedMemory.get(i).size), size));
				sortUsedMemory();
				return (usedMemory.get(i).start + usedMemory.get(i).size);
			}
		}
		
		throw new OutOfMemory();
	}
	
	/**
	 * frees up the space at in the memory at index location. Note: the data of the memory still remains until it is overwritten, so freed memory can potentialy still be read
	 * @param location is the index of the allocated memory in memory, given when malloc is called
	 * @throws MemoryNotFound thrown if location has not been malloced
	 */
	public void free(int location) throws MemoryNotFound
	{
		//the for loop is set up so it skips the first and last value, which indicate the start and end of the heap
		for(int i = 1; i<usedMemory.size()-1;i++)
		{
			if(usedMemory.get(i).start==location)
			{
				usedMemory.remove(i);
				return;
			}
		}
		
		throw new MemoryNotFound();
	}
	
	/**
	 * 
	 * @param location is the index location in memory where the beginning of the malloced memory is
	 * @param size is the amount of values you want returned
	 * @return int[] of length size from sub-index 0 to size of the allocated memory at location
	 * @throws MemoryOverRun thrown when the memory you are trying to obtain exceeds the total memory
	 */
	public int[] get(int location, int size) throws MemoryOverRun
	{
		if(location+size >= stackSize+heapSize)
			throw new MemoryOverRun();
		
		//by the nature of the following code, only memory within location and size can be obtained
		int[] out = new int[size];
		
		for(int i = location; i < location+size; i++)
		{
			out[i-location] = memory[i];
		}
		
		return out;
	}
	
	/**
	 * sets the data in location to values, starting at sub-index of the allocated memory 0
	 * @param values data that is set in the allocated memory 
	 * @param location is the index location in memory where the beginning of the malloced memory is
	 * @param size is the size of the memory allocated with location
	 * @throws MemoryOverRun thrown when the memory you are trying to set exceeds the total memory
	 */
	public void set(int[] values, int location, int size) throws MemoryOverRun
	{
		if(location+size >= stackSize+heapSize || values.length > size)
			throw new MemoryOverRun();
		
		for(int i = 0; i < size; i++)
		{
			memory[i+location] = values[i];
		}
	}
}