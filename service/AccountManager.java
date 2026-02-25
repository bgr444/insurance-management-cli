package service;

import java.util.TreeSet;

import exception.AuthException;
import model.account.Account;

public class AccountManager {

    private TreeSet<Account> accounts;

    public AccountManager() {
        this.accounts = new TreeSet<>();
    }

    public Account login(String email, String password) {
        for (Account account : accounts) {
            if (account.getUser().getEmail().equals(email)) {
                try {
                    // Her hesabın kendi içindeki login metodunu çağırıyoruz
                    // Eğer email tutuyorsa ama şifre yanlışsa AuthException fırlatacak
                    account.login(email, password);

                    // Eğer login başarılıysa (exception fırlamadıysa) hesabı döndür
                    System.out.println("Login successful! Welcome, " + account.getUser().getName());
                    return account;
                } catch (AuthException e) {
                    // Şifre yanlışsa veya email tutmuyorsa buraya düşer
                    // Ancak döngü devam etmeli çünkü başka bir hesapta o email olabilir
                    // Sadece email eşleşip şifre yanlışsa mesajı yazdırabiliriz
                    if (account.getUser().getEmail().equals(email)) {
                        System.out.println("Login failed: " + e.getMessage());
                        return null;
                    }
                }
            }
        }
        // Hiçbir hesapla eşleşmediyse
        System.out.println("Account not found with this email.");
        return null;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }
}
