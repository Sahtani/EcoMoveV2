package UI;

import Services.ClientService;
import Utils.DataValidator;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class ClientUI {

    private ClientService clientService;
    private Scanner scanner;

    // Constructor to initialize the service and scanner
    public ClientUI() {
        this.scanner = new Scanner(System.in);
        this.clientService = new ClientService();
    }

    // Method to display the menu
    public void displayMenu() {
        while (true) {
            System.out.println("Client Management Menu:");
            System.out.println("1. Add Client");
            System.out.println("2. Update Client");
            System.out.println("3. Delete Client");
            System.out.println("4. List Clients");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addClient();

                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Method to add a new client
    public void addClient() {
        boolean success = false;
        String firstName, lastName, email;
        int phoneNumber = 0;

        try {
            UUID id = UUID.randomUUID();


            do {
                System.out.print("Enter first name: ");
                firstName = scanner.nextLine();
                if (!DataValidator.validateName(firstName)) {
                    System.out.println("Invalid first name. Please try again.");
                }
            } while (!DataValidator.validateName(firstName));


            do {
                System.out.print("Enter last name: ");
                lastName = scanner.nextLine();
                if (!DataValidator.validateName(lastName)) {
                    System.out.println("Invalid last name. Please try again.");
                }
            } while (!DataValidator.validateName(lastName));


            do {
                System.out.print("Enter email: ");
                email = scanner.nextLine();
                if (!DataValidator.validateEmail(email)) {
                    System.out.println("Invalid email. Please try again.");
                }
            } while (!DataValidator.validateEmail(email));


            boolean validPhone = false;
            do {
                try {
                    System.out.print("Enter phone number: ");
                    phoneNumber = scanner.nextInt();
                    scanner.nextLine();
                    if (DataValidator.validatePhoneNumber(phoneNumber)) {
                        validPhone = true;
                    } else {
                        System.out.println("Invalid phone number. Please enter a valid 10-digit number.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid phone number.");
                    scanner.nextLine();
                }
            } while (!validPhone);


            success = clientService.createClient(id, firstName, lastName, email, phoneNumber);

            if (success) {
                System.out.println("Client added successfully.");
            } else {
                System.out.println("Failed to add client.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

}