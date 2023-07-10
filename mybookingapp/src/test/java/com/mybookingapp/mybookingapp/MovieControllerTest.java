package com.mybookingapp.mybookingapp;

import com.mybookingapp.mybookingapp.movie.Movie;
import com.mybookingapp.mybookingapp.movie.MovieController;
import com.mybookingapp.mybookingapp.movie.MovieService;
import com.mybookingapp.mybookingapp.ticket.Ticket;
import com.mybookingapp.mybookingapp.ticket.TicketRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class MovieControllerTest {

    @Test
    public void testGetMovieById() {
        // Erstellen Sie ein Mock für den MovieService
        MovieService movieService = Mockito.mock(MovieService.class);
        // Erstellen Sie den Controller mit dem Mock-Service
        MovieController controller = new MovieController(movieService);
        // Erstellen Sie ein Film-Objekt
        Movie movie = new Movie();
        movie.setId(1);
        // Stellen Sie ein, dass der Service dieses Film-Objekt zurückgibt, wenn getMovieById aufgerufen wird
        when(movieService.getMovieById(1L)).thenReturn(movie);
        // Rufen Sie die Methode auf dem Controller auf
        ResponseEntity<Movie> response = controller.getMovieById(1L);
        // Überprüfen Sie, dass der Statuscode OK ist und dass der Körper das erwartete Film-Objekt enthält
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movie, response.getBody());
    }

    @Test
    public void testBuyTicket() {
        MovieService movieService = Mockito.mock(MovieService.class);
        MovieController controller = new MovieController(movieService);

        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setTicketId("1");
        ticketRequest.setBuyerName("John Doe");
        ticketRequest.setBookingNumber("12345");

        Ticket ticket = new Ticket("1", "John Doe", "12345");
        when(movieService.buyTicket(ticketRequest)).thenReturn(ticket);

        ResponseEntity<Ticket> response = controller.buyTicket(ticketRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ticket, response.getBody());
    }

    @Test
    public void testUpdateTicket() {
        MovieService movieService = Mockito.mock(MovieService.class);
        MovieController controller = new MovieController(movieService);

        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setTicketId("1");
        ticketRequest.setBuyerName("John Doe");
        ticketRequest.setBookingNumber("12345");

        Ticket updatedTicket = new Ticket("1", "Jane Doe", "12345");
        when(movieService.updateTicket(1L, ticketRequest)).thenReturn(updatedTicket);

        ResponseEntity<Ticket> response = controller.updateTicket(1L, ticketRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedTicket, response.getBody());
    }
    // Ähnliche Tests könnten für die anderen Methoden geschrieben werden

    @Test
    public void testUpdateTicketWithDifferentParams() {
        MovieService movieService = Mockito.mock(MovieService.class);
        MovieController controller = new MovieController(movieService);

        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setTicketId("1");
        ticketRequest.setBuyerName("John Doe");
        ticketRequest.setBookingNumber("12345");

        Ticket updatedTicket = new Ticket("1", "Jane Doe", "12345");
        when(movieService.updateTicket(Long.valueOf("1"), ticketRequest)).thenReturn(updatedTicket);

        ResponseEntity<Ticket> response = controller.updateTicket(Long.valueOf("1"), ticketRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedTicket, response.getBody());
    }
}
