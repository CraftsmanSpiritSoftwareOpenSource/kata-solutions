package uk.co.craftsmanspirit.checkoutkata.checkout;

import lombok.NonNull;
import uk.co.craftsmanspirit.checkoutkata.rules.PricingAction;
import uk.co.craftsmanspirit.checkoutkata.scan.Item;

import java.util.ArrayList;
import java.util.List;

public class Checkout {
    private PricingAction pricingRule;
    private List<Item> itemList;

    Checkout(@NonNull PricingAction pricingRule) {
        this.pricingRule = pricingRule;
        itemList = new ArrayList<>();
    }

    public void scan(Item item) {
        itemList.add(item);
    }

    public Double total() {
        return pricingRule.total(itemList);
    }
}
