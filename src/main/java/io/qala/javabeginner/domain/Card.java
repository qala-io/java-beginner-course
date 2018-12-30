package io.qala.javabeginner.domain;

import java.time.ZonedDateTime;

public class Card {
    private String title, description;
    private User assignee, creator;
    private Column column;
    private ZonedDateTime creationTime;

    public Card(String title, User creator, Column column) {
        this.title = title;
        this.creator = creator;
        this.column = column;
        this.creationTime = ZonedDateTime.now();
        System.out.println("User [" + creator.getEmail() + "]" +
                " created a card [" + title + "]" +
                " in column [" + column.getName() + "]");
    }

    public void assignTo(User assignee) {
        this.assignee = assignee;
        System.out.println("Card [" + title + "] was assigned to [" + assignee.getEmail() + "]");
    }

    public void moveTo(Column column) {
        this.column = column;
        System.out.println("Card [" + title + "] was moved to [" + column.getName() + "]");
    }

    public void setDescription(String description) {
        this.description = description;
        System.out.println("Card [" + title + "] was given a description [" + description + "]");
    }

    @Override
    public String toString() {
        return "Card [" + title + ']';
    }
}
