package com.meike.abc.meike.Model.Support;


public class PostAttribute {
    public enum Type {
        String, Number, Boolean
    }

    private String name;
    private String value;
    private Type type;
    private String[] range;
    private char compare;

    public PostAttribute(String name, String value) {
        this.name = name;
        this.value = value;
        this.compare = '=';
    }

    public PostAttribute(String name, String value, char compare) {
        this.name = name;
        this.value = value;
        this.type = Type.Number;
        this.compare = compare;
    }

    public PostAttribute(String name, String[] range) {
        this.name = name;
        this.type = Type.String;
        this.range = range;
    }

    public PostAttribute(String name, Type type) {
        this.name = name;
        this.type = type;
        if (type == Type.Boolean) {
            range = new String[] {
                "", "是", "否"
            };
        }
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

    public String[] getRange() {
        return range;
    }

    public char getCompare() {
        return compare;
    }


}
