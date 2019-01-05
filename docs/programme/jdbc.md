JDBC Repository
---

Before starting this chapter you first need to read about JDBC in your book.

Ideally we wouldn't care about databases until Web part is usable - database is hard to change so we could first
find out what exactly we need by building Web UI. But in your introductory books you will have a chapter about 
JDBC while Web requires additional books. That's why we'll first tackle DB side.

# Step1: Create Repository interfaces

* Rename your current repositories to XxxInMemoryRepository e.g. CardInMemoryRepository
* Add interfaces XxxRepository e.g. CardRepository and pull all public methods from your InMemoryRepositories
to these new interfaces
* Each XxxInMemoryRepository needs to implement interface XxxRepository
* Everywhere where XxxInMemoryRepository is used as a type of a variable - change that to interfaces. E.g.

```java
private final CardInMemoryRepository cardRepository = new CardInMemoryRepository(); 
```
needs to change to
```java
private final CardRepository cardRepository = new CardInMemoryRepository(); 
``` 

Note, that IntelliJ allows doing all of this with one function: Refactor->Extract->Interface.

* You should also rename your test classes according to the new names of production classes.

Couple of things to note:

* This all is needed so that we could implement interfaces with new classes and easily replace the implementation
in all the places. With small efforts we could even make it possible to switch between InMemory and JDBC 
implementations.
* We could've created the interfaces right away when we were creating our first repository implementation. But
that would be premature because at that point we didn't know if we needed a different implementation (well I did
but I pretended as if I didn't). If you found a way to do this whole refactoring with IDE capabilities you can
see how simple it is to introduce interfaces at some point in the future without over-engineering at the beginning.

# Step2: Create DB scheme

For our purposes we'll be using H2 database. H2 is an in-memory database written in Java. We don't frequently use
such databases for real apps. Instead we use standalone databases like MySQL, PostgreSQL, Oracle. But H2 is very
useful because we can easily write automated tests with it (instead of deploying standalone DB locally and to 
everyone who needs to run tests), and also it's very easy to add to the project.    

* Add H2 JDBC driver to Maven dependencies
* Now something should create tables when the app is starting (because we use in-memory DB each time we restart 
the app everything is going to be cleared). For that:
   * Add Flyway tool as a dependency
   * Create Flyway SQL Migration that creates tables for each of your domain classes. Such files go to 
   `src/main/resources` folder.
   * Start `new Flyway().migrate()` migrations from one of your main classes

# Step3: Create JDBC Repositories


* Implement XxxJdbcRepository for each interface
   * Create tables for each of our domain object. Use Forkeign Keys (FK) to reference records from other tables. 
   Usually we use tools like Flyway in order to create these tables, but for now you could run these statements
   in one of the classes in your app. 
   * For now create Connection object every time you need to run SQL query

