package io.qala.javabeginner.repository;

import io.qala.javabeginner.domain.Card;
import io.qala.javabeginner.domain.Column;
import io.qala.javabeginner.domain.User;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class CardRepositoryTest {
    @Test
    public void savingCardAssignsId() {
        CardRepository repo = new CardRepository();
        Card newCard = cardInColumn(column());

        repo.save(newCard);
        assertNotNull(newCard.getId());
    }
    @Test
    public void savingCardLetsUsFindItByColumn() {
        CardRepository repo = new CardRepository();
        Column column = column();
        Card newCard = cardInColumn(column);
        repo.save(newCard);

        List<Card> todoCards = repo.findByColumn(column);
        assertEquals(1, todoCards.size());
        assertSame(newCard, todoCards.get(0));
    }
    @Test
    public void ifNoCardsInColumn_thenEmptyCardListIsReturned() {
        CardRepository repo = new CardRepository();
        Card newCard = cardInColumn(column());
        repo.save(newCard);

        List<Card> todoCards = repo.findByColumn(column());
        assertEquals(0, todoCards.size());
    }

    private static Column column() {
        Column column = new Column("TODO");
        new ColumnRepository().save(column);
        return column;
    }

    private static Card cardInColumn(Column column) {
        return new Card("title", new User("creator"), column);
    }
}