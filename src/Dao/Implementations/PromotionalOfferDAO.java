package Dao.Implementations;

import Config.Db;
import Models.Entities.PromotionalOffer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PromotionalOfferDAO {

    private Connection connection ;

    public PromotionalOfferDAO(){
        this.connection= Db.getInstance().getConnection();
    }



    // display promotions :
    public ResultSet displayPromotions() {
        String sql = "SELECT * FROM promotions";
        ResultSet resultPromotions = null;

        try {

            PreparedStatement stmt = connection.prepareStatement(sql);
            resultPromotions = stmt.executeQuery();

        } catch (Exception exception) {
            System.out.println("Statement Exception: " + exception.getMessage());
            exception.printStackTrace();
        }

        return resultPromotions;
    }

    // Method to store a Promotion
    public void create(PromotionalOffer promotionalOffer) {
        String sql = "INSERT INTO promotions (id, offername, description, startdate, enddate, discounttype, " +
                "conditions, offerstatus, contractid) VALUES (?, ?, ?, ?, ?, CAST(? AS discountType), ?, CAST(? AS offerStatus), ?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setObject(1,promotionalOffer.getId());
            pstmt.setString(2,promotionalOffer.getOfferName());
            pstmt.setString(3,promotionalOffer.getDescription());
            pstmt.setObject(4,promotionalOffer.getStartDate());
            pstmt.setObject(5,promotionalOffer.getEndDate());
            pstmt.setString(6,promotionalOffer.getDiscountType().name());
            pstmt.setString(7,promotionalOffer.getConditions());
            pstmt.setString(8,promotionalOffer.getOfferStatus().name());
            pstmt.setObject(9,promotionalOffer.getContractId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Promotion test successfully.");
            } else {
                System.out.println("Failed to add promotion.");
            }

        } catch (SQLException e) {
            System.out.println("Error adding promotion: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean update(PromotionalOffer promotionalOffer) {
        String query = "UPDATE promotions SET offername = ?, description = ?, startdate = ?, enddate = ?, discounttype = ?, conditions = ?, offerstatus = ?, contractid = ? WHERE id = ?";

        try (Connection conn = Db.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1,promotionalOffer.getOfferName());
            pstmt.setString(2,promotionalOffer.getDescription());
            pstmt.setDate(3, java.sql.Date.valueOf(promotionalOffer.getStartDate()));
            pstmt.setDate(4, java.sql.Date.valueOf(promotionalOffer.getEndDate()));

            pstmt.setObject(5,promotionalOffer.getDiscountType().toString(), java.sql.Types.OTHER);

            pstmt.setString(6,promotionalOffer.getConditions());

            pstmt.setObject(7,promotionalOffer.getOfferStatus().toString(), java.sql.Types.OTHER);

            pstmt.setObject(8,promotionalOffer.getContractId(), java.sql.Types.OTHER);
            pstmt.setObject(9, promotionalOffer.getId(), java.sql.Types.OTHER);

            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // delete Promotion
    public String delete(UUID id) {
        String resultMessage;
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM promotions WHERE id = ?");
            pstmt.setObject(1, id, java.sql.Types.OTHER);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                resultMessage = "Promotion deleted successfully.";
            } else {
                resultMessage = "No promotion found with the provided UUID.";
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            resultMessage = "An error occurred while trying to delete the promotion.";
        }
        return resultMessage;
    }

}

