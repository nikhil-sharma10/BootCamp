package demo.domain;

public class Order {

    private int quantity;
    private String itemName;
    private double price;
    private double priceWithTex;

    public Order() {}

    public Order(int quantity, String itemName, double price) {
        this.quantity = quantity;
        this.itemName = itemName;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Order setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public Order setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Order setPrice(double price) {
        this.price = price;
        return this;
    }

    public double getPriceWithTex() {
        return priceWithTex;
    }

    public Order setPriceWithTex(double priceWithTex) {
        this.priceWithTex = priceWithTex;
        return this;
    }
}
