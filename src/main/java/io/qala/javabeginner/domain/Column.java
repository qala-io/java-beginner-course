package io.qala.javabeginner.domain;

public class Column {
    private String id, name;
    private int positionOnBoard;

    public Column(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setPositionOnBoard(int position) {
        this.positionOnBoard = position;
    }
    public int getPositionOnBoard() {
        return positionOnBoard;
    }

    @Override
    public String toString() {
        return name;
    }
}
