package Dao.Implementations;

import Config.Db;
import Dao.Interfaces.PromoDaoInterface;
import Models.Entities.PromotionalOffer;
import Models.Enums.DiscountType;
import Models.Enums.OfferStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PromotionalOfferDAO implements PromoDaoInterface {

    private Connection connection ;

    public PromotionalOfferDAO(){
        this.connection= Db.getInstance().getConnection();
    }



    // display promotions :
    @Override
    public List<PromotionalOffer> getAllPromotions() {
        String sql = "SELECT * FROM promotions";
        List<PromotionalOffer> promotionsList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultPromotions = stmt.executeQuery();

            while (resultPromotions.next()) {
                PromotionalOffer promotion = new PromotionalOffer();
                promotion.setId(UUID.fromString(resultPromotions.getString("id")));
                promotion.setOfferName(resultPromotions.getString("offername"));
                promotion.setDescription(resultPromotions.getString("description"));
                promotion.setStartDate(resultPromotions.getDate("startdate").toLocalDate());
                promotion.setEndDate(resultPromotions.getDate("enddate").toLocalDate());
                promotion.setDiscountType(DiscountType.valueOf(resultPromotions.getString("discounttype")));
                promotion.setConditions(resultPromotions.getString("conditions"));
                promotion.setOfferStatus(OfferStatus.valueOf(resultPromotions.getString("offerstatus").toUpperCase()));
                promotion.setContractId(UUID.fromString(resultPromotions.getString("contractid")));

                promotionsList.add(promotion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return promotionsList;
    }

    // Method to store a Promotion
    @Override
    public boolean addPromotion(PromotionalOffer promotion) {
        String sql = "INSERT INTO promotions (id, offername, description, startdate, enddate, discounttype, " +
                "conditions, offerstatus, contractid) VALUES (?, ?, ?, ?, ?, CAST(? AS discountType), ?, CAST(? AS offerStatus), ?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setObject(1,promotion.getId());
            pstmt.setString(2,promotion.getOfferName());
            pstmt.setString(3,promotion.getDescription());
            pstmt.setObject(4,promotion.getStartDate());
            pstmt.setObject(5,promotion.getEndDate());
            pstmt.setString(6,promotion.getDiscountType().name());
            pstmt.setString(7,promotion.getConditions());
            pstmt.setString(8,promotion.getOfferStatus().name());
            pstmt.setObject(9,promotion.getContractId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("Error adding promotion: " + e.getMessage());
            e.printStackTrace();
        }
        return  false ;
    }

    @Override
    public boolean updatePromotion(PromotionalOffer promotion) {
        String query = "UPDATE promotions SET offername = ?, description = ?, startdate = ?, enddate = ?, discounttype = ?, conditions = ?, offerstatus = ?, contractid = ? WHERE id = ?";

        try (Connection conn = Db.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1,promotion.getOfferName());
            pstmt.setString(2,promotion.getDescription());
            pstmt.setDate(3, java.sql.Date.valueOf(promotion.getStartDate()));
            pstmt.setDate(4, java.sql.Date.valueOf(promotion.getEndDate()));

            pstmt.setObject(5,promotion.getDiscountType().toString(), java.sql.Types.OTHER);

            pstmt.setString(6,promotion.getConditions());

            pstmt.setObject(7,promotion.getOfferStatus().toString(), java.sql.Types.OTHER);

            pstmt.setObject(8,promotion.getContractId(), java.sql.Types.OTHER);
            pstmt.setObject(9, promotion.getId(), java.sql.Types.OTHER);

            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // delete Promotion
    @Override
    public boolean deletePromotion(UUID id) {
        String resultMessage;
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM promotions WHERE id = ?");
            pstmt.setObject(1, id, java.sql.Types.OTHER);
            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception exception) {
            exception.printStackTrace();
            resultMessage = "An error occurred while trying to delete the promotion.";
        }
       return false ;
    }

}

