package io.qala.javabeginner.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;

public class Card {
    private static final Logger LOG = LoggerFactory.getLogger(Card.class);
    private String title, description;
    private User assignee, creator;
    private Column column;
    private ZonedDateTime creationTime;

    public Card(String title, User creator, Column column) {
        this.title = title;
        this.creator = creator;
        this.column = column;
        this.creationTime = ZonedDateTime.now();
        LOG.info("User [{}] created a card [{}] in column [{}]", creator.getEmail(), title, column.getName());
    }

    public void assignTo(User assignee) {
        this.assignee = assignee;
        LOG.info("Card [{}] was assigned to [{}]", title, assignee.getEmail());
    }

    public void moveTo(Column column) {
        this.column = column;
        LOG.info("Card [{}] was moved to [{}]", title, column.getName());
    }

    public void setDescription(String description) {
        this.description = description;
        LOG.info("Card [{}] was given a description [{}]", title, description);
    }

    @Override
    public String toString() {
        return "Card [" + title + ']';
    }
}
