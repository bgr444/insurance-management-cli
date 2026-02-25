package service;
import model.address.IAddress;
import model.user.User;

public class AddressManager {

    public static void addAddress(User user, IAddress address){
        user.getAddresses().add(address);
    }

    public static void removeAddress(User user, IAddress address){
        user.getAddresses().remove(address);
    }
}
