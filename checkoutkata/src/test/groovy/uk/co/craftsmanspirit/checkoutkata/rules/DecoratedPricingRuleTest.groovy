package uk.co.craftsmanspirit.checkoutkata.rules

import spock.lang.Specification
import spock.lang.Unroll
import uk.co.craftsmanspirit.checkoutkata.rules.definition.PricingRuleDefinition
import uk.co.craftsmanspirit.checkoutkata.scan.Item
import uk.co.craftsmanspirit.checkoutkata.scan.ItemCategory

class DecoratedPricingRuleTest extends Specification {

    @Unroll
    def "on create with invalid parameters"(){
        when:"creating a DecoratedPricingRule"
        new DecoratedPricingRule(Definition,Rule)

        then:"expect NullPointerException"
        thrown NullPointerException

        where:
        Definition                 | Rule
        null                       | null
        Mock(PricingRuleDefinition)| null
    }

    def "on total with invalid parameters"(){
        given:"a valid DecoratedPricingRule"
        def mockItemMatcher = Mock(ItemMatcher)
        def mockPricingAction = Mock(PricingAction)
        def mockPricingRuleDefinition = Mock(PricingRuleDefinition)
        1*mockPricingRuleDefinition.getItemMatcher() >> mockItemMatcher
        1*mockPricingRuleDefinition.getPricingAction() >> mockPricingAction
        def mockPricingRule = Mock(PricingAction)

        def decoratedPricingRule = new DecoratedPricingRule(mockPricingRuleDefinition,mockPricingRule)

        when:"total is called with invalid parameter"
        decoratedPricingRule.total(null)

        then:"expect NullPointerException"
        thrown NullPointerException
    }

    def "on total with item list scenario"(){
        given:"a valid DecoratedPricingRule"
        def mockItemMatcher = Mock(ItemMatcher)
        def mockPricingAction = Mock(PricingAction)
        def mockPricingRuleDefinition = Mock(PricingRuleDefinition)
        1*mockPricingRuleDefinition.getItemMatcher() >> mockItemMatcher
        1*mockPricingRuleDefinition.getPricingAction() >> mockPricingAction
        def mockPricingRule = Mock(PricingAction)

        def decoratedPricingRule = new DecoratedPricingRule(mockPricingRuleDefinition,mockPricingRule)

        and:"an item list"
        def sandwich = new Item(category: ItemCategory.FOOD, id:"sandwich",price:0.8)
        def pop = new Item(category: ItemCategory.DRINK,id:"pop",price:1.20)
        def crisps = new Item(category:ItemCategory.CRISPS,id:"hedgehog flavoured",price:2.45)

        def itemList = [sandwich, pop, crisps]

        when:"total is called"
        def total = decoratedPricingRule.total(itemList)

        then:"itemMatcher returns matched items"
        1*mockItemMatcher.match(itemList) >> [sandwich,pop]

        and:"pricingAction is called with matched items"
        1*mockPricingAction.total([sandwich,pop]) >> 1.8

        and:"decorated rule is called with unmatched items"
        1*mockPricingRule.total([crisps]) >> 2.45

        and:"total returned matches total from decorated rule + pricing action"
        total == (Double)4.25
    }
}
