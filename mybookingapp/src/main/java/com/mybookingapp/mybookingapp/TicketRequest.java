package com.mybookingapp.mybookingapp;

public class TicketRequest {
    private String ticketId;
    private String buyerName;
    private String bookingNumber;

    // Konstruktor, Getter und Setter für die Attribute

    public TicketRequest() {
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }
}