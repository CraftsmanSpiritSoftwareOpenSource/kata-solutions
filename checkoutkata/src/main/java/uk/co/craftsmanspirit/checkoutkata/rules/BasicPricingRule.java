package uk.co.craftsmanspirit.checkoutkata.rules;


import lombok.NonNull;
import uk.co.craftsmanspirit.checkoutkata.scan.Item;

import java.util.List;

public class BasicPricingRule implements PricingAction{
    @Override
    public Double total(@NonNull List<Item> itemList) {
        Double total = 0.0;
        for(Item item:itemList){
            total += item.getPrice();
        }
        return total;
    }
}
