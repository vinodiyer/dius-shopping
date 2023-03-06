package org.dius.checkout;

public class CartItem {
    private Product product;
    private double discount;

    public CartItem(Product product, double discount) {
        this.product = product;
        this.discount = discount;
    }

    public Product getProduct() {
        return product;
    }

    public double getSellPrice() {
        return product.getPrice() - discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}