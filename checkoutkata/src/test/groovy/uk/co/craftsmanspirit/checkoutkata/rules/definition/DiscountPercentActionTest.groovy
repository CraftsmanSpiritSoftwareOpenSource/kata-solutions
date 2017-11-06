package uk.co.craftsmanspirit.checkoutkata.rules.definition

import spock.lang.Specification
import uk.co.craftsmanspirit.checkoutkata.scan.Item
import uk.co.craftsmanspirit.checkoutkata.scan.ItemCategory
class DiscountPercentActionTest extends Specification {
    def "on total with invalid parameter scenario"(){
        given:"a valid DiscountPercentAction"
        def discountPercentAction = new DiscountPercentAction()

        when:"total is called with invalid parameter"
        discountPercentAction.total(null)

        then:"a null pointer exception is thrown"
        thrown NullPointerException
    }

    def "on total for items scenario"(){
        given:"a discouny actionm 10%"
        def discountPercentAction = new DiscountPercentAction(percentDiscount: 10)

        and:"a simple item list"
        def oysters = new Item(category:ItemCategory.FOOD, id: "Oysters", price: 2.1)
        def champagne = new Item(category:ItemCategory.DRINK, id: "Champagne", price: 47.9)
        def itemList = [oysters, champagne]

        when:"total is calculated"
        def value = discountPercentAction.total(itemList)

        then:"discount is applied to total"
        value == 45.0 as Double
    }
}
