package Dao;

import Config.Db;
import Models.Entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientDAO {

    private Connection connection ;

    public ClientDAO()
    {
        this.connection = Db.getInstance().getConnection();
    }

    public boolean  Create(Client client){

        String message ;
        String query= "INSERT INTO your_table_name (id, first_name, last_name, email, phone_number) VALUES (?, ?, ?, ?, ?);";

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
}
