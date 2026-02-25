package model.insurance;

import java.time.LocalDate;

import model.user.User;

public class HealthInsurance extends Insurance {
    public HealthInsurance(String name, double price, LocalDate startDate, LocalDate endDate) {
        super(name, price, startDate, endDate);
    }

   @Override
    public double calculate(User user) {

        double ageMultiplier = 1.0;
        
        if (user.getAge() < 18) {
            ageMultiplier = 0.8; 
        } else if (user.getAge() > 40) {
            ageMultiplier = 1.2; 
        }
        
        return this.getPrice() * ageMultiplier;
    }
}
