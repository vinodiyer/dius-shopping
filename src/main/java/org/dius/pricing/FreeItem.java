package org.dius.pricing;

import org.dius.checkout.CartItem;
import org.dius.checkout.Product;
import org.dius.checkout.StockUnit;

import java.util.List;

public class FreeItem implements PricingRule {
    private final StockUnit BUNDLE_ITEM_1 = StockUnit.mbp;
    private final StockUnit BUNDLE_ITEM_2 = StockUnit.vga;
    private final double DISCOUNT_AMOUNT = 30.0;

    @Override
    public String getName() {
        return "Free Item";
    }

    @Override
    public void apply(List<CartItem> cart, Product product) {
        cart.add(new CartItem(product, 0.0));
        List<CartItem> matchByFreeItemSku = cart
                .stream()
                .filter(c -> c.getProduct().getSku() == BUNDLE_ITEM_2)
                .toList();

        long matchByAssociatedItemSkuCount = cart
                .stream()
                .filter(c -> c.getProduct().getSku() == BUNDLE_ITEM_1)
                .count();

        if (matchByAssociatedItemSkuCount == 0) {
            return;
        }

        for (int i = 0; i < matchByFreeItemSku.size(); i++) {
            double discount = i < matchByAssociatedItemSkuCount
                    ? DISCOUNT_AMOUNT
                    : 0.0;
            matchByFreeItemSku.get(i).setDiscount(discount);
        }
    }
}