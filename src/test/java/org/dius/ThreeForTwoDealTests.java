package org.dius;

import org.dius.checkout.CartItem;
import org.dius.checkout.Product;
import org.dius.checkout.StockUnit;
import org.dius.pricing.ThreeForTwoDeal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreeForTwoDealTests {
    @Test
    public void givenThreeForTwoDeal_WhenAddingFirstItem_NoDiscountIsApplied() {
        // Arrange
        ThreeForTwoDeal deal = new ThreeForTwoDeal();
        List<CartItem> cart = new ArrayList<>();

        // Act
        deal.apply(cart, Product.getIPad());

        // Assert
        assertEquals(1, cart.size());

        CartItem item = cart.get(0);
        assertEquals(StockUnit.ipd, item.getProduct().getSku());
        assertEquals("Super iPad", item.getProduct().getName());
        assertEquals(549.99, item.getSellPrice());
    }

    @Test
    public void givenThreeForTwoDeal_WhenAddingSecondItem_NoDiscountIsApplied() {
        // Arrange
        ThreeForTwoDeal deal = new ThreeForTwoDeal();
        List<CartItem> cart = new ArrayList<>();

        // Act
        deal.apply(cart, Product.getIPad());
        deal.apply(cart, Product.getIPad());

        // Assert
        assertEquals(2, cart.size());

        CartItem item1 = cart.get(0);
        assertEquals(StockUnit.ipd, item1.getProduct().getSku());
        assertEquals("Super iPad", item1.getProduct().getName());
        assertEquals(549.99, item1.getSellPrice());

        CartItem item2 = cart.get(0);
        assertEquals(StockUnit.ipd, item2.getProduct().getSku());
        assertEquals("Super iPad", item2.getProduct().getName());
        assertEquals(549.99, item2.getSellPrice());
    }

    @Test
    public void givenThreeForTwoDeal_WhenAddingThirdItem_DiscountIsApplied() {
        // Arrange
        ThreeForTwoDeal deal = new ThreeForTwoDeal();
        List<CartItem> cart = new ArrayList<>();

        // Act
        deal.apply(cart, Product.getIPad());
        deal.apply(cart, Product.getIPad());
        deal.apply(cart, Product.getIPad());

        // Assert
        assertEquals(3, cart.size());

        CartItem item1 = cart.get(0);
        assertEquals(StockUnit.ipd, item1.getProduct().getSku());
        assertEquals("Super iPad", item1.getProduct().getName());
        assertEquals(549.99, item1.getSellPrice());

        CartItem item2 = cart.get(1);
        assertEquals(StockUnit.ipd, item2.getProduct().getSku());
        assertEquals("Super iPad", item2.getProduct().getName());
        assertEquals(549.99, item2.getSellPrice());

        CartItem item3 = cart.get(2);
        assertEquals(StockUnit.ipd, item3.getProduct().getSku());
        assertEquals("Super iPad", item3.getProduct().getName());
        assertEquals(0.0, item3.getSellPrice());
    }
}