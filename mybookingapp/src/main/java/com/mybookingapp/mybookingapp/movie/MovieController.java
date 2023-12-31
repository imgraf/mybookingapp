package com.mybookingapp.mybookingapp.movie;

import com.mybookingapp.mybookingapp.ticket.Ticket;
import com.mybookingapp.mybookingapp.ticket.TicketRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/mymovies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }

    @PostMapping("/buy")
    public ResponseEntity<Ticket> buyTicket(@RequestBody TicketRequest ticketRequest) {
        Ticket ticket = movieService.buyTicket(ticketRequest);
        return ResponseEntity.ok(ticket);
    }

    @PostMapping("/cancel/{ticketId}")
    public ResponseEntity<String> cancelTicket(@PathVariable Long ticketId) {
        movieService.cancelTicket(ticketId);
        return ResponseEntity.ok("Ticket cancelled successfully.");
    }

    @PostMapping("/update/{ticketId}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long ticketId, @RequestBody TicketRequest ticketRequest) {
        Ticket updatedTicket = movieService.updateTicket(ticketId, ticketRequest);
        return ResponseEntity.ok(updatedTicket);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }
}