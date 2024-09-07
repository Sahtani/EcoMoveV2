package Services;

import Dao.ContractDAO;
import Models.Entities.Contract;
import Models.Enums.ContractStatus;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ContarctService {

    private ContractDAO contractDAO;

    public ContarctService(){
        this.contractDAO=new ContractDAO();
    }

    //Contract list :

    public ResultSet getAllContracts() {
        return contractDAO.getAllContracts();
    }

    // add Contract :

    public void createContract(UUID partnerId, LocalDate startDate, LocalDate endDate, float specialRate, String agreementConditions, boolean renewable, ContractStatus status) {
        Contract contract = new Contract();
        contract.setId(UUID.randomUUID()); // Generate unique ID for contract
        contract.setPartnerId(partnerId);
        contract.setStartDate(startDate);
        contract.setEndDate(endDate);
        contract.setSpecialRate(specialRate);
        contract.setAgreementConditions(agreementConditions);
        contract.setRenewable(renewable);
        contract.setContractStatus(status);

        contractDAO.create(contract);
    }

    // update contract :

    public boolean updateContract(UUID id, LocalDate startDate, LocalDate endDate, float specialRate, String agreementConditions, boolean renewable, ContractStatus status) {
        Contract contract = new Contract();
        contract.setId(id);
        contract.setStartDate(startDate);
        contract.setEndDate(endDate);
        contract.setSpecialRate(specialRate);
        contract.setAgreementConditions(agreementConditions);
        contract.setRenewable(renewable);
        contract.setContractStatus(status);

        return contractDAO.update(contract);
    }

    // Delete a contract
    public String deleteContract(UUID id) {
        return contractDAO.delete(id);
    }


}
