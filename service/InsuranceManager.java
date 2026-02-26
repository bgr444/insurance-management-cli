package service;

import model.account.Account;
import model.insurance.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InsuranceManager {
    private static List<Insurance> insurancePool = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    static {
        insurancePool.add(new HealthInsurance("Standard Health", 2000, LocalDate.now(), LocalDate.now().plusYears(1)));
        insurancePool.add(new CarInsurance("Traffic Insurance", 3500, LocalDate.now(), LocalDate.now().plusYears(1)));
        insurancePool.add(new TravelInsurance("Euro Travel", 1000, LocalDate.now(), LocalDate.now().plusMonths(1)));
        insurancePool.add(new ResidenceInsurance("Home Guard", 2500, LocalDate.now(), LocalDate.now().plusYears(1)));
    }

    public static void displayInsuranceProducts() {
        System.out.println("\n--- Available Insurance Products ---");
        for (int i = 0; i < insurancePool.size(); i++) {
            System.out.println((i + 1) + "- " + insurancePool.get(i).getName() +
                    " (Base Price: " + insurancePool.get(i).getPrice() + ")");
        }
    }

    public static void buyInsurance(Account account, int selection) {
        if (selection > 0 && selection <= insurancePool.size()) {

            Insurance baseProduct = insurancePool.get(selection - 1);
            Insurance selected = baseProduct.copy();

            System.out.println("Selected: " + selected.getName());
            System.out.print("Are you sure you want to buy this? (Y/N): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("Y")) {

                account.addInsurance(selected);
                System.out.println("Transaction successful. Check your profile for the final premium.");
            } else {
                System.out.println("Transaction cancelled.");
            }
        } else {
            System.out.println("Invalid selection!");
        }
    }
}
