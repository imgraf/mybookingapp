package com.mybookingapp.mybookingapp;

import com.mybookingapp.mybookingapp.movie.Movie;
import com.mybookingapp.mybookingapp.movie.MovieService;
import com.mybookingapp.mybookingapp.ticket.Ticket;
import com.mybookingapp.mybookingapp.ticket.TicketRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Runner {

    Long movieId;
    String movieName;
    public MovieService movieService;
    public MyBookingAppApplication myApp;

    public Runner(MovieService service, MyBookingAppApplication application) {
        this.movieService = service;
        this.myApp = application;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
            What can I do for you? 
            [1] Book 
            [2] Change 
            [3] Cancel 
            [4] Exit 
            [5] Add new movie \n""");

            System.out.print("Your option: ");
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    // Book
                    this.displayAllMovies();

                    String bookingID = this.createBookingId();

                    System.out.print("Please enter the film ID: ");
                    Long movieId = Long.parseLong(scanner.nextLine());
                    String movieName = this.getMovieName(movieId);

                    System.out.print("Movie name: " + movieName);

                    System.out.println(" ");

                    System.out.print("Please enter your name: ");
                    String buyerName = scanner.nextLine();

                    System.out.print("Please enter the number of tickets: ");
                    int ticketCount = Integer.parseInt(scanner.nextLine());

                    System.out.println(" ");

                    TicketRequest ticketRequest = new TicketRequest();
                    ticketRequest.setBookingNumber(bookingID);
                    ticketRequest.setMovieTitle(movieName);
                    ticketRequest.setBuyerName(buyerName);
                    ticketRequest.setTicketCount(ticketCount);

                    Ticket newTicket = movieService.buyTicket(ticketRequest);
                    System.out.println("Ticket booked successfully. Your ticket ID is: " + newTicket.getId());
                    System.out.println("Booking number: " + bookingID);
                    System.out.println("Customer name: " + buyerName);
                    System.out.println("Movie title: " + movieName);
                    System.out.println("Number of tickets: " + ticketCount);
                    System.out.println(" ");

                    break;

                case "2":
                    // Change
                    while (true) {
                        try {
                            System.out.print("Please enter the ticket ID you want to change: ");
                            String ticketIdInput = scanner.nextLine();

                            if ("exit".equalsIgnoreCase(ticketIdInput)) {
                                break; // user chose to exit, break the loop
                            }
                            Long ticketIdToUpdate = Long.parseLong(ticketIdInput);
                            TicketRequest updateRequest = new TicketRequest();

                            this.displayAllMovies();

                            System.out.print("Please enter the new movie ID: ");
                            movieId = Long.parseLong(scanner.nextLine());
                            movieName = this.getMovieName(movieId);
                            updateRequest.setMovieTitle(movieName);
                            System.out.print("Movie name: " + movieName);

                            System.out.print("\nPlease enter the new buyer name: ");
                            String newBuyerName = scanner.nextLine();
                            updateRequest.setBuyerName(newBuyerName);

                            try {
                                Ticket updatedTicket = movieService.updateTicket(ticketIdToUpdate, updateRequest);
                                System.out.println("Ticket successfully updated.");
                                System.out.println("Customer name: " + updatedTicket.getBuyerName());
                                System.out.println("Booking number: " + updatedTicket.getBookingNumber());
                                System.out.println("Movie title: " + updatedTicket.getMovieName());
                                System.out.println("Number of tickets: " + updatedTicket.getTicketNumber() + "\n");

                                break; // successful update, break the loop
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("The ticket ID and ticket count must be numbers. Please try again or enter 'exit' to cancel.");
                        }
                    }
                    break;

                case "3":
                    // Cancel
                    while (true) {
                        System.out.println("Please enter the ticket ID you wish to cancel:");
                        try {
                            Long ticketIdToCancel = Long.parseLong(scanner.nextLine());
                            movieService.cancelTicket(ticketIdToCancel);
                            System.out.println("Ticket successfully cancelled. \n");
                            break; // Exit the loop if the cancellation is successful
                        } catch (IllegalArgumentException e) {
                            System.out.println("Ticket ID not found. Do you want to try again? [1] Yes, [2] No");
                            String retryResponse = scanner.nextLine();
                            if (!retryResponse.equals("1")) {
                                break; // Exit the loop if the user does not want to retry again
                            }
                        }
                    }
                    break;


                case "4":
                    System.out.println("Goodbye!");
                    System.exit(0);

                case "5":
                    Movie movie = new Movie();
                    System.out.print("ID: ");
                    movie.setId(scanner.nextInt());
                    scanner.nextLine();

                    System.out.print("Title: ");
                    movie.setTitle(scanner.nextLine());
                    System.out.print("Duration: ");
                    movie.setDuration(scanner.nextInt());
                    scanner.nextLine();

                    System.out.print("Description: ");
                    movie.setDescription(scanner.nextLine());
                    System.out.print("Genre: ");
                    movie.setGenre(scanner.nextLine());
                    movieService.saveMovie(movie);
                    System.out.println("Film successfully added");
                    break;

                default:
                    System.out.println("Unknown option. Please try again.");
            }
        }
    }

    void displayAllMovies() {
        // Get list of all movies
        List<Movie> movies = movieService.getAllMovies();

        // Display the list of movies to the user
        System.out.println("Available movies:");
        for (Movie movie : movies) {
            System.out.println("ID: " + movie.getId() + ", Title: " + movie.getTitle() + ", Duration: " + movie.getDuration() + " Minutes, Description: " + movie.getDescription() + ", Genre: " + movie.getGenre());
        }
    }

    public String getMovieName(Long id) {
        String movieName = "";
        List<Movie> movies = movieService.getAllMovies();

        for (Movie movie : movies) {
            if (movie.getId() == id) {
                movieName = movie.getTitle();
            }
        }

        return movieName;
    }



    public String createBookingId() {
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");

        String booking_id = now.format(formatter);

        return booking_id;
    }
}
