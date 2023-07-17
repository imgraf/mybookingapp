package com.mybookingapp.mybookingapp.ticket;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String buyer_name;

    private String booking_number;
    private String movie_title;
    private int ticket_number;

    public Ticket() {
    }

    public Ticket(TicketRequest ticketRequest) {
        this.buyer_name = ticketRequest.getBuyerName();
        this.booking_number = ticketRequest.getBookingNumber();
        this.movie_title = ticketRequest.getMovieTitle();
        this.ticket_number = ticketRequest.getTicketCount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getBuyerName() {
        return buyer_name;
    }

    public void setBuyerName(String buyerName) {
        this.buyer_name = buyerName;
    }

    public String getBookingNumber() {
        return booking_number;
    }

    public String getMovieName() {
        return movie_title;
    }

    public int getTicketNumber() {
        return ticket_number;
    }

    public void setBookingNumber(String bookingNumber) {
        this.booking_number = bookingNumber;
    }

}

