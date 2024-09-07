package Services;

import Dao.PartnerDAO;
import Models.Entities.Partner;
import Models.Enums.PartnerStatus;
import Models.Enums.TransportType;

import java.util.List;
import java.util.UUID;

public class PartnerService {
    private PartnerDAO partnerDAO;

    public PartnerService(){
        this.partnerDAO = new PartnerDAO();
    }

    //Partner list :

    public List<Partner> partnerList(){
        return (List<Partner>) partnerDAO.getAllPartners();
    }

    // add Partner :

    public boolean addPartner(UUID id, String companyName, String commercialContact, String geographicalArea,
                              String specialConditions, TransportType transportType, PartnerStatus partnerStatus) {
        Partner partner=new Partner(id, companyName, commercialContact, geographicalArea, specialConditions, transportType,partnerStatus);
        return partnerDAO.create(partner);


    }

    // update Partner :

    public String updatePartner(UUID id, String companyName, String commercialContact, String geographicalArea,
                                String specialConditions, TransportType transportType, PartnerStatus partnerStatus) {
        Partner partner = new Partner(id, companyName, commercialContact, geographicalArea, specialConditions, transportType, partnerStatus);
        return partnerDAO.update(partner);
    }

    // Mehtode to delete partner :

    public String deletePartner(UUID id) {
        return partnerDAO.delete(id);
    }
}
