package Services.Interfaces;

import Models.Entities.Partner;

import java.sql.ResultSet;
import java.util.UUID;

public interface PartnerServiceInterface {

    boolean addPartner(Partner partner);

    ResultSet getAllPartners();

    boolean updatePartner(Partner partner);

    boolean deletePartner(UUID id);
}
