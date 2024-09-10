package Dao.Interfaces;

import Models.Entities.Contract;

import java.util.List;
import java.util.UUID;

public interface ContractDaoInterface {

    boolean addContract(Contract contract);
    boolean updateContract(Contract contract);
    List<Contract> getAllContracts();
    boolean deleteContract(UUID id);
}
