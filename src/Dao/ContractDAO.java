package Dao;

import Config.Db;
import Models.Entities.Contract;
import Models.Enums.ContractStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ContractDAO {

    private Connection connection;
    public ContractDAO(){
        this.connection= Db.getInstance().getConnection();
    }

    // display list of contracts

    public ResultSet getAllContracts() {
        String sql = "SELECT * FROM contracts";
        ResultSet resultPartners = null;

        try {

            PreparedStatement stmt = connection.prepareStatement(sql);

            resultPartners = stmt.executeQuery();

        } catch (Exception exception) {
            System.out.println("Statement Exception: " + exception.getMessage());
            exception.printStackTrace();
        }

        return resultPartners;
    }

     //Insert a new contract :

    public void create(Contract contract) {
        String sql = "INSERT INTO contracts (id, partner_id, start_date, end_date, special_rate, agreement_conditions, renewable, contract_status) VALUES (?, ?, ?, ?, ?, ?, ?,CAST(? AS statusContract))";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setObject(1, contract.getId());
            pstmt.setObject(2, contract.getPartnerId());
            pstmt.setObject(3, contract.getStartDate());
            if (contract.getEndDate() != null) {
                pstmt.setObject(4, contract.getEndDate());
            } else {
                pstmt.setNull(4, java.sql.Types.DATE);
            }
            pstmt.setFloat(5, contract.getSpecialRate());
            pstmt.setString(6, contract.getAgreementConditions());
            pstmt.setBoolean(7, contract.isRenewable());

            ContractStatus status = contract.getContractStatus();
            if (status != null) {

                pstmt.setString(8, status.name());
            } else {
                pstmt.setNull(8, java.sql.Types.VARCHAR);
            }


            pstmt.executeUpdate();
            System.out.println("Contract stored successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //update contract
    public boolean update(Contract contract) {

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
            if (rowsUpdated > 0) {
                System.out.println("Contract updated successfully.");
            } else {
                System.out.println("No contract found with the provided id.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating contract: " + e.getMessage());
        }

        return false;
    }

    //Remove sepcific Contract :

    public String delete(UUID id) {
        String resultMessage;
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM contracts WHERE id = ?");
            pstmt.setObject(1, id, java.sql.Types.OTHER);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                resultMessage = "Partner deleted successfully.";
            } else {
                resultMessage = "No partner found with the provided UUID.";
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            resultMessage = "An error occurred while trying to delete the partner.";
        }
        return resultMessage;
    }





}
