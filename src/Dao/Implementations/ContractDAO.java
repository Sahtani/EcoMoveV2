package Dao.Implementations;

import Config.Db;
import Dao.Interfaces.ContractDaoInterface;
import Models.Entities.Contract;
import Models.Enums.ContractStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContractDAO implements ContractDaoInterface {

    private Connection connection;
    public ContractDAO(){
        this.connection= Db.getInstance().getConnection();
    }



    @Override
    public boolean addContract(Contract contract) {
        String sql = "INSERT INTO contracts (id, partner_id, start_date, end_date, special_rate, agreement_conditions, renewable, contract_status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, CAST(? AS statusContract))";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setObject(1, contract.getId());
            pstmt.setObject(2, contract.getPartnerId());
            pstmt.setObject(3, java.sql.Date.valueOf(contract.getStartDate()));

            if (contract.getEndDate() != null) {
                pstmt.setObject(4, java.sql.Date.valueOf(contract.getEndDate()));
            } else {
                pstmt.setNull(4, java.sql.Types.DATE);
            }

            pstmt.setFloat(5, contract.getSpecialRate());
            pstmt.setString(6, contract.getAgreementConditions());
            pstmt.setBoolean(7, contract.isRenewable());

            if (contract.getContractStatus() != null) {
                pstmt.setString(8, contract.getContractStatus().name());
            } else {
                pstmt.setNull(8, java.sql.Types.VARCHAR);
            }

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean updateContract(Contract contract) {

            String query = "UPDATE contracts SET start_date = ?, end_date = ?, special_rate = ?, agreement_conditions = ?, renewable = ?, contract_status = ? WHERE id = ?";

            try {
                PreparedStatement pstmt = connection.prepareStatement(query);

//            pstmt.setObject(1, getPartnerId(), java.sql.Types.OTHER);
                pstmt.setObject(1,contract.getStartDate(), java.sql.Types.DATE);
                pstmt.setDate(2, java.sql.Date.valueOf(contract.getEndDate()));
                pstmt.setFloat(3,contract.getSpecialRate());
                pstmt.setString(4,contract.getAgreementConditions());
                pstmt.setBoolean(5,contract.isRenewable());
                pstmt.setObject(6,contract.getContractStatus().toString(), java.sql.Types.OTHER);
                pstmt.setObject(7,contract.getId(), java.sql.Types.OTHER);

                int rowsUpdated = pstmt.executeUpdate();
                return rowsUpdated > 0;
            } catch (SQLException e) {
                System.err.println("Error updating contract: " + e.getMessage());
            }

            return false;
        }




    @Override
    public boolean deleteContract(UUID id) {
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM contracts WHERE id = ?");
            pstmt.setObject(1, id, java.sql.Types.OTHER);
            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception exception) {
            exception.printStackTrace();

        }

        return false;
    }

    // display list of contracts
    @Override
    public List<Contract> getAllContracts() {
        String sql = "SELECT * FROM contracts";
        List<Contract> contracts = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                UUID id = (UUID) resultSet.getObject("id");
                UUID partnerId = (UUID) resultSet.getObject("partner_id");
                LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
                LocalDate endDate = resultSet.getDate("end_date") != null ? resultSet.getDate("end_date").toLocalDate() : null;
                float specialRate = resultSet.getFloat("special_rate");
                String agreementConditions = resultSet.getString("agreement_conditions");
                boolean renewable = resultSet.getBoolean("renewable");
                ContractStatus contractStatus = ContractStatus.valueOf(resultSet.getString("contract_status"));

                Contract contract = new Contract(id, partnerId, startDate, endDate, specialRate, agreementConditions, renewable, contractStatus);
                contracts.add(contract);
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return contracts;
    }



}
