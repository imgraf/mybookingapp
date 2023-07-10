package com.mybookingapp.mybookingapp;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import com.mybookingapp.mybookingapp.movie.Movie;
import com.mybookingapp.mybookingapp.movie.MovieRepository;
import com.mybookingapp.mybookingapp.movie.MovieService;
import com.mybookingapp.mybookingapp.ticket.Ticket;
import com.mybookingapp.mybookingapp.ticket.TicketRepository;
import com.mybookingapp.mybookingapp.ticket.TicketRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import javax.persistence.EntityManager;
import static org.junit.jupiter.api.Assertions.assertEquals;



@SpringBootTest
public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private TicketRepository ticketRepository;
    @MockBean
    private EntityManager entityManager;



    @Test
    public void getMovieByIdTest() {
        // Given
        Movie movie = new Movie();
        movie.setId(1);
        movie.setTitle("Test Movie");
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        // When
        Movie result = movieService.getMovieById(1L);

        // Then
        assertEquals(movie, result);
        verify(movieRepository, times(1)).findById(1L);
    }

    @Test
    public void buyTicketTest() {
        // Given
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setBuyerName("Test Buyer");

        Ticket ticket = new Ticket(ticketRequest);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        // When
        Ticket result = movieService.buyTicket(ticketRequest);

        // Then
        assertEquals(ticket, result);
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }


    @Test
    public void cancelTicketTest() {
        // Given
        Long ticketId = 1L;
        Ticket ticket = new Ticket();
        ticket.setId(ticketId);
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));

        // When
        movieService.cancelTicket(ticketId);

        // Then
        verify(ticketRepository, times(1)).delete(ticket);
    }

    @Test
    public void updateTicketTest() {
        // Given
        Long ticketId = 1L;
        Ticket ticket = new Ticket();
        ticket.setId(ticketId);

        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setBuyerName("Updated Buyer");

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        // When
        Ticket result = movieService.updateTicket(ticketId, ticketRequest);

        // Then
        assertEquals(ticket, result);
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    public void getAllMoviesTest() {
        // Given
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie());
        when(movieRepository.findAll()).thenReturn(movies);

        // When
        List<Movie> result = movieService.getAllMovies();

        // Then
        assertEquals(movies, result);
        verify(movieRepository, times(1)).findAll();
    }
}
