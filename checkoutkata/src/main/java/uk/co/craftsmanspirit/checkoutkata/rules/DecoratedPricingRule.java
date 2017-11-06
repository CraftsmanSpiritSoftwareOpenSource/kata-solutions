package uk.co.craftsmanspirit.checkoutkata.rules;

import lombok.NonNull;
import uk.co.craftsmanspirit.checkoutkata.rules.definition.PricingRuleDefinition;
import uk.co.craftsmanspirit.checkoutkata.scan.Item;

import java.util.ArrayList;
import java.util.List;

public class DecoratedPricingRule implements PricingAction{
    private ItemMatcher itemMatcher;
    private PricingAction pricingAction;
    private PricingAction decoratedRule;

    public DecoratedPricingRule(@NonNull PricingRuleDefinition pricingRuleDefinition, @NonNull PricingAction decoratedRule) {
        this.decoratedRule = decoratedRule;
        itemMatcher = pricingRuleDefinition.getItemMatcher();
        pricingAction = pricingRuleDefinition.getPricingAction();
    }

    @Override
    public Double total(@NonNull List<Item> itemList) {
        List<Item> myItemList = new ArrayList<>(itemList);
        List<Item> matchedItems = itemMatcher.match(myItemList);
        myItemList.removeAll(matchedItems);
        return pricingAction.total(matchedItems) + decoratedRule.total(myItemList);
    }
}
