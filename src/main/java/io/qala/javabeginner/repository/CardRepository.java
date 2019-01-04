package io.qala.javabeginner.repository;

import io.qala.javabeginner.domain.Card;
import io.qala.javabeginner.domain.Column;

import java.util.List;

public interface CardRepository {
    void save(Card newCard);

    List<Card> findByColumn(Column column);
}
