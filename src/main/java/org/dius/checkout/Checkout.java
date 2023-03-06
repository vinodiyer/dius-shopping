package org.dius.checkout;

import org.dius.pricing.PricingRules;

import java.util.ArrayList;
import java.util.List;

public class Checkout {

    private final List<CartItem> cart = new ArrayList<>();
    private final PricingRules pricingRules;

    public Checkout(PricingRules pricingRules) {

        this.pricingRules = pricingRules;
    }

    public void scan(Product product) {
        pricingRules.applyAndAdd(cart, product);
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public double total() {
        double total = 0.0;
        for (int i = 0; i < cart.size(); i++) {
            total += cart.get(i).getSellPrice();
        }

        return total;
    }
}