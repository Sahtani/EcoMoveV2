package Dao;

import Config.Db;
import Models.Entities.Partner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PartnerDAO {

    private Connection connection ;


   public PartnerDAO(){
       this.connection= Db.getInstance().getConnection();
   }

    //Methode to diplay all partners :
    public ResultSet getAllPartners() {
        String sql = "SELECT * FROM partners";
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

    // Methode to create Partner :

    public String create(Partner partner){

        String resultMessage = "";
        String sql = "INSERT INTO partners (id, company_name, commercial_contact, transport_type, " +
                "geographical_zone, special_conditions, partner_status, creation_date) " +
                "VALUES (?, ?, ?, CAST(? AS transportType), ?, ?, CAST(? AS partnerStatus), CURRENT_DATE)";

        try  {

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setObject(1,partner.getId());
            pstmt.setString(2, partner.getcompanyName());
            pstmt.setString(3, partner.getcommercialContact());
            pstmt.setString(4, partner.getTransportType().name());
            pstmt.setString(5, partner.getGeographicalArea());
            pstmt.setString(6, partner.getSpecialConditions());
            pstmt.setString(7, partner.getPartnerStatus().name());


            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                resultMessage ="Partner added successfully.";
            }else {
                resultMessage ="No partner added.";
            }

        } catch (SQLException e) {
            System.out.println("Error adding partner: " + e.getMessage());
        }
        return resultMessage;
    }

    //Mehtode to update Partner :

    public boolean update(Partner partner) {
        String resultMessage = "";
        String query = "UPDATE partners SET company_name = ?, commercial_contact = ?, transport_type = ?, geographical_zone = ?, special_conditions = ?, partner_status = ? WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, partner.getcompanyName());
            pstmt.setString(2, partner.getcommercialContact());
            pstmt.setObject(3, partner.getTransportType().toString(), Types.OTHER);
            pstmt.setString(4, partner.getGeographicalArea());
            pstmt.setString(5, partner.getSpecialConditions());
            pstmt.setObject(6, partner.toString(), Types.OTHER);
            pstmt.setObject(7, partner.getId(), Types.OTHER);

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Partner added successfully.");
            }else {
                System.out.println("No partner found with the provided UUID.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating  partner: " + e.getMessage());
            e.printStackTrace();
        }
        return false ;
    }
    //Mehtode to delete partner :

    public String delete(UUID id) {
        String resultMessage;
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM partners WHERE id = ?");
            pstmt.setObject(1, id, Types.OTHER);
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
