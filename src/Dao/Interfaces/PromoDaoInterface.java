package Dao.Interfaces;

import Models.Entities.PromotionalOffer;

import java.util.List;
import java.util.UUID;

public interface PromoDaoInterface {

    boolean addPromotion(PromotionalOffer promotion);

    boolean updatePromotion(PromotionalOffer promotion);

    boolean deletePromotion(UUID id);

    List<PromotionalOffer> getAllPromotions();

}
