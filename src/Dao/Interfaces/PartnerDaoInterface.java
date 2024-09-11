package Dao.Interfaces;

import Models.Entities.Partner;

import java.util.List;

public interface PartnerDaoInterface {

    boolean createPartner(Partner partner);
    List<Partner> getAllPartners();
    boolean updatePartner(Partner partner);
    boolean deletePartner(int id);

}
