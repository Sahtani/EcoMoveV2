package Services.Implementations;

import Dao.Interfaces.TicketDaoInterface;
import Models.Entities.Ticket;
import Services.Interfaces.TicketServiceInterface;

import java.util.List;
import java.util.UUID;

public class TicketService implements TicketServiceInterface {

    private TicketDaoInterface ticketDaoInterface ;

    public TicketService(TicketDaoInterface ticketDaoInterface ){

        this.ticketDaoInterface = ticketDaoInterface ;
    }

    // add ticket :
    public boolean addTicket(Ticket ticket){
        return ticketDaoInterface.createTicket(ticket);
    }

    @Override
    public List<Ticket> getAllTickets() {

        return ticketDaoInterface.findAllTickets();
    }

    @Override
    public boolean updateTicket(Ticket ticket) {
        return ticketDaoInterface.updateTicket(ticket);
    }

    @Override
    public boolean deleteTicket(UUID id) {
        return ticketDaoInterface.deleteTicket(id);
    }

}
