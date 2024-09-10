package Services.Implementations;

import Dao.Implementations.PartnerDAO;
import Models.Entities.Partner;
import Models.Enums.PartnerStatus;
import Models.Enums.TransportType;

import java.sql.ResultSet;
import java.util.UUID;

public class PartnerService {
    private PartnerDAO partnerDAO;

    public PartnerService(){
        this.partnerDAO = new PartnerDAO();
    }

    //Partner list :


    public ResultSet partnerList() {
        return partnerDAO.getAllPartners();
    }

    // add Partner :

    public String  addPartner(UUID id, String companyName, String commercialContact, String geographicalArea,
                              String specialConditions, TransportType transportType, PartnerStatus partnerStatus) {
        Partner partner=new Partner(id, companyName, commercialContact, geographicalArea, specialConditions, transportType,partnerStatus);
       return partnerDAO.create(partner);


    }

    // update Partner :

    public boolean updatePartner(UUID id, String companyName, String commercialContact, String geographicalArea,
                                String specialConditions, TransportType transportType, PartnerStatus partnerStatus) {
        Partner partner = new Partner(id, companyName, commercialContact, geographicalArea, specialConditions, transportType, partnerStatus);
        return partnerDAO.update(partner);
    }

    // Mehtode to delete partner :

    public String deletePartner(UUID id) {
        return partnerDAO.delete(id);
    }
}
