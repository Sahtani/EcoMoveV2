package UI;

import Models.Entities.Ticket;
import Models.Enums.TicketStatus;
import Models.Enums.TransportType;
import Services.Implementations.TicketServie;

import java.sql.Timestamp;
import java.util.Scanner;
import java.util.UUID;

public class TicketUI {

    private Scanner scanner;
    private final TicketServie ticketServie    ;

    public TicketUI(TicketServie ticketServie) {
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
            }while(choice != 0);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addTicket() {
        try {
            System.out.println("Enter ticket details:");

            System.out.print("Transport Type (AVION, BUS, TRAIN): ");
            String transportType = scanner.nextLine().strip().toUpperCase();

            System.out.print("Purchase Price: ");
            float purchasePrice = Float.parseFloat(scanner.nextLine().strip());

            System.out.print("Sale Price: ");
            float salePrice = Float.parseFloat(scanner.nextLine().strip());

            System.out.print("Sale Date (YYYY-MM-DD HH:MM:SS): ");
            String saleDate = scanner.nextLine().strip();

            System.out.print("Ticket Status ( SOLD,CANCELED,PENDING): ");
            String ticketStatus = scanner.nextLine().strip().toLowerCase();

            System.out.print("Contract ID (UUID): ");
            UUID contractId = UUID.fromString(scanner.nextLine().strip());

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
