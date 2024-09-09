package UI;

import Services.ClientService;

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
            scanner.nextLine(); // Consume newline

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
        try {
            UUID id = UUID.randomUUID();
            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter phone number: ");
            int phoneNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Use the boolean result
            success = clientService.createClient(id, firstName, lastName, email, phoneNumber);

            if (success) {
                System.out.println("Client added successfully.");
            } else {
                System.out.println("Failed to add client.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid phone number.");
            scanner.nextLine(); // Clear invalid input
        }
    }


}