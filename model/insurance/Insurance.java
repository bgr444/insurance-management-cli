package model.insurance;

import java.time.LocalDate;

import model.user.User;

public abstract class Insurance {
    private String name;
    private double price;
    private LocalDate startDate;
    private LocalDate endDate;

    public Insurance(String name, double price, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public abstract double calculate(User user);

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
