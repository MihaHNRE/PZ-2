package ua.nure.st.kpp.example.demo.entity;

public class Property {
    private String name;
    private String type;
    private String address;
    private double price;

    public Property(String name, String type, String address, double price) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Property{name='" + name + "', type='" + type + "', address='" + address + "', price=" + price + '}';
    }
}
