Repository Layer
---

So at the moment we have Domain Model and a console interface. It's time to store data in database. There are special
interfaces and classes to work with DB. And we could use them in all parts of the project to run SQL queries. But
this is usually a bad idea. We need to isolate DB-related logic into separate layer called Repository (or DAO - 
data access object). Why?

* It's just easier to structure the project - you always know where to find SQL
* This eliminates code duplication as oftentimes we need the same query to be executed for different parts of app
* There are different types of code - could be domain logic, or database logic, or invocation of other services - 
it's hard to understand code if it's all mixed together

# Add Repository layer

At the moment we're not ready to work with real databases, so for now let's store objects in memory. Then we'll
replace these classes with another implementation so that the rest of the app won't have to be changed a lot. So
for now:  

* Learn how these data structures work and when they can be useful:
   * ArrayList 
   * Hash Map (aka Hash Table) with external chaining is used in `java.util.HashMap`
   * Look into other Hash-based maps: LinkedHashMap, IdentityHashMap. Note, that the latter implements a 
   completely different structure - a Hash Map with linear probing.
* To each class of our Domain Model add a field: `String id`. This is going to be our 
[surrogate key](./articles/surrogate-keys.md).
* Create classes: `UserRepository`, `CardRepository`
* Add methods to retrieve and save objects from/to "database". These methods should store data in Java Collections:
HashMap would be the most useful in this case because the identifier of the object could be stored as a key in the
map.

# Use Repository layer in console interface

* When user signs in, create such user and save it via UserRepository. If user with such email already exists - 

# Tests (TBD)

While developers write code to solve some real-world problems, we also need to check that our code works. When we
keep adding new code into our project there is a risk that we break something that previously worked. In real life
this happens _all the time_. Thus we have to check whether it still works - it's called testing. There are 2 choices: 

* You can keep re-checking your code manually. This is super tedious and time-consuming, which means you'll
start skipping some checks after a while. This will result in a poorly working software.
* Or you can write yet another code (automated tests) that checks the main code (production code)