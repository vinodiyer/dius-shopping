package org.dius.pricing;

import org.dius.checkout.CartItem;
import org.dius.checkout.Product;

import java.util.List;

public class BulkDiscount implements PricingRule {
    private final double DISCOUNT_AMOUNT = 50.0;

    @Override
    public String getName() {
        return "Bulk Discount";
    }

    @Override
    public void apply(List<CartItem> cart, Product product) {
        List<CartItem> matchByProductSku = cart
                .stream()
                .filter(c -> c.getProduct().getSku() == product.getSku())
                .toList();

        double discount = DISCOUNT_AMOUNT;
        if (matchByProductSku.size() < 4) {
            // not yet qualified for bulk discount
            discount = 0.0;
        }

        if (matchByProductSku.size() == 4) {
            // apply discount for all existing items
            matchByProductSku.forEach((p) -> p.setDiscount(DISCOUNT_AMOUNT));
        }

        cart.add(new CartItem(product, discount));
    }
}