package Dao.Interfaces;

import Models.Entities.Ticket;

import java.util.List;
import java.util.UUID;

public interface TicketDaoInterface {


    boolean createTicket(Ticket ticket);
    List<Ticket> findAllTickets();
    boolean updateTicket(Ticket ticket);
    boolean deleteTicket(UUID id);
}

