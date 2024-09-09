package Interfaces;

import Models.Entities.Client;

import java.util.List;
import java.util.Optional;

public interface ClientDaoInterface {

    boolean createClient(Client client);

    Optional<Client> findClient(String firstName, String lastName, String email);



}
