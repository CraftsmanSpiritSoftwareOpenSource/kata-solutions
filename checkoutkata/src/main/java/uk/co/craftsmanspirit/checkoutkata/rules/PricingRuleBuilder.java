package uk.co.craftsmanspirit.checkoutkata.rules;

import lombok.NonNull;
import uk.co.craftsmanspirit.checkoutkata.rules.definition.PricingRuleDefinition;

import java.util.ArrayList;
import java.util.List;

public class PricingRuleBuilder {

    private List<PricingRuleDefinition> pricingRuleDefinitionList = new ArrayList<>();

    public PricingRuleBuilder addRule(@NonNull PricingRuleDefinition pricingRuleDefinition) {
        pricingRuleDefinitionList.add(pricingRuleDefinition);
        return this;
    }

    public PricingAction build() {
        PricingAction rule = new BasicPricingRule();
        for (PricingRuleDefinition pricingRuleDefinition:pricingRuleDefinitionList){
            rule = new DecoratedPricingRule(pricingRuleDefinition,rule);
        }
        return rule;
    }
}

