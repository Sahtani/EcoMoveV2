package UI;

import Services.Implementations.PartnerService;
import Models.Enums.PartnerStatus;
import Models.Enums.TransportType;

import java.sql.ResultSet;
import java.util.Scanner;
import java.util.UUID;

public class PartnerUI {
    private final Scanner scanner = new Scanner(System.in);
    private final PartnerService partnerService = new PartnerService();

    public void partnerMenu() {
        int choice;
        do {
            allPartners();
            System.out.println("---------- Partner Menu ----------");
            System.out.println("1. Add Partner");
            System.out.println("2. Update Partner");
            System.out.println("3. Delete Partner");
            System.out.println("4. List All Partners");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addPartner();
                case 2 -> updatePartner();
                case 3 -> deletePartner();
                case 4 -> allPartners();
                case 0 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    // Method to add a new partner
    public void addPartner() {
        System.out.print("Enter Company Name: ");
        String companyName = scanner.nextLine().strip();

        System.out.print("Enter Commercial Contact: ");
        String commercialContact = scanner.nextLine().strip();

        System.out.print("Enter Transport Type (AVION, BUS, TRAIN): ");
        TransportType transportType = TransportType.valueOf(scanner.nextLine().strip().toUpperCase());

        System.out.print("Enter Geographical Zone: ");
        String geographicalZone = scanner.nextLine().strip();

        System.out.print("Enter Special Conditions: ");
        String specialConditions = scanner.nextLine().strip();

        System.out.print("Enter Partner Status (ACTIVE, INACTIVE, SUSPENDED): ");
        PartnerStatus partnerStatus = PartnerStatus.valueOf(scanner.nextLine().strip().toUpperCase());

        UUID partnerId = UUID.randomUUID();
        boolean success = partnerService.addPartner(partnerId, companyName, commercialContact, geographicalZone ,specialConditions , transportType, partnerStatus);

    }

    // Method to update a partner
    public void updatePartner() {
        System.out.print("Enter Partner ID: ");
        UUID id = UUID.fromString(scanner.nextLine().strip());

        System.out.print("Enter New Company Name: ");
        String companyName = scanner.nextLine().strip();

        System.out.print("Enter New Commercial Contact: ");
        String commercialContact = scanner.nextLine().strip();

        System.out.print("Enter New Transport Type (AVION, BUS, TRAIN): ");
        TransportType transportType = TransportType.valueOf(scanner.nextLine().strip().toUpperCase());

        System.out.print("Enter New Geographical Zone: ");
        String geographicalZone = scanner.nextLine().strip();

        System.out.print("Enter New Special Conditions: ");
        String specialConditions = scanner.nextLine().strip();

        System.out.print("Enter New Partner Status (ACTIVE, INACTIVE, SUSPENDED): ");
        PartnerStatus partnerStatus = PartnerStatus.valueOf(scanner.nextLine().strip().toUpperCase());

        boolean success = partnerService.updatePartner(id, companyName, commercialContact,geographicalZone, specialConditions, transportType, partnerStatus);

        if (success) {
            System.out.println("Partner updated successfully.");
        } else {
            System.out.println("Failed to update partner.");
        }
    }

    // Method to delete a partner
    public void deletePartner() {
        System.out.print("Enter Partner ID: ");
        UUID id = UUID.fromString(scanner.nextLine().strip());

        boolean success = partnerService.deletePartner(id);

        if (success) {
            System.out.println("Partner deleted successfully.");
        } else {
            System.out.println("Failed to delete partner.");
        }
    }

    // display all partners :
    public void allPartners() {
        try {
            System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------%n");
            System.out.printf("| %-36s | %-20s | %-20s | %-20s | %-20s | %-10s |%n",
                    "UUID", "Company Name", "Commercial Contact", "Geographical Zone", "Transport Type", "Status");
            System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------%n");

            ResultSet resultPartners = partnerService.partnerList();

            while (resultPartners.next()) {
                System.out.printf("| %-36s | %-20s | %-20s | %-20s | %-20s | %-10s |%n",
                        resultPartners.getString("id"),
                        resultPartners.getString("company_name"),
                        resultPartners.getString("commercial_contact"),
                        resultPartners.getString("geographical_zone"),
                        resultPartners.getString("transport_type"),
                        resultPartners.getString("partner_status"));
            }

            System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------%n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
