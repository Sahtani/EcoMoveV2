package Dao.Interfaces;

import Models.Entities.Partner;

import java.sql.ResultSet;
import java.util.List;

public interface PartnerDaoInterface {

    boolean createPartner(Partner partner);
    ResultSet getAllPartners();
    boolean updatePartner(Partner partner);
    boolean deletePartner(int id);

}
