package model.account;

import model.insurance.Insurance;
import model.user.User;

public class Individual extends Account {
    public Individual(User user){
        super(user);
    }

    @Override
    public void addInsurance(Insurance insurance) {

       double basePrice = insurance.calculate(this.getUser());

        double finalPrice = basePrice * 1.10;

        insurance.setPrice(finalPrice);
        this.getInsurances().add(insurance);

    }
}
