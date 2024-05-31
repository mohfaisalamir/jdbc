package com.enigma.jdbc.entity;

public class Table {
    private Integer id;
    private String tableName;

    public Table(Integer id, String tableName) {
        this.id = id;
        this.tableName = tableName;
    }
    public Table() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "\n"+"=".repeat(30) +
                "\nid           : " + id +
                "\ntable name   : " + tableName;
    }
}
