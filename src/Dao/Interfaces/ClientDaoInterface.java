package Dao.Interfaces;

import Models.Entities.Client;

import java.util.Optional;

public interface ClientDaoInterface {

    boolean addClient(Client client);

    Optional<Client> findClient(String firstName, String lastName, String email);



}
