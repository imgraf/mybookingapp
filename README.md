# MyBookingApp

## Description

MyBookingApp is a Spring Boot application that allows users to buy, update, and cancel movie tickets. The application provides a REST API to interact with the movie ticket booking system. It utilizes Spring MVC, Spring Data JPA, Hibernate, and Mockito for testing.

## Getting Started

### Prerequisites

- Java 11 or newer
- Maven

### Installation

1. Clone the repo:
   git clone https://github.com/imgraf/mybookingapp.git
2. Navigate into the project directory:
cd mybookingapp
3. Build the project using Maven:
mvn clean install

## Usage

Run the application:
mvn springboot:run
The application is now running and listening for HTTP requests on `localhost:8080`.

## API Endpoints

- `GET /mymovies/{id}`: Get a movie by its ID.
- `POST /mymovies/buy`: Buy a ticket, passing a TicketRequest in the request body.
- `POST /mymovies/cancel/{ticketId}`: Cancel a ticket by its ID.
- `POST /mymovies/update/{ticketId}`: Update a ticket by its ID, passing a TicketRequest in the request body.
- `GET /mymovies/all`: Get all movies.

## Running Tests

To run the tests, use the following command:
mvn test

## Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are greatly appreciated.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Contact

Graf Raúl

Project Link: [https://github.com/imgraf/mybookingapp](https://github.com/your_username/MyBookingApp)
