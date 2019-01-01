Tools
---

* Read about jar files (it's a way of packaging your code into an archive that's easy to distribute), create an
executable jar file - it should run the code above via `java -jar myapp.jar`

# Step1: IDE

You can't keep working command line forever. Average real life apps have tons of code (>50K lines and hundreds to
thousands of classes), it would be a nightmare to write it all in a notepad and compile it all in command line.
That's why you need to use IDE (e.g. IngelliJ IDEA has free Community Version). 

While IDE let's you easily navigate through your project, you can't just compile and package your apps in IDE:

* Any project will need to hook-up libraries
* First of all every programmer in your team (average team is ~5 developers) would have to manually configure 
his IDE to work with the project. 

# Step2: Build Tools

# Step3: VCS

# Step4: Logging

While `System.out.println` worked fine for your simple case, you'll have to stop using it:

* First of all it's just not convenient to concatenate strings and values all the time
* Most of the time we want to write log into files and not into console (though we could do both)
* We'd like to categorize different lines of log - some of them are written because there are errors, others - 
just to explain what's happening with the system at any moment of time. We'd like to draw attention to error logs.

# Tests

While developers write code to solve some real-world problems, we also need to check that our code works. When we
keep adding new code into our project there is a risk that we break something that previously worked. In real life
this happens _all the time_. Thus we have to check whether it still works - it's called testing. There are 2 choices: 

* You can keep re-checking your code manually. This is super tedious and time-consuming, which means you'll
start skipping some checks after a while. This will result in a poorly working software.
* Or you can write yet another code (automated tests) that checks the main code (production code)