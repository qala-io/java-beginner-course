Java Tools
---

# Step1: IDE

You can't keep working in command line forever. Average apps have tons of code (>50K lines and hundreds to
thousands of classes), it would be a nightmare to write it all in a notepad and compile it all in command line.
That's why you need to use IDE - it will greatly simplify your life. 

* Install an IDE like IntelliJ IDEA (Community Edition is free) or Eclipse
* Open your project, set up SDK (Java installation)
* Try running your console app from IDE
* Try debugging your app - putting a breakpoint and then running the app in Debug mode; see variables' state in 
the running app; evaluate your own expressions. Debugging is going to be one of the most important tools in 
your career, so play with it.  

# Step2: Logging

While `System.out.println` worked fine for your simple case, you'll have to stop using it:

* First of all it's just not convenient to concatenate strings and values all the time
* Most of the time we want to write logs into files and not into console (though we could do both)
* We'd like to categorize different lines of log - some of them are written because there are errors, others - 
just to explain what's happening with the system at any moment of time. We'd like to draw attention to error logs.

That's why instead - we use special libraries for logging. The most popular choice nowadays is SLF4J+Logback.
The former provides interfaces, the latter provides the implementation. So:

* Download 3 libs (jar files): slf4j-api, logback-classic, logback-core. 
You can find them on the official sites, but there is also a central repository of most of the libraries that Java
devs need. You can find all different versions of different libs there: https://mvnrepository.com/
* Add these jar files in your project dependencies in IDE
* After they are added, you can try using logging instead of `System.out.println` in your Domain classes like this:

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Card {
    private static final Logger LOG = LoggerFactory.getLogger(Card.class);
    
    //...
    
    public void assignTo(User assignee) {
        this.assignee = assignee;
        LOG.info("Card [{}] was assigned to [{}]", title, assignee.getEmail());
    }
}
```
When you run your app next time, you should see something like this in console:

```
02:35:12.535 [main] INFO io.qala.javabeginner.domain.Card - User [winston.smith@oceania.io] created a card [Commit a thoughtcrime] in column [TODO]
```

* The format in which this string shows up in console can be configured - you should find some examples of 
`logback.xml` file on the Internet. 
* You may also configure Logback to put these lines into some file
* Try unpacking jar files that you downloaded - find the classes that you imported in your code
* Note that you can Ctrl+Click on the class in IDE and it will decompile the class from JAR for you
* Answer this question: why was `LOG` field marked as `static`? Can we leave out `static`? Does it make sense 
to do so? 

# Step3: Build Tools

While IDE let's you easily navigate through your project, that's not enough:

* It's hard for a newcomer to join the project because he would have to set up his IDE, download all of the
dependencies (there could be hundreds of them). That's tedious and error-prone.
* You don't want to manually package the binary for further deployment. Team members' local configuration may 
differ from each other, but you want to guarantee that the packaged JAR file always works. That's why we set up so
called Build Servers (Jenkins, TeamCity, etc) - these are tools that run on remote servers and can package your
app. Those often don't have UI set up in their OS and they can't use IDE.
* We also would like to have some life-cycle in projects - it would compile things in the right order, run checks,
package a jar file, upload it somewhere.

That's why we have special Build Tools. Most notorious examples are: Ant, Maven, Gradle. The most used one is Maven.
So:

* Read some introductory articles about Maven
* Download and install Maven locally. It's an archive that needs to be unpacked and you'll need to set up M2_HOME
env variable as well as put `M2_HOME/bin` into your `path` variable.
* Create `pom.xml` file in your project, where you specify:
   * groupId/artifactId/version of your project
   * Dependencies (slf4j-api, logback-classic, logback-core) 
   * Configuration of maven-jar-plugin so that it produces an executable jar 
* Try building your project with `mvn package` - if it's successful, you should get a jar file in your `target`
directory that is executable with `java -jar`
* Delete all your IDE files and try to re-open the project with IDE. Your IDE should recognize that it's a Maven
project and add libraries to its Dependencies automatically.

At some point you'll need to 
[learn Maven deeper](https://github.com/qala-io/java-course/blob/master/docs/programme/maven.md), but for now 
your main goal is to learn Java. Maven will simplify some of our further steps.

# Step4: VCS (TBD)


# Tests (TBD)

While developers write code to solve some real-world problems, we also need to check that our code works. When we
keep adding new code into our project there is a risk that we break something that previously worked. In real life
this happens _all the time_. Thus we have to check whether it still works - it's called testing. There are 2 choices: 

* You can keep re-checking your code manually. This is super tedious and time-consuming, which means you'll
start skipping some checks after a while. This will result in a poorly working software.
* Or you can write yet another code (automated tests) that checks the main code (production code)