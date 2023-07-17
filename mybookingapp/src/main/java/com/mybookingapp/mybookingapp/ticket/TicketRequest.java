package com.mybookingapp.mybookingapp.ticket;

public class TicketRequest {
    private String buyerName;
    private String bookingNumber;
    private int ticketCount;
    private String movieName;

    // Constructor, getters, and setters for the attributes

    public TicketRequest() {
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

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public void setMovieTitle(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieTitle() {
        return movieName;
    }
}
