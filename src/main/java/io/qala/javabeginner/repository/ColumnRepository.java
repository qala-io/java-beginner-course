package io.qala.javabeginner.repository;

import io.qala.javabeginner.domain.Column;

import java.util.List;

public interface ColumnRepository {
    void save(Column column);

    Column findByName(String name);

    List<Column> findAllOrderedByPosition();
}
