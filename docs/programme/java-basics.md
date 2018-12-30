Getting acquainted with Java
---

# Reading books

* Start reading a book for Java beginners (e.g. 2 volumes by [Horstmann](http://horstmann.com/corejava/index.html))
* Things that you need to know before starting next steps:
   * Primitives, strings
   * Loops and conditions: `if`, `for`
   * Classes, fields, static fields, methods, constructors, objects (instances)
   * Difference between comparing references (`==`) and data (`equals()`)
* You can read the rest of the book in parallel with this course
* Things that you can skip completely:
   * AWT, Swing or JavaFX (tools for desktop apps). We're going to build a web app, desktop development is 
   not very popular nowadays.
   * Stream API (don't confuse with IO streams) and closures aren't important unless you want to understand 
   someone else's code that utilizes them. 
* Get acquainted with official Java naming/formatting conventions, there will be examples in this course so you 
should be able to get the drift without giving it much of a thought. But understand that it's common in dev 
community to get angry if they see common conventions violated. So start getting used to the conventions from the 
beginning. 

# Creating domain model

Domain model describes the problem that you're solving, it contains your app's terminology, concepts and relations.

* Create classes and fields:
   * User (email, first name, last name)
   * Column (name)
   * Card (title, description, assignee (User), creator (User), column (Column), creationTime (ZonedDateTime))
* Put them in some package (e.g. `io.[your name].[name of the app].domain`)

Now, your User class should look something like this:

```java
package io.qala.javabeginner.domain;

public class User {
    private String email, firstName, lastName;

    public User(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override 
    public String toString() {
        return email;
    }
}
```

Couple of things to note:

* Constructor accepts only email - this is because it's a required field (I decided this just now :)). Other 
fields are optional. 
* All the fields are private. This is how it's done in 99% of cases. We don't want to expose inside information 
to the outside world. This is because our class may change internally while keeping its public methods.
E.g. we could create a class UserName and put both firstName and lastName into that class:

```java
public class User {
    private UserName name = new UserName();
    
    public void setLastName(String firstName) {
        this.name.setFirstName(firstName);
    }
    public void setLastName(String lastName) {
        this.name.setLastName(lastName);
    }
}
```

See how setLastName() and setFirstName() didn't change their signature? This means that we changed internal
state of our class without breaking any other classes. So in the future when creating fields keep this trick
in mind and don't expose class's internals.

# Compiling & Running

* Create a `main()` method to run such code:

```java
Column todo = new Column("TODO"),
       inProgress = new Column("In Progress"),
       done = new Column("Done");

User creator = new User("project_manager@email.com"),
     assignee = new User("developer@email.com");
     
Card c1 = new Card("Task #1", creator, todo);
c1.assignTo(assignee);
c1.moveTo(inProgress);

Card c2 = new Card("Task #2", creator, todo);
c2.assignTo(assignee);
c2.setDescription("Won't fix it, created by mistake");
c2.moveTo(done);
c2.assignTo(creator);
```

* Use `System.out.println` inside your classes to produce such output:

```
User [project_manager@email.com] created a card [Task #1] in column [TODO]
Card [Task #1] was assigned to [developer@email.com]
Card [Task #1] was moved to [In Progress]
User [project_manager@email.com] created a card [Task #2] in column [TODO]
Card [Task #1] was assigned to [developer@email.com]
Card [Task #1] was given a description [Won't fix it, created by mistake]
Card [Task #1] was moved to [Done]
Card [Task #1] was assigned to [project_manager@email.com]
```
* To make it easier to log objects' information you may want to override `toString()` method in some of your classes
* Compile this code in command line and run the code.


## Logging (TBD)

While `System.out.println` worked fine for your simple case, you'll have to stop using it. 