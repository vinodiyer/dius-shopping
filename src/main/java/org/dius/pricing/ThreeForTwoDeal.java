package org.dius.pricing;

import org.dius.checkout.CartItem;
import org.dius.checkout.Product;

import java.util.List;

public class ThreeForTwoDeal implements PricingRule {
    @Override
    public String getName() {
        return "3 for 2 deal";
    }

    @Override
    public void apply(List<CartItem> cart, Product product) {
        long matchBySkuCount = cart
                .stream()
                .filter(c -> c.getProduct().getSku() == product.getSku())
                .count();

        if (matchBySkuCount % 3 == 2) {
            // adding the third item for the sku.
            // every third item should be discounted
            cart.add(new CartItem(product, product.getPrice()));
            return;
        }

        // no discount for this item
        cart.add(new CartItem(product, 0.0));
    }
}