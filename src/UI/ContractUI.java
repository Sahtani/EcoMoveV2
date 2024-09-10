package UI;

import Models.Entities.Contract;
import Models.Entities.Ticket;
import Services.Implementations.ContractService;
import Models.Enums.ContractStatus;
import Services.Interfaces.ContractServiceInterface;
import Utils.DataValidator;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class ContractUI {
    private final Scanner scanner ;
    private final ContractServiceInterface contractService ;

    public ContractUI(ContractServiceInterface contractService) {
        this.contractService = contractService;
        this.scanner =  new Scanner(System.in);
    }


    public void contractMenu() {
        int choice;
        do {
            contractList();
            System.out.println("---------- Contract Menu ----------");
            System.out.println("1. Add Contract");
            System.out.println("2. Update Contract");
            System.out.println("3. Delete Contract");
            System.out.println("4. List All Contracts");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addContract();
                case 2 -> updateContract();
                case 3 -> deleteContract();
                case 4 -> contractList();
                case 0 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    // Method to add a new contract
    public void addContract() {
        try {
            System.out.print("Enter Partner ID (UUID): ");
            UUID partnerId = UUID.fromString(scanner.nextLine().strip());

            LocalDate startDate = DataValidator.promptForValidDate("Enter Start Date (YYYY-MM-DD): ", scanner);

            LocalDate endDate;
            do {
                endDate = DataValidator.promptForValidDate("Enter End Date (YYYY-MM-DD) or leave empty if not applicable: ", scanner);
                if (endDate != null && !DataValidator.validateDate(startDate, endDate)) {
                    System.out.println("Start date must be before end date.");
                }
            } while (endDate != null && !DataValidator.validateDate(startDate, endDate));

            System.out.print("Enter Special Rate: ");
            float specialRate = Float.parseFloat(scanner.nextLine().strip());

            System.out.print("Enter Agreement Conditions: ");
            String agreementConditions = scanner.nextLine().strip();

            System.out.print("Is the Contract Renewable? (true/false): ");
            boolean renewable = Boolean.parseBoolean(scanner.nextLine().strip());

            System.out.print("Enter Contract Status (ongoing, completed, suspended): ");
            ContractStatus contractStatus = ContractStatus.valueOf(scanner.nextLine().strip().toLowerCase());

            UUID contractId = UUID.randomUUID();
            Contract contract = new Contract(contractId, partnerId, startDate, endDate, specialRate, agreementConditions, renewable, contractStatus);
            String result = String.valueOf(contractService.addContract(contract));

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Method to update a contract
    public void updateContract() {
        try {
            System.out.print("Enter Contract ID (UUID): ");
            UUID id = UUID.fromString(scanner.nextLine().strip());

            System.out.print("Enter New Start Date (YYYY-MM-DD): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine().strip());

            System.out.print("Enter New End Date (YYYY-MM-DD) or leave empty if not applicable: ");
            String endDateInput = scanner.nextLine().strip();
            LocalDate endDate = endDateInput.isEmpty() ? null : LocalDate.parse(endDateInput);

            System.out.print("Enter New Special Rate: ");
            float specialRate = Float.parseFloat(scanner.nextLine().strip());

            System.out.print("Enter New Agreement Conditions: ");
            String agreementConditions = scanner.nextLine().strip();

            System.out.print("Is the Contract Renewable? (true/false): ");
            boolean renewable = Boolean.parseBoolean(scanner.nextLine().strip());

            System.out.print("Enter New Contract Status (ongoing, completed, suspended): ");
            ContractStatus contractStatus = ContractStatus.valueOf(scanner.nextLine().strip().toUpperCase());



            Contract contract = new Contract();
            contract.setId(id);
            contract.setStartDate(startDate);
            contract.setEndDate(endDate);
            contract.setSpecialRate(specialRate);
            contract.setAgreementConditions(agreementConditions);
            contract.setRenewable(renewable);
            contract.setContractStatus(contractStatus);

            boolean success = contractService.updateContract(contract);

            if (success) {
                System.out.println("Contract updated successfully.");
            } else {
                System.out.println("Failed to update contract.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to delete a contract
    public void deleteContract() {
        try {
            System.out.print("Enter Contract ID (UUID): ");
            UUID id = UUID.fromString(scanner.nextLine().strip());

            boolean success;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Method to list all contracts
    public void contractList() {
        try {
            System.out.printf("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
            System.out.printf("# %-36s | %-36s | %-10s | %-10s | %-12s | %-30s | %-10s | %-18s #%n",
                    "Contract ID", "Partner ID", "Start Date", "End Date", "Special Rate", "Agreement Conditions", "Renewable", "Status");
            System.out.printf("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");

            ResultSet resultContracts = (ResultSet) contractService.getAllContracts();

            while (resultContracts.next()) {
                System.out.printf("# %-36s | %-36s | %-10s | %-10s | %-12.2f | %-30s | %-10b | %-18s #%n",
                        resultContracts.getString("id"),
                        resultContracts.getString("partner_id"),
                        resultContracts.getDate("start_date").toLocalDate(),
                        resultContracts.getDate("end_date") != null ? resultContracts.getDate("end_date").toLocalDate() : "N/A",
                        resultContracts.getFloat("special_rate"),
                        resultContracts.getString("agreement_conditions"),
                        resultContracts.getBoolean("renewable"),
                        resultContracts.getString("contract_status"));
            }

            System.out.printf("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}