package model.address;

public class HomeAddress implements IAddress {
    private String addressDetail;

    // Constructor: Adresi oluştururken detayını içine yazıyoruz
    public HomeAddress(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    @Override
    public String getAddressInfo() {
        return "Home Address: " + this.addressDetail;
    }

}