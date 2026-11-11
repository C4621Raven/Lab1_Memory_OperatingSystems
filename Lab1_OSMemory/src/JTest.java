import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class JTest
{
	//tests for creating the stack and heap
	@Test
	public void stackTooSmall()
	{
		boolean thrown = false;
		
		try
		{
			Memory m = new Memory(0,1);
		}
		catch (InvalidMemoryItialization e)
		{
			thrown = true;
		}
		
		if(!thrown)
		{
			fail("InvalidMemoryItialization not thrown when stack size is <= 0");
		}
	}
	
	@Test
	public void heapTooSmall()
	{
		boolean thrown = false;
		
		try
		{
			Memory m = new Memory(1,0);
		}
		catch (InvalidMemoryItialization e)
		{
			thrown = true;
		}
		
		if(!thrown)
		{
			fail("InvalidMemoryItialization not thrown when heap size is <= 0");
		}
	}
	
	@Test
	public void totalMemoryTooBig()
	{
		boolean thrown = false;
		
		try
		{
			Memory m = new Memory(Integer.MAX_VALUE,1);
		}
		catch (InvalidMemoryItialization e)
		{
			thrown = true;
		}
		
		if(!thrown)
		{
			fail("InvalidMemoryItialization not thrown when stack size amd heap size exceed the integer litmit");
		}
	}
	
	public void stackSetup()
	{
		try
		{
			Memory m = new Memory(8,12);
			if(m.curStackFrame != -1)
				fail("curStackFrame != -1 when stack is created");
			if(m.curStackEnd != -1)
				fail("curStackEnd != -1 when stack is created");
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
	}
	
	public void heapSetup()
	{
		try
		{
			Memory m = new Memory(8,12);
			if(m.getUsedMemory().toString().equals("[(7:1), (20:0)]"))
				fail("ounter bounds of the heapspace is not created");
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
	}
	
	//stack---------------------------------------------------------
	//push stack frame
	@Test
	public void pushOneStackFrame()
	{
		try
		{
			Memory m = new Memory(8,12);
			
			try
			{
				m.pushStackFrame();
				if(m.curStackFrame != 0)
					fail("curStackFrame != 0 when first stack frame is created");
				if(m.curStackEnd != 1)
					fail("curStackEnd != 1 when first stack frame is created");
				if(m.memory[0]!=-1)
					fail("starting position of previus stack frame is not -1");
				
			}
			catch (StackOverFlow e)
			{
				e.printStackTrace();
			}
			
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
	}
	
	//edit return value
	@Test
	public void editReturnvalueOfStackFrame()
	{
		try
		{
			Memory m = new Memory(8,12);
			
			try
			{
				m.pushStackFrame();
				try 
				{
					m.setReturnInt(200);
				}
				catch (stackFrameDoesNotExist e)
				{
					e.printStackTrace();
				}
				
				if(m.memory[1]!=200)
					fail("return value of stack frame not set properly");
				
			}
			catch (StackOverFlow e)
			{
				e.printStackTrace();
			}
			
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
	}
	
	//add values to stack frame
	@Test
	public void pusValuesOntoStack()
	{
		try
		{
			Memory m = new Memory(8,12);
			
			try
			{
				m.pushStackFrame();
				try
				{
					m.pushValueOnStack('A', 2);
				}
				catch (DuplicateVariableName e)
				{
					e.printStackTrace();
				}
				catch (stackFrameDoesNotExist e)
				{
					e.printStackTrace();
				}
				if(m.curStackFrame != 0)
					fail("curStackFrame != 0 when values are added to the stack");
				if(m.curStackEnd != 3)
					fail("curStackEnd != 3 when values are added to the stack");
				if(m.memory[2]!='A')
					fail("vaiable name not added properly to the stack frame");
				if(m.memory[3]!=2)
					fail("vaiable value not added properly to the stack frame");
				
				try
				{
					m.pushValueOnStack('B', 3);
				}
				catch (DuplicateVariableName e)
				{
					e.printStackTrace();
				}
				catch (stackFrameDoesNotExist e)
				{
					e.printStackTrace();
				}
				if(m.curStackFrame != 0)
					fail("curStackFrame != 0 when values are added to the stack");
				if(m.curStackEnd != 5)
					fail("curStackEnd != 5 when values are added to the stack");
				if(m.memory[4]!='B')
					fail("vaiable name not added properly to the stack frame");
				if(m.memory[5]!=3)
					fail("vaiable value not added properly to the stack frame");
			}
			catch (StackOverFlow e)
			{
				e.printStackTrace();
			}
			
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
	}
	
	//pop stack frame
	public void popFirstStackFrame()
	{
		try
		{
			Memory m = new Memory(8,12);
			
			try
			{
				m.pushStackFrame();
				try
				{
					m.pushValueOnStack('A', 2);
				}
				catch (DuplicateVariableName e)
				{
					e.printStackTrace();
				}
				catch (stackFrameDoesNotExist e)
				{
					e.printStackTrace();
				}
				
				try
				{
					m.pushValueOnStack('B', 3);
				}
				catch (DuplicateVariableName e)
				{
					e.printStackTrace();
				}
				catch (stackFrameDoesNotExist e)
				{
					e.printStackTrace();
				}
				
				try 
				{
					m.setReturnInt(5);
				}
				catch (stackFrameDoesNotExist e)
				{
					e.printStackTrace();
				}
				
				try
				{
					if(5!=m.popStackFrame())
						fail("return value of stack frame not returned");
				}
				catch (StackUnderFlow e)
				{
					e.printStackTrace();
				}
				
				if(m.curStackFrame != -1)
					fail("curStackFrame != -1 when first stack frame is popped");
				if(m.curStackEnd != -1)
					fail("curStackEnd != -1 when first stack frame is popped");
			}
			catch (StackOverFlow e)
			{
				e.printStackTrace();
			}
			
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
	}
	
	//push 3 stack frames
	//and edit values of stack frames
	@Test
	public void pushMultipleFrames()
	{
		try
		{
			Memory m = new Memory(12,1);
			
			try
			{
				m.pushStackFrame();
				m.pushValueOnStack('A', 2);
				m.pushValueOnStack('B', 3);
				m.setReturnInt(10);
				
				m.pushStackFrame();
				m.setReturnInt(9);
				
				m.pushStackFrame();
				m.pushValueOnStack('a', 4);
				m.setReturnInt(8);
				
				int[] wantedMemory = {-1,10,'A',2,'B',3,0,9,4,8,'a',4,0};
				
				if(m.memory.equals(wantedMemory))
					fail("memory has not been added to the stack properly");
				if(m.curStackFrame != 8)
					fail("curStackFrame != 8");
				if(m.curStackEnd != 11)
					fail("curStackEnd != 11");
			}
			catch (StackOverFlow | DuplicateVariableName | stackFrameDoesNotExist e)
			{
				e.printStackTrace();
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
	}
	
	//pop 3 stack frames
	@Test
	public void popMultipleFrames()
	{
		try
		{
			Memory m = new Memory(12,1);
			
			try
			{
				m.pushStackFrame();
				m.pushValueOnStack('A', 2);
				m.pushValueOnStack('B', 3);
				m.setReturnInt(10);
				
				m.pushStackFrame();
				m.setReturnInt(9);
				
				m.pushStackFrame();
				m.pushValueOnStack('a', 4);
				m.setReturnInt(8);
				
				m.popStackFrame();
				m.popStackFrame();
				m.popStackFrame();
				
				if(m.curStackFrame != -1)
					fail("curStackFrame != -1");
				if(m.curStackEnd != -1)
					fail("curStackEnd != -1");
			}
			catch (StackUnderFlow | StackOverFlow | DuplicateVariableName | stackFrameDoesNotExist e)
			{
				e.printStackTrace();
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
	}
	
	//getValue
	@Test
	public void getValueOnStackTest()
	{
		try
		{
			Memory m = new Memory(12,1);
			
			try
			{
				m.pushStackFrame();
				m.pushValueOnStack('A', 2);
				m.pushValueOnStack('B', 3);
				m.setReturnInt(10);
				
				if(m.getValueOnStack('A') != 2)
					fail("value not properly retrived");
			}
			catch (VariableNotFound | StackOverFlow | DuplicateVariableName | stackFrameDoesNotExist e)
			{
				e.printStackTrace();
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
	}
	
	//setValue
	@Test
	public void setValueOnStackTest()
	{
		try
		{
			Memory m = new Memory(12,1);
			
			try
			{
				m.pushStackFrame();
				m.pushValueOnStack('A', 2);
				m.pushValueOnStack('B', 3);
				m.setReturnInt(10);
				
				m.setValueOnStack('A',4);
				
				if(m.getValueOnStack('A') != 4)
					fail("value not properly set");
			}
			catch (VariableNotFound | StackOverFlow | DuplicateVariableName | stackFrameDoesNotExist e)
			{
				e.printStackTrace();
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
	}
	
	//stack underflow
	@Test
	public void stackUnderFlowTests()
	{
		boolean thrown = false;
		
		try
		{
			Memory m = new Memory(10,10);
			
			try
			{
				m.popStackFrame();
			}
			catch (StackUnderFlow e)
			{
				thrown = true;
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
		
		if(!thrown)
			fail("stackUnderFlow not thrown when popping an empty stack");
	}
	
	//push too many stack frames for stackOverflow
	@Test
	public void frameStackOverFlow()
	{
		boolean thrown = false;
		
		try
		{
			Memory m = new Memory(2,1);
			
			try
			{
				m.pushStackFrame();
				m.pushStackFrame();
			}
			catch (StackOverFlow e)
			{
				thrown = true;
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
		
		if(!thrown)
			fail("stackUnderFlow not thrown when popping an empty stack");
	}
	
	//add too many values for stack overflow
	@Test
	public void variableStackOverFlow()
	{
		boolean thrown = false;
		
		try
		{
			Memory m = new Memory(2,1);
			
			try
			{
				m.pushStackFrame();
				try
				{
					m.pushValueOnStack('a', 2);
				}
				catch (DuplicateVariableName | stackFrameDoesNotExist e) {
					e.printStackTrace();
				}
			}
			catch (StackOverFlow e)
			{
				thrown = true;
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
		
		if(!thrown)
			fail("stackUnderFlow not thrown when popping an empty stack");
	}
	
	//duplicatevariabename
	@Test
	public void DuplicateVariableNameTest()
	{
		boolean thrown = false;
		
		try
		{
			Memory m = new Memory(10,10);
			
			m.pushStackFrame();
			
			try
			{
				m.pushValueOnStack('a', 2);
				m.pushValueOnStack('a', 3);
			}
			catch (DuplicateVariableName e)
			{
				thrown = true;
			}
		}
		catch (InvalidMemoryItialization | stackFrameDoesNotExist | StackOverFlow e)
		{
			e.printStackTrace();
		}
		
		if(!thrown)
			fail("DuplicateVariableName not thrown when adding 2 variables of the same name");
	}
	
	//push val stackframedoesnotexist
	@Test
	public void pushValStackFrameDoesNotExistTest()
	{
		boolean thrown = false;
		
		try
		{
			Memory m = new Memory(10,12);
			
			
			try
			{
				m.pushValueOnStack('a', 0);
			}
			catch (StackOverFlow | DuplicateVariableName e)
			{
				e.printStackTrace();
			}
			catch (stackFrameDoesNotExist e)
			{
				thrown = true;
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
		
		if(!thrown)
			fail("stackFrameDoesNotExist not thrown when pushing values onto the stack without a stack frame");
	}
	
	//get val stackframedoesnotexist
	@Test
	public void getValStackFrameDoesNotExistTest()
	{
		boolean thrown = false;
		
		try
		{
			Memory m = new Memory(12,1);
			
			try
			{
				m.getValueOnStack('A');
			}
			catch(stackFrameDoesNotExist e)
			{
				thrown = true;
			}
			catch (VariableNotFound e)
			{
				e.printStackTrace();
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
		
		if(!thrown)
			fail("stackFrameDoesNotExist not thrown when getting a value");
	}
	
	//set val stackframedoesnotexist
	@Test
	public void setValStackFrameDoesNotExistTest()
	{
		boolean thrown = false;
		
		try
		{
			Memory m = new Memory(12,1);
			
			try
			{	
				m.setValueOnStack('A',0);
			}
			catch(stackFrameDoesNotExist e)
			{
				thrown = true;
			}
			catch (VariableNotFound e)
			{
				e.printStackTrace();
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
		
		if(!thrown)
			fail("stackFrameDoesNotExist not thrown when getting a value");
	}
	
	//get val VariableNotFound
	@Test
	public void getValVariableNotFound()
	{
		boolean thrown = false;
		
		try
		{
			Memory m = new Memory(12,1);
			
			try
			{
				m.pushStackFrame();
				m.getValueOnStack('A');
			}
			catch(stackFrameDoesNotExist | StackOverFlow e)
			{
				e.printStackTrace();
			}
			catch (VariableNotFound e)
			{
				thrown = true;
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
		
		if(!thrown)
			fail("VariableNotFound not thrown when getting a value");
	}
		
	//set val VariableNotFound
	@Test
	public void setValVariableNotFound()
	{
		boolean thrown = false;
		
		try
		{
			Memory m = new Memory(12,1);
			
			try
			{
				m.pushStackFrame();
				m.setValueOnStack('A',2);
			}
			catch(stackFrameDoesNotExist | StackOverFlow e)
			{
				e.printStackTrace();
			}
			catch (VariableNotFound e)
			{
				thrown = true;
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
		
		if(!thrown)
			fail("VariableNotFound not thrown when getting a value");
	}
	
	//heap---------------------------------------------------------------
	//malloc 4 memory
	@Test
	public void mallocFour()
	{
		try
		{
			Memory m = new Memory(1,20);
			
			try
			{
				int a = m.malloc(5);
				int b = m.malloc(5);
				int c = m.malloc(5);
				int d = m.malloc(5);
				
				if(!m.usedMemory.toString().equals("[(0:1), (1:5), (6:5), (11:5), (16:5), (21:0)]"))
					fail("malloced memory not stored properly in usedMemory");
			}
			catch (OutOfMemory e)
			{
				e.printStackTrace();
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
	}
	
	//set 2 memory
	@Test
	public void setTwo()
	{
		try
		{
			Memory m = new Memory(1,20);
			
			try
			{
				int a = m.malloc(5);
				int b = m.malloc(5);
				int c = m.malloc(5);
				int d = m.malloc(5);
				
				try
				{
					int[] setMemA = {1,2,3,4,5};
					int[] setMemB = {5,4,3,2,1};
					m.set(setMemA, a, 5);
					m.set(setMemB, c, 5);
				}
				catch (MemoryOverRun e)
				{
					e.printStackTrace();
				}
				
				int[] wantedMemory = {0,1,2,3,4,5,0,0,0,0,0,5,4,3,2,1,0,0,0,0,0};
				
				if(!Arrays.equals(m.memory, wantedMemory))
					fail("memory not properly set");
			}
			catch (OutOfMemory e)
			{
				e.printStackTrace();
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
	}
	
	//get memory
	@Test
	public void getMemoryTest()
	{
		try
		{
			Memory m = new Memory(1,20);
			
			try
			{
				int a = m.malloc(5);
				int b = m.malloc(5);
				int c = m.malloc(5);
				
				int[] setMemA = {1,2,3,4,5};
				int[] setMemB = {5,4,3,2,1};

				m.set(setMemA, a, 5);
				m.set(setMemB, c, 5);
								
				if(!Arrays.equals(m.get(a, 5), setMemA))
					fail("memory not properly get");
			}
			catch (OutOfMemory | MemoryOverRun e)
			{
				e.printStackTrace();
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
	}
	
	//free 2 momeory
	@Test
	public void freeMemoryTwo()
	{
		try
		{
			Memory m = new Memory(1,20);
			
			try
			{
				int a = m.malloc(5);
				int b = m.malloc(5);
				int c = m.malloc(5);
				
				int[] setMemA = {1,2,3,4,5};
				int[] setMemB = {5,4,3,2,1};

				m.set(setMemA, a, 5);
				m.set(setMemB, c, 5);
								
				m.free(a);
				m.free(c);
				
				if(!m.usedMemory.toString().equals("[(0:1), (6:5), (21:0)]"))
					fail("memory not properly freed");
			}
			catch (OutOfMemory | MemoryOverRun | MemoryNotFound e)
			{
				e.printStackTrace();
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
	}
	
	//add 3 memory
	@Test
	public void freeMemoryAddThree()
	{
		try
		{
			Memory m = new Memory(1,20);
			
			try
			{
				int a = m.malloc(5);
				int b = m.malloc(5);
				int c = m.malloc(5);
				
				int[] setMemA = {1,2,3,4,5};
				int[] setMemB = {5,4,3,2,1};

				m.set(setMemA, a, 5);
				m.set(setMemB, c, 5);
								
				m.free(a);
				m.free(c);
				
				int d = m.malloc(2);
				int e = m.malloc(3);
				int f = m.malloc(5);
				
				if(!m.usedMemory.toString().equals("[(0:1), (1:2), (3:3), (6:5), (11:5), (21:0)]"))
					fail("memory not properly added");
			}
			catch (OutOfMemory | MemoryOverRun | MemoryNotFound e)
			{
				e.printStackTrace();
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
	}
	
	//free rest of memory
	@Test
	public void freeAllMemory()
	{
		try
		{
			Memory m = new Memory(1,20);
			
			try
			{
				int a = m.malloc(5);
				int b = m.malloc(5);
				int c = m.malloc(5);
				
				int[] setMemA = {1,2,3,4,5};
				int[] setMemB = {5,4,3,2,1};

				m.set(setMemA, a, 5);
				m.set(setMemB, c, 5);
								
				m.free(a);
				m.free(c);
				
				int d = m.malloc(2);
				int e = m.malloc(3);
				int f = m.malloc(5);
				
				m.free(b);
				m.free(d);
				m.free(e);
				m.free(f);

				if(!m.usedMemory.toString().equals("[(0:1), (21:0)]"))
					fail("memory not properly freed");
			}
			catch (OutOfMemory | MemoryOverRun | MemoryNotFound e)
			{
				e.printStackTrace();
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
	}
	
	//outOfmemory 
	@Test
	public void OutOfMemoryTest()
	{
		boolean thrown = false;
		
		try
		{
			Memory m = new Memory(1,1);
			
			try
			{
				m.malloc(2);
			}
			catch (OutOfMemory e)
			{
				thrown = true;
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
		
		if(!thrown)
			fail("outOfMemory not thrown when out of heap space");
	}
	
	//memorynotfound
	@Test
	public void MemoryNotFoundTest()
	{
		boolean thrown = false;
		
		try
		{
			Memory m = new Memory(1,20);
			
			try
			{
				m.free(10);
			}
			catch (MemoryNotFound e)
			{
				thrown = true;
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
		
		if(!thrown)
			fail("MemoryNotFound not thrown when freeing memory that does not exist");
	}
	
	//memoryoverrun get
	@Test
	public void GetMemoryOverRun()
	{
		boolean thrown = false;
		
		try
		{
			Memory m = new Memory(1,20);
			
			try
			{
				m.get(0,21);
			}
			catch (MemoryOverRun e)
			{
				thrown = true;
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
		
		if(!thrown)
			fail("MemoryOverRun not thrown when attempting to get more memory than exists in the heap");
	}
	
	//memory overrn set
	@Test
	public void SetMemoryOverRun()
	{
		boolean thrown = false;
		
		try
		{
			Memory m = new Memory(1,20);
			
			try
			{
				int[] data = {1,2,3};
				m.set(data,0,21);
			}
			catch (MemoryOverRun e)
			{
				thrown = true;
			}
		}
		catch (InvalidMemoryItialization e)
		{
			e.printStackTrace();
		}
		
		if(!thrown)
			fail("MemoryOverRun not thrown when attempting to set more memory than exists in the heap");
	}
}