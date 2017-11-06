package uk.co.craftsmanspirit.checkoutkata.rules.definition

import spock.lang.Specification
import uk.co.craftsmanspirit.checkoutkata.scan.Item
import uk.co.craftsmanspirit.checkoutkata.scan.ItemCategory

class CategoryMatcherTest extends Specification {
    def "on match with invalid parameter passed"(){
        given:"a category matcher"
        def categoryMatcher = new CategoryMatcher()

        when:"match is called with invalid parameter"
        categoryMatcher.match(null)

        then:"a NullPointerException"
        thrown NullPointerException
    }

    def "match items scenario"(){
        given:"a FOOD category matcher"
        def categoryMatcher = new CategoryMatcher(category: ItemCategory.FOOD)

        and:"an itemList"
        def sandwich = new Item(category: ItemCategory.FOOD, id:"sandwich",price:0.8)
        def pop = new Item(category: ItemCategory.DRINK,id:"pop",price:1.20)
        def oysters = new Item(category:ItemCategory.FOOD,id:"Oysters",price:2.45)

        def itemList = [oysters, pop, sandwich]

        when:"match is called"
        def matchedItems = categoryMatcher.match(itemList)

        then:"the matching items are returned"
        matchedItems == [oysters, sandwich]
    }
}
