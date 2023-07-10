package com.mybookingapp.mybookingapp;

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
        // Validierung
        if (ticketRequest.getBuyerName().isEmpty()) {
            throw new IllegalArgumentException("Buyer name is required");
        }

        // Erstellen Sie das neue Ticket und speichern Sie es
        Ticket ticket = new Ticket(ticketRequest);
        return ticketRepository.save(ticket);
    }

    public void cancelTicket(Long ticketId) {
        // Überprüfen Sie, ob das Ticket existiert
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticket id"));

        // Löschen Sie das Ticket
        ticketRepository.delete(ticket);
    }

    public Ticket updateTicket(Long ticketId, TicketRequest ticketRequest) {
        // Finden Sie das Ticket und aktualisieren Sie die Daten
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticket id"));

        ticket.setBuyerName(ticketRequest.getBuyerName());
        ticket.setBookingNumber(ticketRequest.getBookingNumber());

        // Speichern Sie das aktualisierte Ticket
        return ticketRepository.save(ticket);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Transactional
    public void saveMovie(Movie movie) {
        entityManager.merge(movie);
    }



}

    //----------------------------------------------------------------------//



    //----------------------------------------------------------------------//

