package Dao.Interfaces;

import Models.Entities.Partner;

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

public interface PartnerDaoInterface {

    boolean createPartner(Partner partner);
    ResultSet getAllPartners();
    boolean updatePartner(Partner partner);
    boolean deletePartner(UUID id);

}
