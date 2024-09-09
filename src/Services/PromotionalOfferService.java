package Services;

import Dao.PromotionalOfferDAO;
import Models.Entities.PromotionalOffer;
import Models.Enums.DiscountType;
import Models.Enums.OfferStatus;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.UUID;

public class PromotionalOfferService {


    private PromotionalOfferDAO promotionalOfferDAO;

    public PromotionalOfferService() {
        this.promotionalOfferDAO = new PromotionalOfferDAO();
    }

    // Create a new promotional offer
    public void createPromotionalOffer(String offerName, String description, LocalDate startDate, LocalDate endDate,
                                       DiscountType discountType, String conditions, OfferStatus offerStatus, UUID contractId) {
        PromotionalOffer promotionalOffer = new PromotionalOffer();
        promotionalOffer.setId(UUID.randomUUID());
        promotionalOffer.setOfferName(offerName);
        promotionalOffer.setDescription(description);
        promotionalOffer.setStartDate(startDate);
        promotionalOffer.setEndDate(endDate);
        promotionalOffer.setDiscountType(discountType);
        promotionalOffer.setConditions(conditions);
        promotionalOffer.setOfferStatus(offerStatus);
        promotionalOffer.setContractId(contractId);

        promotionalOfferDAO.create(promotionalOffer);
    }

    // Get all promotional offers
    public ResultSet getAllPromotions() {
        return promotionalOfferDAO.displayPromotions();
    }

    // Update an existing promotional offer
    public boolean updatePromotionalOffer(UUID id, String offerName, String description, LocalDate startDate, LocalDate endDate,
                                          DiscountType discountType, String conditions, OfferStatus offerStatus,UUID contractId) {
        PromotionalOffer promotionalOffer = new PromotionalOffer();
        promotionalOffer.setId(id);
        promotionalOffer.setOfferName(offerName);
        promotionalOffer.setDescription(description);
        promotionalOffer.setStartDate(startDate);
        promotionalOffer.setEndDate(endDate);
        promotionalOffer.setDiscountType(discountType);
        promotionalOffer.setConditions(conditions);
        promotionalOffer.setOfferStatus(offerStatus);
        promotionalOffer.setContractId(contractId);

        return promotionalOfferDAO.update(promotionalOffer);
    }

    // Delete a promotional offer
    public String deletePromotionalOffer(UUID id) {
        return promotionalOfferDAO.delete(id);
    }


}

