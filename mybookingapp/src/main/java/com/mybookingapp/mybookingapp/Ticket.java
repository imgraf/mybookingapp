package com.mybookingapp.mybookingapp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String ticketId;
    private String buyerName;
    private String bookingNumber;

    public Ticket() {
    }

    public Ticket(String ticketId, String buyerName, String bookingNumber) {
        this.ticketId = ticketId;
        this.buyerName = buyerName;
        this.bookingNumber = bookingNumber;
    }

    public Ticket(TicketRequest ticketRequest) {
        this.ticketId = ticketRequest.getTicketId();
        this.buyerName = ticketRequest.getBuyerName();
        this.bookingNumber = ticketRequest.getBookingNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
