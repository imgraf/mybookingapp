package com.mybookingapp.mybookingapp.ticket;

import com.mybookingapp.mybookingapp.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
