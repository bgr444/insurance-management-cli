
import model.account.Account;
import model.account.Individual;
import model.address.BusinessAddress;
import model.address.HomeAddress;
import model.account.Enterprise;
import model.user.User;
import model.insurance.CarInsurance;
import model.insurance.HealthInsurance;
import model.insurance.ResidenceInsurance;
import model.insurance.TravelInsurance;
import service.AccountManager;
import service.AddressManager;

import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void prepareData(AccountManager manager) {

        User u1 = new User("Cem", "Duran", "cem@mail.com", "123", "Developer", 28);
        Account acc1 = new Individual(u1);

        User u2 = new User("Ayşe", "Kaya", "ayse@mail.com", "456", "Doctor", 55);
        Account acc2 = new Individual(u2);

        User u3 = new User("Mehmet", "Lojistik", "mehmet@sirket.com", "789", "Driver", 40);
        Account acc3 = new Enterprise(u3);

        User u4 = new User("Selin", "Tech", "selin@tech.com", "321", "Manager", 33);
        Account acc4 = new Enterprise(u4);

        User u5 = new User("Can", "Genç", "can@mail.com", "000", "Student", 17);
        Account acc5 = new Individual(u5);

        User u6 = new User("John", "Doe", "john@mail.com", "password123", "Developer", 25);
        Account acc6 = new Individual(u6);

        AddressManager.addAddress(u1, new HomeAddress("Kadikoy, Istanbul"));
        AddressManager.addAddress(u1, new BusinessAddress("Maslak Tech Plaza, Istanbul"));

        AddressManager.addAddress(u2, new HomeAddress("Cankaya, Ankara"));
        AddressManager.addAddress(u2, new BusinessAddress("City Hospital, Ankara"));

        AddressManager.addAddress(u3, new BusinessAddress("Logistics Center, Kocaeli"));
        
        AddressManager.addAddress(u4, new HomeAddress("Nilufer, Bursa"));
        AddressManager.addAddress(u4, new BusinessAddress("Industrial Zone, Bursa"));

        AddressManager.addAddress(u5, new HomeAddress("Besiktas, Istanbul (Student Dorm)"));

        AddressManager.addAddress(u6, new HomeAddress("New York, USA"));
        AddressManager.addAddress(u6, new BusinessAddress("Silicon Valley, CA"));

        manager.addAccount(acc1);
        manager.addAccount(acc2);
        manager.addAccount(acc3);
        manager.addAccount(acc4);
        manager.addAccount(acc5);
        manager.addAccount(acc6);
    }

    public static void main(String[] args) {
        
        AccountManager manager = new AccountManager();
        prepareData(manager);
        Scanner scanner = new Scanner(System.in);

        LocalDate today = LocalDate.now();
        LocalDate nextYear = today.plusYears(1);

        System.out.println("--- Welcome to Insurance System ---");

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Account loggedInAccount = manager.login(email, password);

        if (loggedInAccount != null) {
            boolean exit = false;
            while (!exit) {
                System.out.println("\n1- Show My Information");
                System.out.println("2- Add Adress");
                System.out.println("3- Add Health Insurance");
                System.out.println("4- Add Traffic Insurance");
                System.out.println("5- Add Travel Insurance");
                System.out.println("6- Add Residence Insurance");
                System.out.println("0- Logout");
                System.out.print("Your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        loggedInAccount.showUserInfo();
                        break;
                    case 2:
                        System.out.println("1- Home Address\n2- Business Address");
                        int addrType = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter address details: ");
                        String addrDetail = scanner.nextLine();

                        if (addrType == 1) {
                            AddressManager.addAddress(loggedInAccount.getUser(), new HomeAddress(addrDetail));
                        } else {
                            AddressManager.addAddress(loggedInAccount.getUser(), new BusinessAddress(addrDetail));
                        }
                        System.out.println("Address added.");
                        break;

                    case 3:
                        HealthInsurance health = new HealthInsurance("General Health", 2000, today, nextYear);
                        loggedInAccount.addInsurance(health);
                        System.out.println("Insurance added successfully at calculated price.");
                        break;
                    case 4:
                        CarInsurance car = new CarInsurance("Traffic Insurance", 1500, LocalDate.now(),
                                LocalDate.now().plusYears(1));
                        loggedInAccount.addInsurance(car);
                        System.out.println("Car insurance added.");
                        break;
                    case 5:
                        TravelInsurance trav = new TravelInsurance("Travel Insurance", 1000, LocalDate.now(),
                                LocalDate.now().plusMonths(1));
                        loggedInAccount.addInsurance(trav);
                        System.out.println("Travel insurance added.");
                        break;

                    case 6:
                        ResidenceInsurance res = new ResidenceInsurance("Residence Insurance", 2500,
                                LocalDate.now(), LocalDate.now().plusYears(1));
                        loggedInAccount.addInsurance(res);
                        System.out.println("Residence insurance added.");
                        break;
                    case 0:
                        exit = true;
                        System.out.println("Logging out...");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } else {
            System.out.println("Access Denied. Program terminated.");
        }

        scanner.close();
    }
}
