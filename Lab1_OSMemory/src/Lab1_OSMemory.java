import java.util.Arrays;

public class Lab1_OSMemory
{
	public static void main(String[] args)
	{
		try
		{
			Memory m = new Memory(8, 12);
			System.out.println("create memory");
			System.out.println("memory: "+Arrays.toString(m.memory));
			System.out.println("curStackFrame: " + m.curStackFrame);
			System.out.println("curStackEnd: " + m.curStackEnd);
			System.out.println("usedMemory: " + m.getUsedMemory());
			
			try
			{
				m.pushStackFrame();
			}
			catch (StackOverFlow e)
			{
				e.printStackTrace();
			}
			System.out.println("\npush stackframe");
			System.out.println("memory: "+Arrays.toString(m.memory));
			System.out.println("curStackFrame: " + m.curStackFrame);
			System.out.println("curStackEnd: " + m.curStackEnd);
			System.out.println("usedMemory: " + m.getUsedMemory());
			
			try {
				m.pushValueOnStack('A', 2);
			} catch (StackOverFlow e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DuplicateVariableName e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (stackFrameDoesNotExist e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\npush 'A' with 2 on stack frame");
			System.out.println("memory: "+Arrays.toString(m.memory));
			System.out.println("curStackFrame: " + m.curStackFrame);
			System.out.println("curStackEnd: " + m.curStackEnd);
			System.out.println("usedMemory: " + m.getUsedMemory());
			
			try {
				m.setReturnInt(5);
			} catch (stackFrameDoesNotExist e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\nset retun value to 5");
			System.out.println("memory: "+Arrays.toString(m.memory));
			System.out.println("curStackFrame: " + m.curStackFrame);
			System.out.println("curStackEnd: " + m.curStackEnd);
			System.out.println("usedMemory: " + m.getUsedMemory());
			
			try {
				m.pushValueOnStack('B', 3);
			} catch (StackOverFlow e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DuplicateVariableName e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (stackFrameDoesNotExist e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\npush 'B' with 3 onto stack");
			System.out.println("memory: "+Arrays.toString(m.memory));
			System.out.println("curStackFrame: " + m.curStackFrame);
			System.out.println("curStackEnd: " + m.curStackEnd);
			System.out.println("usedMemory: " + m.getUsedMemory());
			
			try {
				m.pushStackFrame();
			} catch (StackOverFlow e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\npush new stackframe");
			System.out.println("memory: "+Arrays.toString(m.memory));
			System.out.println("curStackFrame: " + m.curStackFrame);
			System.out.println("curStackEnd: " + m.curStackEnd);
			System.out.println("usedMemory: " + m.getUsedMemory());
			
			try {
				m.popStackFrame();
			} catch (StackUnderFlow e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\npop stack frame");
			System.out.println("memory: "+Arrays.toString(m.memory));
			System.out.println("curStackFrame: " + m.curStackFrame);
			System.out.println("curStackEnd: " + m.curStackEnd);
			System.out.println("usedMemory: " + m.getUsedMemory());
			
			try {
				m.popStackFrame();
			} catch (StackUnderFlow e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\npop stack frame");
			System.out.println("memory: "+Arrays.toString(m.memory));
			System.out.println("curStackFrame: " + m.curStackFrame);
			System.out.println("curStackEnd: " + m.curStackEnd);
			System.out.println("usedMemory: " + m.getUsedMemory());
			
			try {
				m.pushStackFrame();
			} catch (StackOverFlow e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\npush stack frame");
			System.out.println("memory: "+Arrays.toString(m.memory));
			System.out.println("curStackFrame: " + m.curStackFrame);
			System.out.println("curStackEnd: " + m.curStackEnd);
			System.out.println("usedMemory: " + m.getUsedMemory());
			
			try {
				m.pushValueOnStack('a', -2);
			} catch (StackOverFlow e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DuplicateVariableName e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (stackFrameDoesNotExist e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\npush 'a' with -2 onto stack");
			System.out.println("memory: "+Arrays.toString(m.memory));
			System.out.println("curStackFrame: " + m.curStackFrame);
			System.out.println("curStackEnd: " + m.curStackEnd);
			System.out.println("usedMemory: " + m.getUsedMemory());
			
			try {
				m.pushStackFrame();
			} catch (StackOverFlow e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\npush stack frame");
			System.out.println("memory: "+Arrays.toString(m.memory));
			System.out.println("curStackFrame: " + m.curStackFrame);
			System.out.println("curStackEnd: " + m.curStackEnd);
			System.out.println("usedMemory: " + m.getUsedMemory());
			
			try {
				m.pushStackFrame();
			} catch (StackOverFlow e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\npush stack frame");
			System.out.println("memory: "+Arrays.toString(m.memory));
			System.out.println("curStackFrame: " + m.curStackFrame);
			System.out.println("curStackEnd: " + m.curStackEnd);
			System.out.println("usedMemory: " + m.getUsedMemory());
			
			int mem1 = -1;
			
			try {
				mem1 = m.malloc(4);
			} catch (OutOfMemory e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\nmalloc size 4");
			System.out.println("memory: "+Arrays.toString(m.memory));
			System.out.println("curStackFrame: " + m.curStackFrame);
			System.out.println("curStackEnd: " + m.curStackEnd);
			System.out.println("usedMemory: " + m.getUsedMemory());
			
			try {
				m.set(new int[] {1,2,3,4}, mem1, 4);
			} catch (MemoryOverRun e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("\nset data in malloced");
			System.out.println("memory: "+Arrays.toString(m.memory));
			System.out.println("curStackFrame: " + m.curStackFrame);
			System.out.println("curStackEnd: " + m.curStackEnd);
			System.out.println("usedMemory: " + m.getUsedMemory());
			
			try {
				m.free(mem1);
			} catch (MemoryNotFound e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\nsfrees mem1");
			System.out.println("memory: "+Arrays.toString(m.memory));
			System.out.println("curStackFrame: " + m.curStackFrame);
			System.out.println("curStackEnd: " + m.curStackEnd);
			System.out.println("usedMemory: " + m.getUsedMemory());
			
			try {
				m.malloc(2);
			} catch (OutOfMemory e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\nmalloc size 2");
			System.out.println("memory: "+Arrays.toString(m.memory));
			System.out.println("curStackFrame: " + m.curStackFrame);
			System.out.println("curStackEnd: " + m.curStackEnd);
			System.out.println("usedMemory: " + m.getUsedMemory());
			
			try {
				mem1 = m.malloc(6);
			} catch (OutOfMemory e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\nmalloc size 6");
			System.out.println("memory: "+Arrays.toString(m.memory));
			System.out.println("curStackFrame: " + m.curStackFrame);
			System.out.println("curStackEnd: " + m.curStackEnd);
			System.out.println("usedMemory: " + m.getUsedMemory());
			
			try {
				m.set(new int[] {6,5,4,3,2,1}, mem1, 6);
			} catch (MemoryOverRun e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("\nset data in malloced");
			System.out.println("memory: "+Arrays.toString(m.memory));
			System.out.println("curStackFrame: " + m.curStackFrame);
			System.out.println("curStackEnd: " + m.curStackEnd);
			System.out.println("usedMemory: " + m.getUsedMemory());
		}
		catch (InvalidMemoryItialization e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
}