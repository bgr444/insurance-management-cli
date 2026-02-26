package model.account;

import model.insurance.Insurance;
import constant.AuthenticationStatus;
import exception.AuthException;
import model.user.User;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Account implements Comparable<Account> {
    private User user;
    private ArrayList<Insurance> insurances;
    private AuthenticationStatus log;

    public Account(User user) {
        this.user = user;
        this.insurances = new ArrayList<>();

    }

    public void login(String email, String password) throws AuthException {

        if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
            this.log = AuthenticationStatus.SUCCESS;
        } else {
            this.log = AuthenticationStatus.FAIL;
            throw new AuthException("Invalid email or password.");
        }
    }

    public final void showUserInfo() {
        if (this.log == AuthenticationStatus.SUCCESS) {
            System.out.println("--- Account Details (Logged In) ---");
            System.out.println("--- User Information ---");
            System.out.println("Name      : " + user.getName() + " " + user.getLastName());
            System.out.println("Email     : " + user.getEmail());
            System.out.println("Job       : " + user.getJobTitle());
            System.out.println("Age       : " + user.getAge());
            System.out.println("Last Login: " + user.getLastLoginDate());

            System.out.println("--- Address List ---");
            user.printAddresses();

            System.out.println("--- Insurance Policies ---");
            for (Insurance insurance : insurances) {
                String status = (insurance.getEndDate().isAfter(LocalDate.now())) ? "[ACTIVE]" : "[EXPIRED]";
                System.out.println(status + " Name: " + insurance.getName() +
                        " | End Date: " + insurance.getEndDate() +
                        " | Final Price: " + insurance.getPrice());
            }
        } else {
            System.out.println("User is not logged in!");
        }

    }

    public abstract void addInsurance(Insurance insurance);

    @Override
    public int compareTo(Account other) {
        return this.user.getEmail().compareTo(other.user.getEmail());
    }

    /*
     * Equals ve Hashcode override'larını yazma sebebimiz, treeset sadece comparable
     * implementini kullanarak listesini sıralı hâlde tutuyor
     * yani set olmasına rağmen bir hashleme söz konusu değil(red-black tree
     * yapısına göre listesini tutuyor), bu da sadece compareTo()
     * ile işlem yaptığı anlamına geliyor, aşağıda da accountlarımızı farklı bir
     * collection'da da kullanmak istersek diye diğer collection'larda problem
     * çıkarmayacak
     * email üzerinden eşitlik sorgulamalarını ve hashlemeleri override ederek diğer
     * collection'lara uygun hâle getiriyoruz
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Account))
            return false;
        Account account = (Account) o;
        return user.getEmail().equals(account.user.getEmail());
    }

    @Override
    public int hashCode() {
        return user.getEmail().hashCode();
    }

    public User getUser() {
        return this.user;
    }

    public ArrayList<Insurance> getInsurances() {
        return this.insurances;
    }

    public void setLog(AuthenticationStatus log) {
        this.log = log;
    }

    public AuthenticationStatus getLog() {
        return log;
    }
}
