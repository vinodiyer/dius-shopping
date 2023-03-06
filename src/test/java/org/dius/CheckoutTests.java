package org.dius;

import org.dius.checkout.CartItem;
import org.dius.checkout.Checkout;
import org.dius.checkout.Product;
import org.dius.checkout.StockUnit;
import org.dius.pricing.PricingRules;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTests {

    @Test
    public void givenCheckout_WhenCartIsEmpty_ShouldReturnZeroTotal() {
        // Arrange
        Checkout checkout = new Checkout(new PricingRules());
        double total = checkout.total();

        // Assert
        assertEquals(0.0d, total);
    }

    @Test
    public void givenCheckout_WhenAProductIsScanned_ItShouldBeInTheCart() {
        // Arrange
        Checkout checkout = new Checkout(new PricingRules());

        // Act
        checkout.scan(Product.getIPad());

        // Assert
        List<CartItem> cart = checkout.getCart();
        assertEquals(1, cart.size());

        CartItem item = cart.get(0);
        assertEquals(StockUnit.ipd, item.getProduct().getSku());
        assertEquals("Super iPad", item.getProduct().getName());
        assertEquals(549.99, item.getSellPrice());
    }

    @Test
    public void givenCheckout_WhenNoPricingRulesAreSet_CartTotalShouldMatchProductTotal() {
        // Arrange
        Checkout checkout = new Checkout(new PricingRules());

        // Act
        checkout.scan(Product.getVgaAdapter());
        checkout.scan(Product.getAppleTV());
        checkout.scan(Product.getMacBook());
        checkout.scan(Product.getIPad());

        // Assert
        var expectedTotal = 30.00 + 109.50 + 1399.99 + 549.99;
        assertEquals(expectedTotal, checkout.total());
    }
}