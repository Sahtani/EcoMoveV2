package Services.Implementations;

import Dao.Implementations.ContractDAO;
import Models.Entities.Contract;
import Models.Enums.ContractStatus;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.UUID;

public class ContractService  {

    private ContractDAO contractDAO;

    public ContractService(){
        this.contractDAO=new ContractDAO();
    }

    //Contract list :

    public ResultSet getAllContracts() {
        return (ResultSet) contractDAO.getAllContracts();
    }

    // add Contract :

    public String createContract(UUID partnerId, UUID id, LocalDate startDate, LocalDate endDate, float specialRate, String agreementConditions, boolean renewable, ContractStatus status) {
        Contract contract = new Contract();
        contract.setId(UUID.randomUUID());
        contract.setPartnerId(partnerId);
        contract.setStartDate(startDate);
        contract.setEndDate(endDate);
        contract.setSpecialRate(specialRate);
        contract.setAgreementConditions(agreementConditions);
        contract.setRenewable(renewable);
        contract.setContractStatus(status);

        contractDAO.addContract(contract);

        return agreementConditions;
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

        return contractDAO.updateContract(contract);
    }

    // Delete a contract
    public boolean deleteContract(UUID id) {
        return contractDAO.deleteContract(id);
    }


}
