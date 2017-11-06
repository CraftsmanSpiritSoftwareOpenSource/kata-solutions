package uk.co.craftsmanspirit.checkoutkata.rules

import spock.lang.Specification
import uk.co.craftsmanspirit.checkoutkata.scan.Item
import uk.co.craftsmanspirit.checkoutkata.scan.ItemCategory

class BasicPricingRuleTest extends Specification {
    def "on total with invalid item list scenario"(){
        given:"a basic pricing rule"
        def basicPricingRule = new BasicPricingRule()

        when:"total called"
        basicPricingRule.total(null)

        then:"a null pointer exception is thrown"
        thrown NullPointerException
    }

    def "on total for simple item scenario"(){
        given:"a simple item list"
        def item = new Item(category: ItemCategory.FOOD, id: "Oysters", price: 2.1)
        def itemList = [item]

        and:"a basic pricing rule"
        def basicPricingRule = new BasicPricingRule()

        when:"total called"
        def total = basicPricingRule.total(itemList as List)

        then:"item price returned"
        assert total == new Double(2.1)
    }
}
