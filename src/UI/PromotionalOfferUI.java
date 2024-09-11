package UI;

import Models.Entities.PromotionalOffer;
import Models.Enums.DiscountType;
import Models.Enums.OfferStatus;
import Services.Implementations.PromotionalOfferService;
import Services.Implementations.TicketService;
import Services.Interfaces.ClientServiceInterface;
import Services.Interfaces.PromoServiceInterface;
import Services.Interfaces.TicketServiceInterface;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class PromotionalOfferUI {


    private Scanner scanner;
    private PromoServiceInterface promotionalOfferService;


    public PromotionalOfferUI(PromoServiceInterface promotionalOfferService) {
        this.scanner = new Scanner(System.in);
        this.promotionalOfferService = promotionalOfferService;
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

            List<PromotionalOffer> resultPromotions = promotionalOfferService.getAllPromotions();

            for (PromotionalOffer offer : resultPromotions) {
                System.out.printf("# %-36s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-36s | %-20s #%n",
                        offer.getId(),
                        offer.getOfferName(),
                        offer.getDescription(),
                        offer.getStartDate() != null ? offer.getStartDate().toString() : "N/A",
                        offer.getEndDate() != null ? offer.getEndDate().toString() : "Indefinite",
                        offer.getDiscountType(),
                        offer.getConditions(),
                        offer.getContractId(),
                        offer.getOfferStatus());
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
        DiscountType discountType = DiscountType.valueOf(scanner.nextLine().toUpperCase());
        System.out.println("Enter Reduction Value: ");
        float reductionValue = (float) Double.parseDouble(scanner.nextLine());
        System.out.println("Enter Conditions: ");
        String conditions = scanner.nextLine();
        System.out.println("Enter Promotion Status: ");
        OfferStatus offerStatus = OfferStatus.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Enter Contract ID (UUID): ");
        UUID contractId = UUID.fromString(scanner.nextLine().strip());

        PromotionalOffer promotion = new PromotionalOffer();
        promotion.setOfferName(offerName);
        promotion.setDescription(description);
        promotion.setStartDate(startDate);
        promotion.setEndDate(endDate);
        promotion.setDiscountType(discountType);
        promotion.setReductionValue((float) reductionValue);
        promotion.setConditions(conditions);
        promotion.setOfferStatus(offerStatus);
        promotion.setContractId(contractId);

        promotionalOfferService.addPromotion(promotion);
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
        System.out.println("Enter Updated Reduction Value: ");
        float reductionValue;
        try {
            reductionValue = (float) Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid reduction value. Please enter a valid number.");
            return;
        }
        String conditions = scanner.nextLine();
        System.out.println("Enter Updated Promotion Status: ");
        OfferStatus offerStatus = OfferStatus.valueOf(scanner.nextLine());

        System.out.print("Enter Contract ID (UUID): ");
        UUID contractId = UUID.fromString(scanner.nextLine().strip());

        PromotionalOffer promotion = new PromotionalOffer();
        promotion.setOfferName(offerName);
        promotion.setDescription(description);
        promotion.setStartDate(startDate);
        promotion.setEndDate(endDate);
        promotion.setDiscountType(discountType);
        promotion.setReductionValue(reductionValue);
        promotion.setConditions(conditions);
        promotion.setOfferStatus(offerStatus);
        promotion.setContractId(contractId);

        promotionalOfferService.updatePromotion(promotion);

        System.out.println("Promotion updated successfully.");
    }

    // Method to delete a promotion
    private void deletePromotion() {
        System.out.println("Enter the ID of the promotion to delete: ");
        UUID promotionId = UUID.fromString(scanner.nextLine().strip());
        scanner.nextLine();

        promotionalOfferService.deletePromotion(promotionId);
        System.out.println("Promotion deleted successfully.");
    }


}
