package Models.Entities ;

import Config.Db;
import Models.Enums.DiscountType;
import Models.Enums.OfferStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;
public class PromotionalOffer {




    private UUID id;
    private String offerName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private DiscountType discountType;
    private float reductionValue ;
    private String conditions;
    private OfferStatus offerStatus;
    private UUID contractId;
    private Connection connection;

    // Constructor
    public PromotionalOffer(UUID id, String offerName, String description, LocalDate startDate, LocalDate endDate,
                            DiscountType discountType,float reductionValue,  String conditions, OfferStatus offerStatus, UUID contractId) {
        this.id = id;
        this.offerName = offerName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountType = discountType;
        this.reductionValue = reductionValue ;
        this.conditions = conditions;
        this.offerStatus = offerStatus;
        this.contractId = contractId;
        this.connection = Db.getInstance().getConnection();
    }

    // Default constructor
    public PromotionalOffer() {
        this.connection = Db.getInstance().getConnection();
    }

    // Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public float getReductionValue(){
        return reductionValue ;
    }

    public void setReductionValue(float reductionValue){
        this.reductionValue = reductionValue ;
    }
    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    public UUID getContractId() {
        return contractId;
    }

    public void setContractId(UUID contractId) {
        this.contractId = contractId;
    }


  }