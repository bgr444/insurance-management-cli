package model.address;

public class BusinessAddress implements IAddress {

    private String addressDetail;

    // Constructor: Adresi oluştururken detayını içine yazıyoruz
    public BusinessAddress(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    @Override
    public String getAddressInfo() {
        return "Business Address: " + this.addressDetail;
    }

}
