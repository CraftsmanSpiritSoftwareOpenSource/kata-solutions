package uk.co.craftsmanspirit.checkoutkata.rules

import spock.lang.Specification
import uk.co.craftsmanspirit.checkoutkata.rules.definition.PricingRuleDefinition

class PricingRuleBuilderTest extends Specification {
    def builder

    def setup(){
        builder = new PricingRuleBuilder()
    }
    def "on build with no additional rules scenario"(){
        when:"build is called"
        def pricingRule = builder.build()

        then:"a basic pricing rule is created"
        pricingRule instanceof BasicPricingRule
    }

    def "on addRule with invalid rule scenario"(){
        when:"adding invalid rule"
        builder.addRule(null)

        then:"NullPointerException is thrown"
        thrown NullPointerException
    }

    def "on build where PricingRuleDefinition added"(){
        given:"a PricingRuleDefinition"
        def mockPricingRuleDefinition = Mock(PricingRuleDefinition)

        and:"it is added to the builder multiple times"
        builder
                .addRule(mockPricingRuleDefinition)
                .addRule(mockPricingRuleDefinition)
                .addRule(mockPricingRuleDefinition)

        when:"build is called"
        def pricingRule = builder.build()

        then:"a DecoratedPricingRule is created"
        pricingRule instanceof DecoratedPricingRule

        and:"pricing rule definition applied"
        3*mockPricingRuleDefinition.getItemMatcher() >> Mock(ItemMatcher)
        3*mockPricingRuleDefinition.getPricingAction() >> Mock(PricingAction)
    }
}
