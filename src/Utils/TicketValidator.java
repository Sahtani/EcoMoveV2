package Utils;

import Models.Enums.TicketStatus;
import Models.Enums.TransportType;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public class TicketValidator {

    public static boolean isValidTransportType(String transportType) {
        try {
            TransportType.valueOf(transportType);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isValidPurchasePrice(float purchasePrice) {
        return purchasePrice >= 0;
    }

    public static boolean isValidSalePrice(float salePrice) {
        return salePrice >= 0;
    }

    public static boolean isValidSaleDate(String saleDate) {
        try {
            LocalDateTime.parse(saleDate, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidTicketStatus(String ticketStatus) {
        try {
            TicketStatus.valueOf(ticketStatus.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isValidContractId(String contractId) {
        try {
            UUID.fromString(contractId);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
