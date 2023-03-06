package org.dius.checkout;

public class Product {
    private StockUnit sku;
    private String name;
    private double price;

    private Product(StockUnit sku, String name, double price) {

        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public StockUnit getSku(){
        return sku;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public static Product getIPad(){
        return new Product(StockUnit.ipd, "Super iPad", 549.99);
    }

    public static Product getMacBook(){
        return new Product(StockUnit.mbp, "MacBook Pro", 1399.99);
    }

    public static Product getAppleTV(){
        return new Product(StockUnit.atv, "Apple TV", 109.50);
    }

    public static Product getVgaAdapter(){
        return new Product(StockUnit.vga, "VGA adapter", 30.00);
    }
}

