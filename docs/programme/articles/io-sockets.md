How Input/Output happens under the hood
---

When a program wants to write something let's say into a file, it first has to tell OS\* which file we want to work with.
The file gets "opened" and OS gives back a number called "file descriptor", let's say 13243. When we read or write
to that file we have to tell OS: "Here is a file descriptor 13243, and here is the data that we write: Hello". And
then OS writes the data. It doesn't need a file name anymore - it just needs this file descriptor (FD).

Note that writing to a console and reading user input from a console is pretty much the same as working with files. 
But instead of explicitly telling OS "open a console", it gets it opened when we start a process. So each program 
that you start has some FDs associated with it. Namely: 0 (Standard input), 1 (Standard output), 2 (Standard error). 
So each program can expect user input at FD=0 and print something in console at FD=1 and FD=2. Moreover if we 
were to close these file descriptors - our program would automatically finish.

Java has special objects created to work with standard I/O, they are accessible via `System.in`, `System.out` 
and `System.err`. To simplify reading from `System.in` you may want to wrap `System.in` with `java.util.Scanner`.  

\* How does Java talk to OS? Let me remind you that JVM is just a program written in C++. This program reads
instructions from class files and executes them. So if it's a C++, then it can also use C-libraries. Each OS has
a list of those as part of its source code. And they do whatever is written there - e.g. they write bytes into disk
and read them from disk. Thus JVM reads whatever we wrote in Java, translates this into C++ code which eventually
invokes OS libs.