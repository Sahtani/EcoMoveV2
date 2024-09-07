package Models.Entities;
import java.time.LocalDate;
import java.util.UUID;

import Config.Db;
import Models.Enums.ContractStatus;
import java.sql.Connection;


public class Contract {


        private UUID id;
        private UUID partnerId;
        private LocalDate startDate;
        private LocalDate endDate;
        private float specialRate;
        private String agreementConditions;
        private boolean renewable;
        private ContractStatus contractStatus;
        private Connection connection;

        public Contract(UUID id, UUID partnerId, LocalDate startDate, LocalDate endDate, float specialRate, String agreementConditions, boolean renewable, ContractStatus contractStatus) {
            this.id = id;
            this.partnerId = partnerId;
            this.startDate = startDate;
            this.endDate = endDate;
            this.specialRate = specialRate;
            this.agreementConditions = agreementConditions;
            this.renewable = renewable;
            this.contractStatus = contractStatus;
        }

        // constructor
        public Contract() {
            this.connection = Db.getInstance().getConnection();
        }


        //Getters
        public UUID getId() {
            return id;
        }

        public UUID getPartnerId() {
            return partnerId;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return this.endDate;
        }

        public float getSpecialRate() {
            return specialRate;
        }

        public String getAgreementConditions() {
            return agreementConditions;
        }

        public boolean isRenewable() {
            return renewable;
        }

        public ContractStatus getContractStatus() {
            return contractStatus;
        }

        //Setters
        public void setId(UUID id) {
            this.id = id;
        }

        public void setPartnerId(UUID partnerId) {
            this.partnerId = partnerId;
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }

        public void setSpecialRate(float specialRate) {
            this.specialRate = specialRate;
        }

        public void setAgreementConditions(String agreementConditions) {
            this.agreementConditions = agreementConditions;
        }

        public void setRenewable(boolean renewable) {
            this.renewable = renewable;
        }

        public String setContractStatus(ContractStatus contractStatus) {
            this.contractStatus = contractStatus;
            return null;
        }


    }