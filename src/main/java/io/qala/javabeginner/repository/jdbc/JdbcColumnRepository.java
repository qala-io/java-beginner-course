package io.qala.javabeginner.repository.jdbc;

import io.qala.javabeginner.domain.Column;
import io.qala.javabeginner.repository.ColumnRepository;

import java.sql.Connection;
import java.util.List;

public class JdbcColumnRepository implements ColumnRepository {
    private final Connection connection;

    public JdbcColumnRepository(Connection connection) {
        this.connection = connection;
    }

    @Override public void save(Column column) {

    }

    @Override public Column findByName(String name) {
        return null;
    }

    @Override public List<Column> findAllOrderedByPosition() {
        return null;
    }
}
