Getting acquainted with Java
---

# Step1: Creating domain model

Domain model describes the problem that you're solving, it contains your app's terminology, concepts and relations.
It's the core of your project.

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

# Step2: Compiling & Running

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
* Compile this code in command line and run it ([what are class files anyway?](./articles/class-files.md))

# Step3: Make it interactive

* Create a console API so that user could interact with our app. Something like this:

```
First, log in!
Email: winston.smith@oceania.io
First name: Winston
Last name: Smith
Hello Winston Smith, now you can create a task.

Title: Commit a thoughtcrime
Column: TODO
User [winston.smith@oceania.io] created a card [Commit a thoughtcrime] in column [TODO]
You just created a card. Assign it to your friend.

Email: julia@oceania.io
First name: Julia
Last name: Noname
Card [Commit a thoughtcrime] was assigned to [julia@oceania.io]
``` 

* Note, that users' data as well as card information was entered in console
* Read about how console input and output is done in Java programs under the hood ([link](./articles/io-sockets.md))

It's very rare for Java devs to create interactive console apps, but we can't go straight to web apps because it's
going to be too much. We'll first build lower layers and then eventually implement a web interface.

# Step4: Packaging your app

JAR files are convenient for distributing Java software or libraries. Libraries - are pieces of code that someone
else wrote and published, and now you can download it and use it as if it were a part of your project.

* Read about jar files, create an executable jar file - it should run the code above via `java -jar myapp.jar`
* Try to unpack JAR file with `jar` and Unzip utilities