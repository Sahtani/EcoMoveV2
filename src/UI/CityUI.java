package UI;

import Dao.Implementations.CityDao;
import Models.Entities.City;
import Services.Implementations.CityService;
import Services.Interfaces.ICityService;

import java.util.List;
import java.util.Scanner;

public class CityUI {
    private ICityService cityService;
    private Scanner scanner;

    public CityUI(ICityService cityService) {
        this.cityService = cityService;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("City Management Menu:");

            System.out.println("1. List Cities");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listCities();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }






    public void listCities() {
        List<City> cities = cityService.getAllCities();
        if (cities.isEmpty()) {
            System.out.println("No cities found.");
        } else {
            System.out.printf("%-10s%-20s%n", "ID", "Name");
            System.out.println("-------------------------------");
            for (City city : cities) {
                System.out.printf("%-10d%-20s%n", city.getId(), city.getCityName());
            }
        }
    }

    public static void main(String[] args) {

        CityService cityService = new CityService(new CityDao());
        CityUI cityUI = new CityUI(cityService);
        cityUI.displayMenu();
    }
}
