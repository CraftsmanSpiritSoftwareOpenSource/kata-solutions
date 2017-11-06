package uk.co.craftsmanspirit.checkoutkata.rules.definition;

import lombok.NonNull;
import lombok.Setter;
import uk.co.craftsmanspirit.checkoutkata.rules.BasicPricingRule;
import uk.co.craftsmanspirit.checkoutkata.scan.Item;

import java.util.List;

public class DiscountPercentAction extends BasicPricingRule {
    @Setter
    private Double percentDiscount;

    public DiscountPercentAction(){
    }

    @Override
    public Double total(@NonNull List<Item> matchedItems) {
        Double preDiscountTotal = super.total(matchedItems);
        return (preDiscountTotal/100.0)*(100.0-percentDiscount);
    }
}
