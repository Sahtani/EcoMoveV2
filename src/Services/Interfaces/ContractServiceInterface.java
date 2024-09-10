package Services.Interfaces;

import Models.Entities.Contract;

import java.util.List;
import java.util.UUID;

public interface ContractServiceInterface {

    boolean addContract(Contract contract);

    boolean updateContract(Contract contract);

    boolean deleteContract(UUID id);

    List<Contract> getAllContracts();
}
