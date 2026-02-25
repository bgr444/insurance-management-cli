package model.insurance;

import java.time.LocalDate;

import model.user.User;

public class CarInsurance extends Insurance {
    public CarInsurance(String name, double price, LocalDate start, LocalDate end) {
        super(name, price, start, end);
    }

    @Override
    public double calculate(User user) {
        
        if (user.getJobTitle().equalsIgnoreCase("Driver")) {
            return this.getPrice() * 0.90; 
        }
        return this.getPrice() * 1.5; 
    }
}
