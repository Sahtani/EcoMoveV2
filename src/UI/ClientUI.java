package UI;

import Models.Entities.Client;
import Services.Implementations.ClientService;
import Services.Interfaces.ClientServiceInterface;
import Utils.DataValidator;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class ClientUI {

    private ClientServiceInterface clientService;
    private Scanner scanner;

    // Constructor

    public ClientUI(ClientServiceInterface clientService) {
        this.scanner = new Scanner(System.in);
        this.clientService = clientService;
    }


    // Method to display the menu
    public void displayMenu() {
        while (true) {
            System.out.println("Client Management Menu:");
            System.out.println("1. Add Client");
            System.out.println("2. Update Client");
            System.out.println("3. Delete Client");
            System.out.println("4. Log In Client\"");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addClient();

                    break;
                case 4:
                    loginClient();

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
        String firstName, lastName, email;
        int phoneNumber = 0;

        try {
            UUID id = UUID.randomUUID();

            do {
                System.out.print("Enter first name: ");
                firstName = scanner.nextLine();
            } while (!DataValidator.validateName(firstName));

            do {
                System.out.print("Enter last name: ");
                lastName = scanner.nextLine();
            } while (!DataValidator.validateName(lastName));

            do {
                System.out.print("Enter email: ");
                email = scanner.nextLine();
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

            Client client = new Client();
            client.setId(id);
            client.setFirstName(firstName);
            client.setLastName(lastName);
            client.setEmail(email);
            client.setPhoneNumber(phoneNumber);

            Optional<Client> existingClient = clientService.loginClient(firstName, lastName, email);
            if (existingClient.isPresent()) {
                System.out.println("Client already exists, try to log in.");
                return;
            } else {
                boolean success = clientService.addClient(client);
                if (success) {
                    System.out.println("Client added successfully.");
                } else {
                    System.out.println("Failed to add client.");
                }
            }
        } catch (Exception e) {
            System.out.println("error : " + e.getMessage());
        }
    }

            //login client :
    public void loginClient(){

        String firstName, lastName, email;
        try {
            do {
                System.out.print("Enter first name: ");
                firstName = scanner.nextLine();
                if (!DataValidator.validateName(firstName)) {
                    System.out.println("Invalid first name. Please try again.");
                }


            }while (!DataValidator.validateName(firstName));

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

            Optional<Client> client = clientService.loginClient(firstName, lastName, email);

            if (client.isPresent()) {
                System.out.println("Client logged in successfully: ");
            } else {
                System.out.println("Login failed. Client not found.");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}