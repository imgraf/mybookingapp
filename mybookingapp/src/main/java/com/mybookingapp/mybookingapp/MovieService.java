package com.mybookingapp.mybookingapp;

import com.mybookingapp.mybookingapp.MovieRepository;
import com.mybookingapp.mybookingapp.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final TicketRepository ticketRepository;

    public MovieService(MovieRepository movieRepository, TicketRepository ticketRepository) {
        this.movieRepository = movieRepository;
        this.ticketRepository = ticketRepository;
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid movie id"));
    }

    public Ticket buyTicket(TicketRequest ticketRequest) {
        // Validiere die Ticketanfrage
        // (Fügen Sie hier Ihren Validierungscode ein)

        // Erstelle ein Ticket basierend auf der Ticketanfrage
        Ticket newTicket = new Ticket(ticketRequest);
        // (Dank des neuen Konstruktors, den Sie in Ticket.java hinzugefügt haben, sollte dies nun funktionieren)

        // Speichere das Ticket in der Datenbank und gib es zurück
        return ticketRepository.save(newTicket);
    }


    public void cancelTicket(Long ticketId) {
        // Finden Sie das Ticket in der Datenbank anhand der Ticket-ID

        // Überprüfen Sie, ob das Ticket existiert und stornierbar ist

        // Stornieren Sie das Ticket in der Datenbank
    }

    public Ticket updateTicket(Long ticketId, TicketRequest ticketRequest) {
        // Finden Sie das Ticket in der Datenbank anhand der Ticket-ID
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new IllegalArgumentException("Invalid ticket id: " + ticketId));

        // Überprüfen Sie, ob das Ticket änderbar ist
        // Aktualisieren Sie die Ticketdetails basierend auf der Ticketanfrage

        return ticketRepository.save(ticket);
    }
}