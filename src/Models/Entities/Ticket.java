package Models.Entities;

import Config.Db;
import Models.Enums.TicketStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;

import Models.Enums.TransportType;


public class Ticket {


    private UUID id;
    private TransportType transportType;
    private float purchasePrice;
    private float salePrice;
    private Timestamp saleDate;
    private TicketStatus ticketStatus;
    private UUID contractId;
    private City departureCity;
    private City destinationCity;
    private LocalDate departureDate;
    private Connection connection;
    private Contract contract;


    // Constructor
    public Ticket(UUID id, TransportType transportType, float purchasePrice, float salePrice, Timestamp saleDate, TicketStatus ticketStatus, City departureCity, City destinationCity, LocalDate departureDate, Contract contract) {
        this.id = id;
        this.transportType = transportType;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.saleDate = saleDate;
        this.ticketStatus = ticketStatus;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureDate = departureDate;
        this.contract = contract;
    }

    // constructor
    public Ticket() {

    }


    //Getters

    public UUID getId() {
        return id;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public float getPurchasePrice() {
        return purchasePrice;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public Timestamp getSaleDate() {
        return saleDate;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public UUID getContractId() {
        return contractId;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(City departureCity) {
        this.departureCity = departureCity;
    }

    public Contract getContract() {
        return contract;
    }
    //Setters

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public void setSaleDate(Timestamp saleDate) {
        this.saleDate = saleDate;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public City getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(City destinationCity) {
        this.destinationCity = destinationCity;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public void setContractId(UUID contractId) {
        this.contractId = contractId;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

}
