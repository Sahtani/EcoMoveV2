package Services.Implementations;

import Dao.Interfaces.ClientDaoInterface;
import Models.Entities.Client;
import Services.Interfaces.ClientServiceInterface;

import java.util.Optional;
import java.util.UUID;

public class ClientService implements ClientServiceInterface {

    private ClientDaoInterface clientDao;
    private UUID loggedInClientId;

    public ClientService(ClientDaoInterface clientDao) {
        this.clientDao = clientDao;
    }


    @Override
    public boolean addClient(Client client) {
        return clientDao.addClient(client);
    }

    // upadet client ;

    public boolean updateClient(Client client){
        return clientDao.update(client);
    }

    // login client :
    public Optional<Client> loginClient(String firstName, String lastName, String email) {

        return clientDao.findClient(firstName, lastName, email);
    }

    public boolean isLoggedIn() {
        return loggedInClientId != null;
    }
    public UUID getLoggedInClientId() {
        return loggedInClientId;
    }

}
