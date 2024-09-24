package com.example.demo.model;

public enum TicketStatus {
    FREE("Free"),
    SOLD("Sold");

    public String value;
    private TicketStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
