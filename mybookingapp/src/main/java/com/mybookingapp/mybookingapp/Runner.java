package com.mybookingapp.mybookingapp;

import com.mybookingapp.mybookingapp.movie.Movie;
import com.mybookingapp.mybookingapp.movie.MovieService;
import com.mybookingapp.mybookingapp.ticket.Ticket;
import com.mybookingapp.mybookingapp.ticket.TicketRequest;

import java.util.List;
import java.util.Scanner;

public class Runner {

    public MovieService movieService;
    public MyBookingAppApplication myApp;

    public Runner(MovieService service, MyBookingAppApplication application) {
        this.movieService = service;
        this.myApp = application;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Was kann ich für Sie tun? [1] Buchen [2] Ändern [3] Stornieren [4] Beenden ([5] Neuen Film hinzufügen)");
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    // Book
                    this.book();
                    System.out.println("Bitte geben Sie den Film-ID ein:");
                    Long movieId = Long.parseLong(scanner.nextLine());
                    System.out.println("Bitte geben Sie Ihren Namen ein:");
                    String buyerName = scanner.nextLine();
                    System.out.println("Bitte geben Sie die Anzahl der Tickets ein:");
                    int ticketCount = Integer.parseInt(scanner.nextLine());
                    TicketRequest ticketRequest = new TicketRequest();
                    ticketRequest.setBuyerName(buyerName);
                    ticketRequest.setTicketCount(ticketCount);

                    Ticket newTicket = movieService.buyTicket(ticketRequest);
                    System.out.println("Ticket erfolgreich gebucht. Ihre Ticket-ID ist: " + newTicket.getId());
                    break;

                case "2":
                    // Change
                    while (true) {
                        try {
                            System.out.println("Bitte geben Sie die Ticket-ID ein, die Sie ändern möchten:");
                            String ticketIdInput = scanner.nextLine();
                            if ("exit".equalsIgnoreCase(ticketIdInput)) {
                                break; // user chose to exit, break the loop
                            }
                            Long ticketIdToUpdate = Long.parseLong(ticketIdInput);

                            System.out.println("Bitte geben Sie den neuen Käufernamen ein:");
                            String newBuyerName = scanner.nextLine();

                            System.out.println("Bitte geben Sie die neue Anzahl der Tickets ein:");
                            String ticketCountInput = scanner.nextLine();
                            if ("exit".equalsIgnoreCase(ticketCountInput)) {
                                break; // user chose to exit, break the loop
                            }
                            int newTicketCount = Integer.parseInt(ticketCountInput);

                            TicketRequest updateRequest = new TicketRequest();
                            updateRequest.setBuyerName(newBuyerName);
                            updateRequest.setTicketCount(newTicketCount);

                            try {
                                Ticket updatedTicket = movieService.updateTicket(ticketIdToUpdate, updateRequest);
                                System.out.println("Ticket erfolgreich aktualisiert.");
                                break; // successful update, break the loop
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Die Ticket-ID und die Ticket-Anzahl müssen Zahlen sein. Bitte versuchen Sie es erneut oder geben Sie 'exit' ein, um abzubrechen.");
                        }
                    }
                    break;

                case "3":
                    // Cancel
                    while (true) {
                        System.out.println("Bitte geben Sie die Ticket-ID ein, die Sie stornieren möchten:");
                        try {
                            Long ticketIdToCancel = Long.parseLong(scanner.nextLine());
                            movieService.cancelTicket(ticketIdToCancel);
                            System.out.println("Ticket erfolgreich storniert.");
                            break; // Exit the loop if the cancellation is successful
                        } catch (IllegalArgumentException e) {
                            System.out.println("Ticket-ID nicht gefunden. Wollen Sie es erneut versuchen? [1] Ja, [2] Nein");
                            String retryResponse = scanner.nextLine();
                            if (!retryResponse.equals("1")) {
                                break; // Exit the loop if the user does not want to retry again
                            }
                        }
                    }
                    break;


                case "4":
                    System.out.println("Auf Wiedersehen!");
                    System.exit(0);

                case "5":
                    Movie movie = new Movie();
                    System.out.print("ID: ");
                    movie.setId(scanner.nextInt());
                    scanner.nextLine();

                    System.out.print("Titel: ");
                    movie.setTitle(scanner.nextLine());
                    System.out.print("Dauer: ");
                    movie.setDuration(scanner.nextInt());
                    scanner.nextLine();

                    System.out.print("Beschreibung: ");
                    movie.setDescription(scanner.nextLine());
                    System.out.print("Genre: ");
                    movie.setGenre(scanner.nextLine());
                    movieService.saveMovie(movie);
                    System.out.println("Film erfolgreich hinzugefügt");
                    break;

                default:
                    System.out.println("Unbekannte Option. Bitte versuchen Sie es erneut.");
            }
        }
    }

    void book() {
        // Get list of all movies
        List<Movie> movies = movieService.getAllMovies();

        // Display the list of movies to the user
        System.out.println("Verfügbare Filme:");
        for (Movie movie : movies) {
            System.out.println("ID: " + movie.getId() + ", Titel: " + movie.getTitle() + ", Dauer: " + movie.getDuration() + " Minuten, Beschreibung: " + movie.getDescription() + ", Genre: " + movie.getGenre());
        }
    }
}
