package org.dius;

import org.dius.checkout.CartItem;
import org.dius.checkout.Product;
import org.dius.pricing.FreeItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FreeItemTests {
    @Test
    public void giveFreeItem_WhenAssociatedItemNotFound_ItemIsNotFree(){
        // Arrange
        FreeItem deal = new FreeItem();
        List<CartItem> cart = new ArrayList<>();

        // Act
        deal.apply(cart, Product.getIPad());
        deal.apply(cart, Product.getVgaAdapter());

        // Assert
        assertEquals(2, cart.size());
        double total = cart.stream().mapToDouble(item -> item.getSellPrice()).sum();
        assertEquals(549.99 + 30.00, total);
    }

    @Test
    public void giveFreeItem_WhenAssociatedItemIsFound_ItemIsFree(){
        // Arrange
        FreeItem deal = new FreeItem();
        List<CartItem> cart = new ArrayList<>();

        // Act
        deal.apply(cart, Product.getIPad());
        deal.apply(cart, Product.getVgaAdapter());
        deal.apply(cart, Product.getMacBook());

        // Assert
        assertEquals(3, cart.size());
        double total = cart.stream().mapToDouble(item -> item.getSellPrice()).sum();
        assertEquals(549.99 + 1399.99, total);
    }
}
