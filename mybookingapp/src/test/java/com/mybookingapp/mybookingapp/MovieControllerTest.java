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
        // Create a mock for the MovieService
        MovieService movieService = Mockito.mock(MovieService.class);
        // Create the controller with the mock service
        MovieController controller = new MovieController(movieService);
        // Create a movie object
        Movie movie = new Movie();
        movie.setId(1);
        // Set up the service to return this movie object when getMovieById is called
        when(movieService.getMovieById(1L)).thenReturn(movie);
        // Call the method on the controller
        ResponseEntity<Movie> response = controller.getMovieById(1L);
        // Check that the status code is OK and that the body contains the expected movie object
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
