package uk.co.craftsmanspirit.checkoutkata.steps

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import uk.co.craftsmanspirit.checkoutkata.checkout.Checkout
import uk.co.craftsmanspirit.checkoutkata.rules.PricingAction
import uk.co.craftsmanspirit.checkoutkata.rules.PricingRuleBuilder
import uk.co.craftsmanspirit.checkoutkata.rules.definition.PricingRuleDefinition
import uk.co.craftsmanspirit.checkoutkata.scan.Item

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

ObjectMapper mapper = new ObjectMapper()

Properties testProperties

Before(){
    testProperties = new Properties()
    testProperties.load(this.getClass().getResourceAsStream("/test.properties"))
}

PricingAction pricingRules

Given(~/^a set of pricing rules: (.*)$/) { String ruleSetProperty ->
    pricingRules = createPricingRule(testProperties."$ruleSetProperty" as String, mapper)
}

Checkout checkout

And(~/^a new checkout transaction$/) { ->
    checkout = new Checkout(pricingRules)
}

List<Item> itemsList

When(~/^the each item from (.*) is scanned$/) { itemListProperty ->
    String itemListJsonFile = testProperties."$itemListProperty"
    itemsList = mapper.readValue(
            this.getClass().getResourceAsStream(itemListJsonFile),
            new TypeReference<List<Item>>(){})

}

List<Double> priceList

Then(~/^the correct prices: (.*) is calculated$/) { pricesListProperty ->
    String priceListJsonFile = testProperties."$pricesListProperty"
    priceList = mapper.readValue(
            this.getClass().getResourceAsStream(priceListJsonFile),
            new TypeReference<List<Double>>(){})

    assert priceList.size() == itemsList.size()
    int itemCount=0
    for(Item item: itemsList){
        checkout.scan(item)
        assert priceList.get(itemCount++) == checkout.total()
    }
}

PricingAction createPricingRule(String picingRulesJsonFile, ObjectMapper mapper) {

    List<PricingRuleDefinition> ruleDefinitionList = mapper.readValue(
            this.getClass().getResourceAsStream(picingRulesJsonFile),
            new TypeReference<List<PricingRuleDefinition>>(){})

    PricingRuleBuilder pricingRuleBuilder = new PricingRuleBuilder()

    for(PricingRuleDefinition pricingRuleDefinition:ruleDefinitionList){
        pricingRuleBuilder.addRule(pricingRuleDefinition)
    }
    pricingRuleBuilder.build()

}