package model.insurance;

import java.time.LocalDate;

import model.user.User;

public class TravelInsurance extends Insurance {
    public TravelInsurance(String name, double price, LocalDate start, LocalDate end) {
        super(name, price, start, end);
    }

    @Override
    public double calculate(User user) {
        
        return this.getPrice() * 1.10;
    }
}