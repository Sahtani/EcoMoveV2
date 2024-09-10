package UI;

import Models.Enums.DiscountType;
import Models.Enums.OfferStatus;
import Services.Implementations.PromotionalOfferService;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class PromotionalOfferUI {


        private Scanner scanner;
        private PromotionalOfferService promotionalOfferService;

        public PromotionalOfferUI() {
            this.scanner = new Scanner(System.in);
            this.promotionalOfferService = new PromotionalOfferService();
        }

        // Main menu method
        public void start() {
            int choice;
            do {
                System.out.println("--------------------------------------------------");
                System.out.println("                Promotion Management              ");
                System.out.println("--------------------------------------------------");
                System.out.println("1. View All Promotions");
                System.out.println("2. Add a New Promotion");
                System.out.println("3. Edit a Promotion");
                System.out.println("4. Delete a Promotion");
                System.out.println("0. Exit");
                System.out.print("Please enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        promotionsList();
                        break;
                    case 2:
                        addPromotion();
                        break;
                    case 3:
                        updatePromotion();
                        break;
                    case 4:
                        deletePromotion();
                        break;
                    case 0:
                        System.out.println("Exiting promotion management.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } while (choice != 0);
        }

        // Method to view all promotions
        public void promotionsList() {
            try {
                System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
                System.out.printf("# %-36s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-36s | %-36s #%n",
                        "UUID id", "Offer Name", "Description", "Start Date", "End Date", "Discount Type", "Conditions", "Contract ID", "Offer Status");
                System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------%n");

                ResultSet resultPromotions = promotionalOfferService.getAllPromotions();
                while (resultPromotions.next()) {
                    System.out.printf("# %-36s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-36s | %-20s #%n",
                            resultPromotions.getString("id"),
                            resultPromotions.getString("offername"),
                            resultPromotions.getString("description"),
                            resultPromotions.getDate("startdate").toString(),
                            resultPromotions.getDate("enddate") != null ? resultPromotions.getDate("enddate").toString() : "Indefinite",
                            resultPromotions.getString("discounttype"),
                            resultPromotions.getString("conditions"),
                            resultPromotions.getString("contractid"),
                            resultPromotions.getString("offerstatus"));
                }

                System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------%n");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        // Method to add a new promotion
        public void addPromotion() {
            System.out.println("Enter Promotion Name: ");
            String offerName = scanner.nextLine();
            System.out.println("Enter Description: ");
            String description = scanner.nextLine();
            System.out.println("Enter Start Date (YYYY-MM-DD): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());
            System.out.println("Enter End Date (YYYY-MM-DD): ");
            LocalDate endDate = LocalDate.parse(scanner.nextLine());
            System.out.println("Enter Discount Type: ");
            DiscountType discountType = DiscountType.valueOf(scanner.nextLine());
            System.out.println("Enter Conditions: ");
            String conditions = scanner.nextLine();
            System.out.println("Enter Promotion Status: ");
            OfferStatus offerStatus = OfferStatus.valueOf(scanner.nextLine());
            System.out.print("Enter Contract ID (UUID): ");
            UUID contractId = UUID.fromString(scanner.nextLine().strip());

            promotionalOfferService.createPromotionalOffer(offerName,description,startDate,endDate, discountType, conditions,offerStatus,contractId);
            System.out.println("Promotion added successfully.");


            }

        // Method to update an existing promotion
        private void updatePromotion() {
            System.out.println("Enter the ID of the promotion to update: ");
            UUID promotionId = UUID.fromString(scanner.nextLine().strip());

            scanner.nextLine(); // Consume newline

            System.out.println("Enter Updated Promotion Name: ");
            String offerName = scanner.nextLine();
            System.out.println("Enter Updated Description: ");
            String description = scanner.nextLine();
            System.out.println("Enter Updated Start Date (YYYY-MM-DD): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());
            System.out.println("Enter Updated End Date (YYYY-MM-DD): ");
            LocalDate endDate = LocalDate.parse(scanner.nextLine());
            System.out.println("Enter Updated Discount Type: ");
            DiscountType discountType = DiscountType.valueOf(scanner.nextLine());
            System.out.println("Enter Updated Conditions: ");
            String conditions = scanner.nextLine();
            System.out.println("Enter Updated Promotion Status: ");
            OfferStatus offerStatus = OfferStatus.valueOf(scanner.nextLine());

            System.out.print("Enter Contract ID (UUID): ");
            UUID contractId = UUID.fromString(scanner.nextLine().strip());


            promotionalOfferService.updatePromotionalOffer(promotionId,offerName,description,startDate,endDate, discountType, conditions,offerStatus, contractId);

            System.out.println("Promotion updated successfully.");
        }

        // Method to delete a promotion
        private void deletePromotion() {
            System.out.println("Enter the ID of the promotion to delete: ");
            UUID promotionId = UUID.fromString(scanner.nextLine().strip());
            scanner.nextLine();

            promotionalOfferService.deletePromotionalOffer(promotionId);
            System.out.println("Promotion deleted successfully.");
        }


}
