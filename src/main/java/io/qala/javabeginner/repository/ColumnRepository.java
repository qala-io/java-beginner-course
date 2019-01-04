package io.qala.javabeginner.repository;

import io.qala.javabeginner.domain.Column;

import java.util.*;

public class ColumnRepository {
    private final Map<String, Column> columnsById = new HashMap<>();
    private final Map<String, Column> columnsByName = new HashMap<>();

    public void save(Column column) {
        if(column.getId() != null)
            throw new IllegalArgumentException("The column already stored in DB: " + column.getId());
        column.setId(UUID.randomUUID().toString());
        columnsById.put(column.getId(), column);
        columnsByName.put(column.getName(), column);
    }
    public Column findByName(String name) {
        return columnsByName.get(name);
    }
    public List<Column> findAllOrderedByPosition() {
        return new ArrayList<>(orderedByPosition(columnsById.values()));
    }

    private List<Column> orderedByPosition(Collection<Column> unordered) {
        List<Column> ordered = new ArrayList<>(unordered);
        ordered.sort(Comparator.comparingInt(Column::getPositionOnBoard));
        return ordered;
    }
}
