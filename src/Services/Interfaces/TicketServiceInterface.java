package Services.Interfaces;

import Models.Entities.Ticket;

import java.util.List;
import java.util.UUID;

public interface TicketServiceInterface {

    boolean addTicket(Ticket ticket);

    List<Ticket> getAllTickets();

    boolean updateTicket(Ticket ticket);

    boolean deleteTicket(UUID id);


}
