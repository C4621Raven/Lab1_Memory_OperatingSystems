# Memory Manager Simulation

## Purpose of the Assignment

I completed this project for my Operating Systems course to simulate how memory works inside a computer. Instead of relying on the memory management a programming language provides automatically, I built my own simplified version of it from scratch to see how an operating system gives programs the memory they need to run.

I designed a `Memory` class that models a block of computer memory divided into two regions:

- **The stack**, which handles function calls. Each call gets its own stack frame holding its local variables and a return value, and that frame is removed when the function finishes.
- **The heap**, which handles memory requested while a program is running. Blocks of memory are allocated on request, read and written, and then freed so the space can be reused.

To make the simulation realistic, I tracked the location of every stack variable and every heap block, and handled the failures a real system has to deal with, such as running out of memory, overflowing the stack, and reading or writing past the end of a block. I also wrote tests for every method and a demo program that uses the class the way a real program would.
