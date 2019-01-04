package io.qala.javabeginner.repository.memory;

import io.qala.javabeginner.domain.Card;
import io.qala.javabeginner.domain.Column;
import io.qala.javabeginner.repository.CardRepository;

import java.util.*;

import static java.util.Collections.emptyList;

public class CardInMemoryRepository implements CardRepository {
    private final Map<String, Card> cardById = new LinkedHashMap<>();
    private final Map<String, List<Card>> cardByColumn = new HashMap<>();

    @Override
    public void save(Card newCard) {
        if(newCard.getId() != null)
            throw new IllegalArgumentException("The card already stored in DB: " + newCard.getId());
        newCard.setId(UUID.randomUUID().toString());
        cardById.put(newCard.getId(), newCard);
        List<Card> cardsByColumn = cardByColumn.computeIfAbsent(newCard.getColumn().getId(), (c) -> new ArrayList<>());
        cardsByColumn.add(newCard);
    }
    @Override
    public List<Card> findByColumn(Column column) {
        List<Card> cards = cardByColumn.get(column.getId());
        if(cards == null)
            cards = emptyList();
        return cards;
    }

}
