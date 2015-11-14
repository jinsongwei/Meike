package com.meike.abc.meike.Model.Constants;

public enum TableType {
    Housing(TableName.Housing),
    Auto(TableName.Auto),
    Jobs(TableName.Jobs),
    Sale(TableName.Sale),
    Other(TableName.Other);

    private final String name;

    TableType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
