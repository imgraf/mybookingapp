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
        Ticket ticket = new Ticket(expectedTicketId, expectedBuyerName, expectedBookingNumber);

        // Assert
        assertEquals(expectedTicketId, ticket.getTicketId());
        assertEquals(expectedBuyerName, ticket.getBuyerName());
        assertEquals(expectedBookingNumber, ticket.getBookingNumber());
    }
}
