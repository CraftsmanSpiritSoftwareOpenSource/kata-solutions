package uk.co.craftsmanspirit.checkoutkata.checkout

import spock.lang.Specification
import uk.co.craftsmanspirit.checkoutkata.rules.BasicPricingRule
import uk.co.craftsmanspirit.checkoutkata.scan.Item
import uk.co.craftsmanspirit.checkoutkata.scan.ItemCategory

class CheckoutTest extends Specification {
    def "on create with invalid pricing rule scenario"(){
        when:"creating a checkout with a null pricing rule"
        new Checkout(null)
        then:"expect a null pointer exception"
        thrown NullPointerException
    }

    def "on scan with simple item scenario"(){
        given:"a a checkout with default pricing rules"
        def checkout = new Checkout(new BasicPricingRule())
        and:"a simple item"
        def item = new Item(category: ItemCategory.FOOD, id: "Oysters", price: 2.1)

        when:"the item is scanned"
        checkout.scan(item as Item)
        then:"the total is correct"
        assert checkout.total() == 2.1

    }
}
