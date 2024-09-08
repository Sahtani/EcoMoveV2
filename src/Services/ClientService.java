package Services;

import Dao.ClientDAO;
import Models.Entities.Client;

import java.util.UUID;

public class ClientService {

    private ClientDAO clientDAO;

    public ClientService(){
        this.clientDAO = new ClientDAO();
    }

    // add Client :

    public String createClient(UUID id, String firstName, String lastName, String email , int phoneNumber){
        Client client = new Client(id , firstName, lastName, email, phoneNumber) ;
        return String.valueOf(clientDAO.Create(client));
    }
}
