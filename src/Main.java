//import config.Db;
//import models.Partner;
//import models.TransportType;
//import models.PartnerStatus;
//
//
//import java.sql.Connection;
//import java.util.Scanner;
//import java.util.UUID;
//public class Main {
//
//    public static void main(String[] args) {
//        mainMenu();
//
//
//    public static void mainMenu() {
//        LibrarianController librarianController = new LibrarianController();
//        Scanner scanner = new Scanner(System.in);
//        int choice = 0;
//        do {
//            System.out.printf("---------------------------------------------%n");
//            System.out.printf("|          Library Management System        |%n");
//            System.out.printf("---------------------------------------------%n");
//            System.out.printf("|                   Welcome                 |%n");
//            System.out.printf("---------------------------------------------%n");
//            System.out.printf("#      1. Login                             #%n");
//            System.out.printf("#      2. Register                          #%n");
//            System.out.printf("#      0. Exit                              #%n");
//            System.out.printf("---------------------------------------------%n");
//            System.out.printf("# Enter Number >>> ");
//            choice = scanner.nextInt();
//            scanner.nextLine();
//            switch (choice) {
//                case 1 -> librarianController.login();
//                case 2 -> librarianController.register();
//                case 0 -> {
//                    System.out.printf(Colors.YELLOW + "---------------------------------------------%n");
//                    System.out.printf("|                    Exit                   |%n");
//                    System.out.printf("---------------------------------------------%n" + Colors.RESET_COLOR);
//                    System.exit(0);
//                }
//                default -> {
//                    System.out.printf(Colors.RED + "---------------------------------------------%n");
//                    System.out.printf("|           Please Choose a Number          |%n");
//                    System.out.printf("---------------------------------------------%n" + Colors.RESET_COLOR);
//                }
//            }
//        }while (choice != 0);
//    }
//    public static void main(String[] args) {
//        Db db = Db.getInstance("EcoMove","postgres","soumia");
//        Connection connection = db.getConnection();
//
//
////        UUID partnerId = UUID.randomUUID();
////
////                Scanner scanner = new Scanner(System.in);
////
////                // Collect partner data from the user
////                System.out.println("Enter company name:");
////                String companyName = scanner.nextLine();
////
////                System.out.println("Enter commercial contact:");
////                String commercialContact = scanner.nextLine();
////
////                System.out.println("Enter transport type (AVION, BUS, TRAIN):");
////                String transportTypeInput = scanner.nextLine();
////                TransportType transportType = TransportType.valueOf(transportTypeInput);
////
////                System.out.println("Enter geographical zone:");
////                String geographicalZone = scanner.nextLine();
////
////                System.out.println("Enter special conditions:");
////                String specialConditions = scanner.nextLine();
////
////                System.out.println("Enter partner status (active, inactive, suspended):");
////                String partnerStatusInput = scanner.nextLine();
////                PartnerStatus partnerStatus = PartnerStatus.valueOf(partnerStatusInput.toUpperCase());
//
//
//                // Add the partner using the DAO
////                Partner.addPartner(partnerId, companyName, commercialContact, transportType,
////                        geographicalZone, speci



import Dao.Implementations.ClientDAO;
import Dao.Implementations.TicketDAO;
import Dao.Interfaces.ClientDaoInterface;
import Dao.Interfaces.TicketDaoInterface;
import Services.Implementations.ClientService;
import Services.Implementations.TicketServie;
import UI.ClientUI;
import UI.ContractUI;
import UI.PartnerUI;
import UI.TicketUI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {

        PartnerUI partnerUI = new PartnerUI();
        ContractUI contractUI = new ContractUI();
        ClientDaoInterface clientDAO = new ClientDAO();
        ClientService clientService = new ClientService(clientDAO);
        TicketDaoInterface ticketDao = new TicketDAO() ;
        TicketServie ticketServie = new TicketServie(ticketDao) ;
        TicketUI ticketUI = new TicketUI(ticketServie) ;



        ClientUI clientUI = new ClientUI(clientService);


        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.printf("---------------------------------------------%n");
            System.out.printf("|          EcoMove System                   |%n");
            System.out.printf("---------------------------------------------%n");
            System.out.printf("|                   Welcome                 |%n");
            System.out.printf("---------------------------------------------%n");
            System.out.printf("#      1. Gestion des partenariats                            #%n");
            System.out.printf("#      2. Gestion des contrats :                         #%n");
            System.out.printf("#      3. Gestion des offres promotionnelles:                            #%n");
            System.out.printf("#      4. Gestion des Billets:                            #%n");
            System.out.printf("---------------------------------------------%n");
            System.out.printf("# Enter Number >>> ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {

                  case 1 ->  partnerUI.partnerMenu();
                  case 2 -> contractUI.contractMenu();
                  case 3 -> clientUI.displayMenu();
                  case 4 -> ticketUI.indexTicket();

                case 0 -> {
                    System.out.printf("---------------------------------------------%n");
                    System.out.printf("|                    Exit                   |%n");
                    System.out.printf("---------------------------------------------%n");
                    System.exit(0);
                }
                default -> {
                    System.out.printf( "---------------------------------------------%n");
                    System.out.printf("|           Please Choose a Number          |%n");
                    System.out.printf("---------------------------------------------%n");
                }
            }
        }while (choice != 0);
    }
}
