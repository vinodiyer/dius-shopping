package org.dius;

import org.dius.checkout.CartItem;
import org.dius.checkout.Product;
import org.dius.checkout.StockUnit;
import org.dius.pricing.BulkDiscount;
import org.dius.pricing.FreeItem;
import org.dius.pricing.PricingRule;
import org.dius.pricing.PricingRules;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricingRuleTests {
    @Test
    public void givenPricingRules_IfCartIsEmpty_ItemIsAddedToCart() {
        // Arrange
        PricingRules rules = new PricingRules();
        List<CartItem> cart = new ArrayList<>();

        // Act
        rules.applyAndAdd(cart, Product.getIPad());

        // Assert
        assertEquals(1, cart.size());

        CartItem item = cart.get(0);
        assertEquals(StockUnit.ipd, item.getProduct().getSku());
        assertEquals("Super iPad", item.getProduct().getName());
        assertEquals(549.99, item.getSellPrice());
    }

    @Test
    public void givenPricingRules_IfNewRuleIsAddedWithoutOverwrite_OldRuleIsRetained() {
        // Arrange
        PricingRules rules = new PricingRules();

        // Act
        rules.addRule(StockUnit.mbp, new FreeItem());
        rules.addRule(StockUnit.mbp, new BulkDiscount());

        // Assert
        assertEquals(1, rules.getRules().size());
        assertEquals("Free Item", rules.getRules().get(StockUnit.mbp).getName());
    }

    @Test
    public void givenPricingRules_IfNewRuleIsAddedWithOverwrite_OldRuleIsOverwritten() {
        // Arrange
        PricingRules rules = new PricingRules();

        // Act
        rules.addRule(StockUnit.mbp, new FreeItem());
        rules.addRule(StockUnit.mbp, new BulkDiscount(), true);

        // Assert
        assertEquals(1, rules.getRules().size());
        assertEquals("Bulk Discount", rules.getRules().get(StockUnit.mbp).getName());
    }
}