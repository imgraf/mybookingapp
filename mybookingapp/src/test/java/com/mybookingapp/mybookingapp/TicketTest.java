package com.mybookingapp.mybookingapp;

import com.mybookingapp.mybookingapp.ticket.Ticket;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketTest {

    @Test
    public void testTicketConstructorAndGetters() {
        // Arrange
        String expectedTicketId = "1";
        String expectedBuyerName = "John";
        String expectedBookingNumber = "BN123";

        // Act
        Ticket ticket = new Ticket();
        ticket.setId(Long.parseLong(expectedTicketId)); // Konvertierung von String zu Long
        ticket.setBuyerName(expectedBuyerName);
        ticket.setBookingNumber(expectedBookingNumber);

        // Assert
        assertEquals(expectedTicketId, String.valueOf(ticket.getId())); // Konvertierung von Long zu String
        assertEquals(expectedBuyerName, ticket.getBuyerName());
        assertEquals(expectedBookingNumber, ticket.getBookingNumber());
    }
}
