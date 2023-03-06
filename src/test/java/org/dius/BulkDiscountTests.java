package org.dius;

import org.dius.checkout.CartItem;
import org.dius.checkout.Product;
import org.dius.pricing.BulkDiscount;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BulkDiscountTests {
    @Test
    public void givenBulkDiscount_WhenAddingUpToFourItems_NoDiscountIsApplied() {
        // Arrange
        BulkDiscount deal = new BulkDiscount();
        List<CartItem> cart = new ArrayList<>();

        // Act
        deal.apply(cart, Product.getIPad());
        deal.apply(cart, Product.getIPad());
        deal.apply(cart, Product.getIPad());
        deal.apply(cart, Product.getIPad());

        // Assert
        assertEquals(4, cart.size());
        double total = cart.stream().mapToDouble(item -> item.getSellPrice()).sum();
        assertEquals(549.99 * 4, total);
    }

    @Test
    public void givenBulkDiscount_WhenAddingFiveOrMoreItems_BulkDiscountIsApplied() {
        // Arrange
        BulkDiscount deal = new BulkDiscount();
        List<CartItem> cart = new ArrayList<>();

        // Act
        deal.apply(cart, Product.getIPad());
        deal.apply(cart, Product.getIPad());
        deal.apply(cart, Product.getIPad());
        deal.apply(cart, Product.getIPad());
        deal.apply(cart, Product.getIPad());

        // Assert
        assertEquals(5, cart.size());
        double total1 = cart.stream().mapToDouble(item -> item.getSellPrice()).sum();
        assertEquals(499.99 * 5, total1);

        // Add another item and assert
        deal.apply(cart, Product.getIPad());
        assertEquals(6, cart.size());
        double total2 = cart.stream().mapToDouble(item -> item.getSellPrice()).sum();
        assertEquals(499.99 * 6, total2);
    }
}