package UI;

import Models.Entities.Ticket;
import Models.Enums.TicketStatus;
import Models.Enums.TransportType;
import Services.Implementations.TicketService;
import Services.Interfaces.TicketServiceInterface;
import Utils.TicketValidator;

import java.sql.Timestamp;
import java.util.Scanner;
import java.util.UUID;

public class TicketUI {

    private Scanner scanner;
    private final TicketServiceInterface ticketServie;

    public TicketUI(TicketService ticketServie) {
        this.scanner = new Scanner(System.in);
        this.ticketServie = ticketServie;
    }

    public void indexTicket() {
        try {
            int choice;
            do {

                System.out.printf("#   1. Add new Ticket                       %n");
                System.out.printf("#   2. Edit a Ticket                       %n");
                System.out.printf("#   3. Delete a Ticket                     %n");
                System.out.printf("#   0. Main Menu                         %n");
                System.out.printf("# > Enter a number: ");
                choice = this.scanner.nextInt();
                scanner.nextLine();
                switch (choice) {

                    case 1 -> addTicket();

                    default -> {
                        System.out.printf("---------------------------------------------%n");
                        System.out.printf("|            Please Choose a Number         |%n");
                        System.out.printf("---------------------------------------------%n");
                    }
                }
            } while (choice != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTicket() {
        String transportType;
        float purchasePrice = 0;
        float salePrice = 0;
        String saleDate;
        String ticketStatus;
        UUID contractId;

        try {
            System.out.println("Enter ticket details:");

            do {
                System.out.print("Transport Type (AVION, BUS, TRAIN): ");
                transportType = scanner.nextLine().strip().toUpperCase();
            } while (!TicketValidator.isValidTransportType(transportType));

            do {
                System.out.print("Purchase Price: ");
                try {
                    purchasePrice = Float.parseFloat(scanner.nextLine().strip());
                } catch (NumberFormatException e) {
                    purchasePrice = -1;
                }
            } while (!TicketValidator.isValidPurchasePrice(purchasePrice));

            do {
                System.out.print("Sale Price: ");
                try {
                    salePrice = Float.parseFloat(scanner.nextLine().strip());
                } catch (NumberFormatException e) {
                    salePrice = -1;
                }
            } while (!TicketValidator.isValidSalePrice(salePrice));

            do {
                System.out.print("Sale Date (YYYY-MM-DD HH:MM:SS): ");
                saleDate = scanner.nextLine().strip();
            } while (!TicketValidator.isValidSaleDate(saleDate));

            do {
                System.out.print("Ticket Status (SOLD, CANCELED, PENDING): ");
                ticketStatus = scanner.nextLine().strip().toUpperCase();
            } while (!TicketValidator.isValidTicketStatus(ticketStatus));

            do {
                System.out.print("Contract ID (UUID): ");
                String contractIdStr = scanner.nextLine().strip();
                if (TicketValidator.isValidContractId(contractIdStr)) {
                    contractId = UUID.fromString(contractIdStr);
                } else {
                    contractId = null;
                }
            } while (contractId == null);

            Ticket ticket = new Ticket();
            ticket.setTransportType(TransportType.valueOf(transportType));
            ticket.setPurchasePrice(purchasePrice);
            ticket.setSalePrice(salePrice);
            ticket.setSaleDate(Timestamp.valueOf(saleDate));
            ticket.setTicketStatus(TicketStatus.valueOf(ticketStatus));
            ticket.setContractId(contractId);

            boolean success = ticketServie.addTicket(ticket);

            if (success) {
                System.out.println("Ticket added successfully.");
            } else {
                System.out.println("Failed to add ticket.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
