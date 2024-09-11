package Dao.Implementations;

import Config.Db;
import Dao.Interfaces.PartnerDaoInterface;
import Models.Entities.Partner;

import java.sql.*;
import java.util.UUID;

public class PartnerDAO implements PartnerDaoInterface {

    private Connection connection ;


   public PartnerDAO(){
       this.connection= Db.getInstance().getConnection();
   }

    @Override
    // Methode to create Partner :
    public boolean createPartner(Partner partner) {
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

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error adding partner: " + e.getMessage());
        }
        return false ;
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

    //Mehtode to update Partner :

    @Override
    public boolean updatePartner(Partner partner) {

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

                return rowsUpdated > 0;

            } catch (SQLException e) {
                System.out.println("Error updating  partner: " + e.getMessage());
                e.printStackTrace();
            }
            return false ;
        }

    //Mehtode to delete partner :
    @Override
    public boolean deletePartner(int id) {

            try {
                PreparedStatement pstmt = connection.prepareStatement("DELETE FROM partners WHERE id = ?");
                pstmt.setObject(1, id, Types.OTHER);
                int rowsAffected = pstmt.executeUpdate();

                return rowsAffected > 0;
            } catch (Exception exception) {
                exception.printStackTrace();

            }
            return false;
        }
    }



