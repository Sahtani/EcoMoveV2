package Dao.Implementations;

import Config.Db;
import Dao.Interfaces.TicketDaoInterface;
import Models.Entities.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class TicketDAO implements TicketDaoInterface {

    private Connection connection ;
    public TicketDAO() {
        this.connection = Db.getInstance().getConnection();

    }
    @Override
    public boolean createTicket(Ticket ticket) {
            String query = "INSERT INTO ticket (id, transporttype, purchaseprice, saleprice, saledate, ticketstatus, contractid) VALUES (?,CAST(? AS transportType), ?, ?, ?, CAST(? AS ticketStatus), ?)";

            try {
                PreparedStatement pstmt = connection.prepareStatement(query);

                UUID ticketId = UUID.randomUUID();
                pstmt.setObject(1, ticketId, java.sql.Types.OTHER);


                pstmt.setString(2, ticket.getTransportType().toString());
                pstmt.setFloat(3, ticket.getPurchasePrice());
                pstmt.setFloat(4, ticket.getSalePrice());
                pstmt.setTimestamp(5, ticket.getSaleDate());
                pstmt.setString(6, ticket.getTicketStatus().name());


                pstmt.setObject(7, ticket.getContractId(), java.sql.Types.OTHER);

                int affectedRows = pstmt.executeUpdate();
                return affectedRows > 0;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

    @Override
    public List<Ticket> findAllTickets() {
        return List.of();
    }

    @Override
    public boolean updateTicket(Ticket ticket) {
        return false;
    }

    @Override
    public boolean deleteTicket(UUID id) {
        return false;
    }





}
