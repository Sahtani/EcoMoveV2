package Services;

import Interfaces.TicketDaoInterface;
import Models.Entities.Ticket;

public class TicketServie {

    private TicketDaoInterface ticketDaoInterface ;

    public TicketServie(TicketDaoInterface ticketDaoInterface ){
        this.ticketDaoInterface = ticketDaoInterface ;
    }

    // add ticket :
    public boolean addTicket(){
        Ticket ticket = new Ticket() ;
        return ticketDaoInterface.createTicket(ticket);
    }
}
