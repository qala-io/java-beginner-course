Automated Testing
---

You already have an interactive app that works. But:

* Does it work in all the cases? Will it work nicely if user enters nothing for his email or first name? In order
to check what would happen you should now run the app and manually check this case.
* Let's say it works correctly _now_, will it work tomorrow after you introduce new changes?

The process of manually checking the app is called Manual Testing. But developers often write automated checks - 
source code (a test) to check another piece of source code (production code). These are called Automated Tests
and they are crucial in modern software engineering.

# Step1: Preparation for testing

* Add a JUnit4 dependency to your Maven. This is a library that will help us write and run tests. There are 3 
popular choices: JUnit4, TestNG which are simpler and JUnit5 which is a bit more involved.
* The scope of this Maven dependency must be `test`
* Create `src/test/java` folder and there you can create a package with your test classes. We usually place
test classes into the same packages where the classes-under-test are located. It's just they are not in 
`src/main/java`.
* Learn what are asserts in JUnit and also read about annotations `@Test`, `@Before`, `@After`, 
`@BeforeClass`, `@AfterClass`
* Write some basic test that fails (e.g. `assertTrue(false)`). Try running `mvn test` and make sure that 
you see what you expect - that the test failed. Also try running this test from IDE. 
* Then make the test pass and also run with Maven and with IDE.

# Step2: Writing tests

* Write tests for all your repositories. E.g. here is a list of tests that make sense for `CardRepository`:
   * savingCardAssignsId
   * savingCardLetsUsFindItByColumn
   * ifNoCardsInColumn_thenEmptyCardListIsReturned
   
Example:

```java
public class CardRepositoryTest {
    @Test
    public void savingCardAssignsId() {
        CardRepository repo = new CardRepository();
        Card newCard = cardInColumn(column());
        
        repo.save(newCard);
        assertNotNull(newCard.getId());
    }
    
    private static Column column() {
        Column column = new Column("TODO");
        new ColumnRepository().save(column);
        return column;
    }
    private static Card cardInColumn(Column column) {
        return new Card("title", new User("creator@email.com"), column);
    }
}
```

Note that we created 2 private methods to aid with readability. It's important for tests to be easy to read - 
they should state intentions, expectations and the code should be business-oriented (as opposed to technical - 
with loops and conditions). Tests should also be simple - they need to check little pieces of functionality. 

You'll need to keep writing tests for each new piece of functionality that you add.

# Summary

Such testing is important because:

* It let's us check the functionality faster. Often we write software with defects - fixing them and then 
re-running the whole app to manually check the fixes can actually be slower.
* It helps checking if the old functionality still works after new changes are introduced. It's _very_ often
that we break parts of the app that worked fine before.
* It simplifies introducing newcomers to the team. It's easy for newcomers to introduce bugs because they
don't know the project well yet. But if tests are in place there's some safety net. 

If you write tests for new functionality (or test it manually) - it's called Acceptance Testing (don't confuse 
with similarly sounding User Acceptance Testing - UAT). If you check that old functionality works - it's called 
Regression Testing. 

When your app grows your Regression suite grows as well. Often the amount of tests is greater than the amount of
production code. So take this seriously. 

There is also a notion of TDD (Test Driven Development). It's when you write tests first (they fail of course)
and then you write your production code to let the tests pass. It's crucial that you don't write all tests
right away and then implement the whole functionality. At the beginning you implement impaired functionality -
something that works only in specific cases. And then you write more and more tests and implement more and more of
production code. 

TDD might make you more productive especially if the feature that you write is very big and complicated - 
TDD makes you concentrate on small pieces and not be overwhelmed. There is a book 
[Test Driven Development: By Example](https://www.amazon.com/Test-Driven-Development-Kent-Beck/dp/0321146530) 
by Kent Beck that explains how to do TDD right.

To TDD or not to TDD is your choice - the end result shouldn't depend on how you got there. 