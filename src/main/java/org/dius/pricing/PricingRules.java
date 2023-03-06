package org.dius.pricing;

import org.dius.checkout.CartItem;
import org.dius.checkout.Product;
import org.dius.checkout.StockUnit;

import java.util.Hashtable;
import java.util.List;

public class PricingRules {

    private Hashtable<StockUnit, PricingRule> rules = new Hashtable<>(0);

    /**
     * Currently allows only one discount rule per sku
     *
     * @param rule
     * @return true if rule has been added for the sku
     */
    public boolean addRule(StockUnit sku, PricingRule rule, boolean overwriteRule) {
        if (rules.containsKey(sku) && !overwriteRule) {
            return false;
        }

        rules.put(sku, rule);
        return true;
    }

    public boolean addRule(StockUnit sku, PricingRule rule) {
        return addRule(sku, rule, false);
    }

    public void applyAndAdd(List<CartItem> cart, Product product) {
        if (cart.isEmpty() || !rules.containsKey(product.getSku())) {
            cart.add(new CartItem(product, 0.0));
            return;
        }

        rules.get(product.getSku()).apply(cart, product);
    }

    public Hashtable<StockUnit, PricingRule> getRules() {
        return rules;
    }
}