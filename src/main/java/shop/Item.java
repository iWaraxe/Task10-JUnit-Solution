package shop;

public class Item {

    private String name;
    private double price;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return String.format("Class: %s; Name: %s; Price: %s; ", getClass(), name, price);
    }
}
