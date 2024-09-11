package Services.Interfaces;

import Models.Entities.Client;

import java.util.Optional;
import java.util.UUID;

public interface ClientServiceInterface {

    boolean addClient(Client client);

    boolean updateClient(Client client);

    Optional<Client> loginClient(String firstName, String lastName, String email);

    boolean isLoggedIn();

    UUID getLoggedInClientId();
}
