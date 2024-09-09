package Dao;

import Config.Db;
import Models.Entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ClientDAO {

    private Connection connection ;

    public ClientDAO()
    {
        this.connection = Db.getInstance().getConnection();
    }

    public boolean  Create(Client client){

        String query= "INSERT INTO clients (id, first_name, last_name, email, phone_number) VALUES (?, ?, ?, ?, ?);";

        try {
           PreparedStatement stmt = connection.prepareStatement(query);
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

    public Client findClient(String firstName, String lastName, String email){
        String query = "SELECT id, first_name, last_name, email, phone_number FROM clients WHERE email= ? OR (first_name = ? OR last_name = ? ) " ;
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            try {
                ResultSet resultSet = stmt.executeQuery();
                if (resultSet.next()) {
                    return new Client(
                            (UUID) resultSet.getObject("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("email"),
                            resultSet.getInt("phone_number")
                    );
                } else {
                    return null;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
