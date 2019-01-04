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
