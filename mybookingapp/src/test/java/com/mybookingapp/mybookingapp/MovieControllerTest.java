package com.mybookingapp.mybookingapp;

import com.mybookingapp.mybookingapp.movie.Movie;
import com.mybookingapp.mybookingapp.movie.MovieController;
import com.mybookingapp.mybookingapp.movie.MovieService;
import com.mybookingapp.mybookingapp.ticket.Ticket;
import com.mybookingapp.mybookingapp.ticket.TicketRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class MovieControllerTest {

    private MovieController movieController;

    @Mock
    private MovieService movieService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        movieController = new MovieController(movieService);
    }

    @Test
    void testGetMovieById() {
        // Arrange
        Long movieId = 1L;
        Movie movie = new Movie("Test Movie", "Genre", 120, "Description");
        movie.setId(movie.getId());

        when(movieService.getMovieById(movieId)).thenReturn(movie);

        // Act
        ResponseEntity<Movie> response = movieController.getMovieById(movieId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movie, response.getBody());
        verify(movieService, times(1)).getMovieById(movieId);
    }

    @Test
    void testBuyTicket() {
        // Arrange
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setBuyerName("John Doe");
        Ticket ticket = new Ticket(ticketRequest);

        when(movieService.buyTicket(eq(ticketRequest))).thenReturn(ticket);

        // Act
        ResponseEntity<Ticket> response = movieController.buyTicket(ticketRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ticket, response.getBody());
        verify(movieService, times(1)).buyTicket(eq(ticketRequest));
    }

    @Test
    void testCancelTicket() {
        // Arrange
        Long ticketId = 1L;

        // Act
        ResponseEntity<String> response = movieController.cancelTicket(ticketId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Ticket cancelled successfully.", response.getBody());
        verify(movieService, times(1)).cancelTicket(ticketId);
    }

    @Test
    void testUpdateTicket() {
        // Arrange
        Long ticketId = 1L;
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setBuyerName("John Doe");
        Ticket updatedTicket = new Ticket(ticketRequest);

        when(movieService.updateTicket(eq(ticketId), eq(ticketRequest))).thenReturn(updatedTicket);

        // Act
        ResponseEntity<Ticket> response = movieController.updateTicket(ticketId, ticketRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedTicket, response.getBody());
        verify(movieService, times(1)).updateTicket(eq(ticketId), eq(ticketRequest));
    }

    @Test
    void testGetAllMovies() {
        // Arrange
        List<Movie> movies = Arrays.asList(
                new Movie("Test Movie 1", "Genre 1", 120, "Description 1"),
                new Movie("Test Movie 2", "Genre 2", 90, "Description 2")
        );

        when(movieService.getAllMovies()).thenReturn(movies);

        // Act
        ResponseEntity<List<Movie>> response = movieController.getAllMovies();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movies, response.getBody());
        verify(movieService, times(1)).getAllMovies();
    }
}
