package Services.Interfaces;

import Models.Entities.Client;

import java.util.Optional;

public interface ClientServiceInterface {

    boolean addClient(Client client);
//    void updateClient(Client client);
    Optional<Client> loginClient(String firstName, String lastName, String email);
}
