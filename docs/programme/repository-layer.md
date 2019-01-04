Repository Layer
---

So at the moment we have Domain Model and a console interface. It's time to store data in database. In Java there
are special interfaces and classes to work with DB - they are part of so called JDBC. And we could use 
them in all parts of the project to run SQL queries. But this is usually a bad idea. We need to isolate 
DB-related logic into separate layer called Repository (or DAO - data access object). Why?

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
* Create classes: `UserRepository`, `CardRepository`, `ColumnRepository`
* Add methods to retrieve and save objects from/to "database". These methods should store data in Java Collections:
HashMap would be the most useful in this case because the identifier of the object could be stored as a key in the
map.
   * When saving an object to our "database" Repository classes need to assign ID. The easiest option is to
   assign a random one: `UUID.randomUUID().toString()` 
   * If you want to retrieve objects by some other unique field (e.g. Columns could be retrieved by name) you can 
create additional maps. This is an equivalent of creating indices in DB. 
* Example of a repository class:

```java
package io.qala.javabeginner.repository;

import io.qala.javabeginner.domain.Card;
import io.qala.javabeginner.domain.Column;

import java.util.*;

public class CardRepository {
    private final Map<String, Card> cardById = new LinkedHashMap<>();
    private final Map<String, List<Card>> cardByAssignee = new HashMap<>();
    private final Map<String, List<Card>> cardByColumn = new HashMap<>();

    public void save(Card newCard) {
        if(newCard.getId() != null)
            throw new IllegalArgumentException("The card already stored in DB: " + newCard.getId());
        newCard.setId(UUID.randomUUID().toString());
        cardById.put(newCard.getId(), newCard);
        List<Card> cardsByColumn = cardByColumn.computeIfAbsent(newCard.getColumn().getId(), (c) -> new ArrayList<>());
        cardsByColumn.add(newCard);
        List<Card> assigneeCards = cardByAssignee.computeIfAbsent(newCard.getAssignee().getId(), (c) -> new ArrayList<>());
        assigneeCards.add(newCard);
    }
    public List<Card> findByColumn(Column column) {
        return cardByColumn.get(column.getId());
    }

}

``` 

# Improve console interface

Now let's create a feeling of a real app in the console. 

* Make it possible to choose a command - list cards, create a card. User can keep creating cards and listing
them. 
* Every time we move to a column - if such column exists in repository, we re-use that column
* Every time we assign a task to a user - if such user exists, we re-use that user

Example of communication:

```
First, log in!
Email: manager@email.com
First name: Shawn
Last name: Spencer
Hello Shawn Spencer, now you can create a task.
Choose 1 to create a card, 2 to show the list of cards, 0 to exit
1
Title: Task #1
Column: TODO
15:01:25.076 [main] INFO io.qala.javabeginner.domain.Card - User [manager@email.com] created a card [Task #1] in column [TODO]
You just created a card. Assign it to your friend.
Email: developer@email.com
First name: Gus
Last name: Burton
15:01:33.986 [main] INFO io.qala.javabeginner.domain.Card - Card [Task #1] was assigned to [developer@email.com]
Choose 1 to create a card, 2 to show the list of cards, 0 to exit
1
Title: Task #2
Column: TODO
15:01:43.592 [main] INFO io.qala.javabeginner.domain.Card - User [manager@email.com] created a card [Task #2] in column [TODO]
You just created a card. Assign it to your friend.
Email: manager@email.com
15:01:49.721 [main] INFO io.qala.javabeginner.domain.Card - Card [Task #2] was assigned to [manager@email.com]
Choose 1 to create a card, 2 to show the list of cards, 0 to exit
1
Title: Task #3
Column: Done
15:02:22.344 [main] INFO io.qala.javabeginner.domain.Card - User [manager@email.com] created a card [Task #3] in column [Done]
You just created a card. Assign it to your friend.
Email: developer@email.com
15:02:27.857 [main] INFO io.qala.javabeginner.domain.Card - Card [Task #4] was assigned to [developer@email.com]
Choose 1 to create a card, 2 to show the list of cards, 0 to exit
2
TODO:	Task #1(Gus Burton), Task #2(Shawn Spencer)
Done:	Task #3(Gus Burton)
Choose 1 to create a card, 2 to show the list of cards, 0 to exit
0
``` 

And here is a glimpse of how you things can be organized:

```java
    private void run() {
        System.out.println("First, log in!");
        User currentUser = enterUser(in);
        System.out.println("Hello " + currentUser.getFullName() + ", now you can create a task.");
        for(;;) {
            System.out.println("Choose 1 to create a card, 2 to show the list of cards, 0 to exit");
            String command = in.nextLine();
            if(command.equals("0"))
                return;
            else if(command.equals("1"))
                createCard(currentUser);
            else if(command.equals("2"))
                showCards();
            else
                System.out.println("The command was not recognized, try again");
        }
    }
    private void showCards() {
        for (Column column : columnRepository.findAllOrderedByPosition()) {
            String line = column.getName() + ":\t";
            for (Card card : cardRepository.findByColumn(column))
                line += card.getTitle() + "(" + card.getAssignee().getFullName() + "), ";
            line = line.substring(0, line.length() - 2);
            System.out.println(line);
        }
    }
```

After you implement this you may already start appreciating Repository Layer. Without it you'd scatter all those
HashMaps all over the code.