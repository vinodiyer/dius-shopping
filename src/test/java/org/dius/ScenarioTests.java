package org.dius;

import org.dius.checkout.Checkout;
import org.dius.checkout.Product;
import org.dius.checkout.StockUnit;
import org.dius.pricing.BulkDiscount;
import org.dius.pricing.FreeItem;
import org.dius.pricing.PricingRules;
import org.dius.pricing.ThreeForTwoDeal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class contains the three example scenarios listed in the coding test.
 * Add more tests and modify the SKUs scanned, or setup rules to test additional cases.
 */
public class ScenarioTests {
    private PricingRules pricingRules;

    public ScenarioTests() {
        pricingRules = new PricingRules();
        pricingRules.addRule(StockUnit.atv, new ThreeForTwoDeal());
        pricingRules.addRule(StockUnit.ipd, new BulkDiscount());
        pricingRules.addRule(StockUnit.vga, new FreeItem());
    }

    @Test
    public void Scenario1() {
        // Act
        Checkout co = new Checkout(pricingRules);
        co.scan(Product.getAppleTV());
        co.scan(Product.getAppleTV());
        co.scan(Product.getAppleTV());
        co.scan(Product.getVgaAdapter());

        // Assert
        assertEquals(249.00, co.total());
    }

    @Test
    public void Scenario2() {
        // Act
        Checkout co = new Checkout(pricingRules);
        co.scan(Product.getAppleTV());
        co.scan(Product.getIPad());
        co.scan(Product.getIPad());
        co.scan(Product.getAppleTV());
        co.scan(Product.getIPad());
        co.scan(Product.getIPad());
        co.scan(Product.getIPad());

        // Assert
        assertEquals(2718.95, co.total());
    }

    @Test
    public void Scenario3() {
        // Act
        Checkout co = new Checkout(pricingRules);
        co.scan(Product.getMacBook());
        co.scan(Product.getVgaAdapter());
        co.scan(Product.getIPad());

        // Assert
        assertEquals(1949.98, co.total());
    }
}