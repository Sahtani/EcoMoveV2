package Dao.Implementations;

import Config.Db;
import Dao.Interfaces.ClientDaoInterface;
import Models.Entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class ClientDAO implements ClientDaoInterface {

    private Connection connection ;

    public ClientDAO()
    {
        this.connection = Db.getInstance().getConnection();
    }

    @Override

    public boolean  addClient(Client client){

        String query= "INSERT INTO clients (id, first_name, last_name, email, phone_number) VALUES (?, ?, ?, ?, ?);";

        try {
           PreparedStatement stmt = connection.prepareStatement(query);
           client.setId(UUID.randomUUID());
           stmt.setObject(1, client.getId());
           stmt.setString(2, client.getFirstName());
           stmt.setString(3, client.getLastName());
           stmt.setString(4, client.getEmail());
           stmt.setInt(5, client.getPhoneNumber());

           int rows=stmt.executeUpdate();
           return rows > 0 ;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //connection client
    @Override
    public Optional<Client> findClient(String firstName, String lastName, String email) {
        String query = "SELECT id, first_name, last_name, email, phone_number FROM clients WHERE email = ? AND (first_name = ? AND last_name = ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Set the parameters for the query
            stmt.setString(1, email);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    Client client = new Client(
                            (UUID) resultSet.getObject("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("email"),
                            resultSet.getInt("phone_number")
                    );
                    return Optional.of(client);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding client", e);
        }
    }

}
