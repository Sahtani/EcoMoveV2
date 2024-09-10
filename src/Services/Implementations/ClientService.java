package Services.Implementations;

import Dao.Interfaces.ClientDaoInterface;
import Models.Entities.Client;

import java.util.Optional;
import java.util.UUID;

public class ClientService {


    private ClientDaoInterface clientDaoInterface ;

    public ClientService(ClientDaoInterface clientDaoInterface){
        this.clientDaoInterface = clientDaoInterface;
    }

    // add Client :

    public boolean createClient(UUID id, String firstName, String lastName, String email , int phoneNumber){
        Client client = new Client(id , firstName, lastName, email, phoneNumber) ;
        return clientDaoInterface.createClient(client);
    }

    // login client :
    public Optional<Client> loginClient(String firstName, String lastName, String email){

        return clientDaoInterface.findClient(firstName, lastName,email);
    }
}
