package Dao.Interfaces;

import Models.Entities.Client;

import java.util.Optional;

public interface ClientDaoInterface {

    boolean createClient(Client client);

    Optional<Client> findClient(String firstName, String lastName, String email);



}
