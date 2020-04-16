package ru.he.models.enums.instruments;

public enum InstrumentType {
    VOCALS(0, "Vocals"),
    STRINGS(1, "Guitars"),
    PERCUSSION(2, "Percussion"),
    KEYBOARDS(3, "Keyboard");

    private int key;
    private String value;

    InstrumentType(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
