package org.dius.pricing;

import org.dius.checkout.CartItem;
import org.dius.checkout.Product;

import java.util.List;

public interface PricingRule {
    String getName();
    void apply(List<CartItem> cart, Product product);
}

