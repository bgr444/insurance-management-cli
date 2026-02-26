
import model.account.Account;
import model.account.Individual;
import model.address.BusinessAddress;
import model.address.HomeAddress;
import model.account.Enterprise;
import model.user.User;
import service.AccountManager;
import service.AddressManager;
import service.InsuranceManager;
import java.util.Scanner;
import constant.AuthenticationStatus;


public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static AccountManager accountManager = new AccountManager();

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
        prepareData(accountManager);
        run();
    }

    public static void run() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n--- INSURANCE MANAGEMENT SYSTEM ---");
            System.out.println("1- LogIn\n2- Create Account\n0- Exit");
            System.out.print("Selection: ");
            String pref = scanner.nextLine();

            switch (pref) {
                case "1" -> loginAction(); 
                case "2" -> createAccountMenu(); 
                case "0" -> {
                    System.out.println("Goodbye!");
                    isRunning = false;
                }
                default -> System.out.println("Invalid option! Try again.");
            }
        }
    }

    public static void createAccountMenu() {
        System.out.println("\n--- Create New Account ---");
        System.out.println("1- Individual Account");
        System.out.println("2- Enterprise Account");
        System.out.println("0- Cancel");
        System.out.print("Selection: ");
        String type = scanner.nextLine();

        if (type.equals("0"))
            return;

        
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Surname: ");
        String surname = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Profession: ");
        String profession = scanner.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        
        User newUser = new User(name, surname, email, password, profession, age);
        Account newAccount = null;

        if (type.equals("1")) {
            newAccount = new Individual(newUser);
        } else if (type.equals("2")) {
            newAccount = new Enterprise(newUser);
        }

        if (newAccount != null) {
            accountManager.addAccount(newAccount);
            System.out.println("Account created successfully! You can now log in.");
        } else {
            System.out.println("Invalid selection. Account creation failed.");
        }
    }

    public static void loginAction() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Account loggedInAccount = accountManager.login(email, password);

        if (loggedInAccount != null) {
            System.out.println("Login Successful");

            accountMenu(loggedInAccount);
        } else {
            System.out.println("Invalid email or password!");
        }
    }

    public static void accountMenu(Account account) {
        boolean inAccount = true;
        while (inAccount) {
            System.out.println("\n--- WELCOME " + account.getUser().getName().toUpperCase() + " ---");
            System.out.println("1- Insurance Operations");
            System.out.println("2- Address Operations");
            System.out.println("3- My Profile");
            System.out.println("0- Logout");
            System.out.print("Enter: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> insuranceSubMenu(account);
                case "2" -> addressSubMenu(account);
                case "3" -> account.showUserInfo();
                case "0" -> {
                    System.out.println("Logging out...");
                    account.setLog(AuthenticationStatus.FAIL);
                    inAccount = false;
                }
                default -> System.out.println("Invalid selection!");
            }
        }
    }

    public static void insuranceSubMenu(Account account) {

        InsuranceManager.displayInsuranceProducts();

        System.out.println("0- Back to Menu");
        System.out.print("Select a product to buy: ");

        String choiceStr = scanner.nextLine();
        int choice;

        try {
            choice = Integer.parseInt(choiceStr);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number!");
            return;
        }

        if (choice == 0)
            return;
        InsuranceManager.buyInsurance(account, choice);
    }

    public static void addressSubMenu(Account account) {
        System.out.println("\n--- Address Operations ---");
        System.out.println("1- Add Home Address");
        System.out.println("2- Add Business Address");
        System.out.println("3- Remove Address");
        System.out.println("0- Back");
        System.out.print("Selection: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> {
                System.out.print("Enter home address: ");
                AddressManager.addAddress(account.getUser(), new HomeAddress(scanner.nextLine()));
                System.out.println("Home address added.");
            }
            case "2" -> {
                System.out.print("Enter business address: ");
                AddressManager.addAddress(account.getUser(), new BusinessAddress(scanner.nextLine()));
                System.out.println("Business address added.");
            }
            case "3" -> {
                account.getUser().printAddresses();

                var addresses = account.getUser().getAddresses();

                if (addresses != null && !addresses.isEmpty()) {
                    System.out.print("Select address number to remove: ");

                    try {
                        int index = Integer.parseInt(scanner.nextLine()) - 1;

                        if (index >= 0 && index < addresses.size()) {

                            AddressManager.removeAddress(account.getUser(), addresses.get(index));
                            System.out.println("Address removed successfully.");
                        } else {
                            System.out.println("Invalid selection! Please enter a number from the list.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter a valid number!");
                    }
                }
            }
            case "0" -> {
            }
            default -> System.out.println("Invalid selection!");
        }
    }
}
