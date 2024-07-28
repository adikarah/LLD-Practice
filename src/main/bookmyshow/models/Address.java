package main.bookmyshow.models;

public class Address {
    private String localityName;
    private String pinCode;
    private String cityName;
    private String state;

    public Address(String localityName, String pinCode, String cityName, String state) {
        this.localityName = localityName;
        this.pinCode = pinCode;
        this.cityName = cityName;
        this.state = state;
    }

    public String getLocalityName() {
        return localityName;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getCityName() {
        return cityName;
    }

    public String getState() {
        return state;
    }
}
