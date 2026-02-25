package model.account;

import model.insurance.Insurance;
import model.user.User;

public class Enterprise extends Account {

    public Enterprise(User user) {
        super(user);
    }

    @Override
    public void addInsurance(Insurance insurance) {
        double basePrice = insurance.calculate(this.getUser());
        
        double finalPrice = basePrice * 1.05;
        
        insurance.setPrice(finalPrice);
        this.getInsurances().add(insurance);
    }
}
