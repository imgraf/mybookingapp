package com.mybookingapp.mybookingapp.movie;

import com.mybookingapp.mybookingapp.ticket.Ticket;
import com.mybookingapp.mybookingapp.ticket.TicketRepository;
import com.mybookingapp.mybookingapp.ticket.TicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    private EntityManager entityManager;


    public MovieService(MovieRepository movieRepository, TicketRepository ticketRepository) {
        this.movieRepository = movieRepository;
        this.ticketRepository = ticketRepository;
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid movie id"));
    }

    public Ticket buyTicket(TicketRequest ticketRequest) {
        // Validation
        if (ticketRequest.getBuyerName().isEmpty()) {
            throw new IllegalArgumentException("Buyer name is required");
        }

        Ticket ticket = new Ticket(ticketRequest);
        return ticketRepository.save(ticket);
    }

    public void cancelTicket(Long ticketId) {
        // Check if the ticket exists
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticket id"));

        // Delete the ticket
        ticketRepository.delete(ticket);
    }

    public Ticket updateTicket(Long ticketId, TicketRequest ticketRequest) {
        // Find the ticket and update the data
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Ticket mit der ID " + ticketId + " existiert nicht."));

        // ticket.setBookingNumber(ticketRequest.getBookingNumber());
        ticket.setBuyerName(ticketRequest.getBuyerName());

        // Save the updated ticket
        return ticketRepository.save(ticket);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Transactional
    public void saveMovie(Movie movie) {
        entityManager.merge(movie);
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }
}
