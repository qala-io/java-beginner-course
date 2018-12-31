What are class files?
---

Class-files are files that contain whatever we wrote in Java. But they contain this is in a much shorter form. 
Compilation transforms Java symbols into respective byte sequences. E.g. `=`, `+`, method invocation - this all can 
be represented as some byte sequence.

On the one hand it saves space, but it has more to it. Another reason to convert source code into binary class-format 
and not keep it in java-files is that it's possible to write other programming languages like Scala, Kotlin, 
Clojure which have different syntax but they still are compiled into class files.  

Later JVM will read those byte sequences and execute whatever we asked it to. Note, that JVM is a _C++ program_ - so
it just reads the class-files and depending on what we wrote there it has C++ equivalents of `if`, `for`, and other 
commands that it runs. You could write your own program (e.g. in Java) that reads files in your own format and if 
it sees a `=` sign, it notes that it needs to create a variable and store its name somewhere, and then 
associate that variable with the value to the right hand of `=` (you can store the name and the value in HashMap).

So when they say that C++ is a Compiled Language - they say that its source code is transformed directly into CPU 
instructions (those also have some byte sequences). When they say that e.g. Python is an Interpreted Language - 
they mean that there is another program (again - could be written in C++) that reads whatever is present in Python
files and transforms it into CPU commands. Java is both interpreted (JVM is written in C++) and compiled (JVM could 
transform some Java into C/C++ code and compile it into machine code when the _Java app is running_).  