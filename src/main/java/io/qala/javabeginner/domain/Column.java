package io.qala.javabeginner.domain;

public class Column {
    private String name;

    public Column(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
