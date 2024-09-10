package Services.Implementations;

import Dao.Implementations.PromotionalOfferDAO;
import Dao.Interfaces.TicketDaoInterface;
import Models.Entities.PromotionalOffer;
import Models.Enums.DiscountType;
import Models.Enums.OfferStatus;
import Services.Interfaces.PromoServiceInterface;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class PromotionalOfferService implements PromoServiceInterface {


    private PromotionalOfferDAO promotionalOfferDAO;


    public PromotionalOfferService(PromotionalOfferDAO promotionalOfferDAO ){

        this.promotionalOfferDAO = promotionalOfferDAO ;
    }



    @Override
    public boolean addPromotion(PromotionalOffer promotion) {
        return promotionalOfferDAO.addPromotion(promotion);
    }

    @Override
    public boolean updatePromotion(PromotionalOffer promotion) {
        return promotionalOfferDAO.updatePromotion(promotion);
    }

    @Override
    public boolean deletePromotion(UUID id) {
        return promotionalOfferDAO.deletePromotion(id);
    }

    // Get all promotional offers
    public List<PromotionalOffer> getAllPromotions() {

        return promotionalOfferDAO.getAllPromotions();
    }


}

