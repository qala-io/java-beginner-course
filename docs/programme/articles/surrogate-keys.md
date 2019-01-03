Surrogate Keys
---

When storing a record in DB we want to be able to fetch it back. But what attribute do we search by? Let's say
we're talking about Users:

* Can we search by Last Name? No, it's not unique - there could be multiple users with the same Last Name.
* What about email? It is actually unique, so it could be a good identifier. Except in most apps user can change his
email. Then all the parts of the app (database tables) that referenced the user by his email would have to be updated.

These are 2 primary reasons why most of the time we don't use one of the business fields as an identifier. Almost
always business fields are either not unique, or can be changed. That's why we add a new field specifically to 
identify records - usually we call it exactly that: id.

In DB terminology if we use one of the business fields as a key (unique identifier) - that's called a Natural Key.
If we create a special field (column) specifically for the purpose of identification - that's called a Surrogate Key.