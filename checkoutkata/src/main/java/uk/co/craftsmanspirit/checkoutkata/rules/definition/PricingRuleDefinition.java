package uk.co.craftsmanspirit.checkoutkata.rules.definition;

import lombok.Data;
import uk.co.craftsmanspirit.checkoutkata.rules.ItemMatcher;
import uk.co.craftsmanspirit.checkoutkata.rules.PricingAction;

@Data
public class PricingRuleDefinition {
    private ItemMatcher itemMatcher;
    private PricingAction pricingAction;
}
