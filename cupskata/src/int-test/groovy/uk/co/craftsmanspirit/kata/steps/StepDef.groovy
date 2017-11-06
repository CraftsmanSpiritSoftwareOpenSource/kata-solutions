package uk.co.craftsmanspirit.kata.steps

import com.fasterxml.jackson.databind.ObjectMapper
import uk.co.craftsmanspirit.kata.waterdispenser.handler.WaterDispenser
import uk.co.craftsmanspirit.kata.waterdispenser.dto.WaterResponse

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


WaterDispenser waterDispenser

Given(~/^A water dispenser$/) { ->
    waterDispenser = new WaterDispenser()
}

String responseString

When(~/^Receiving a request for (.*) Litres of water$/) { int requestedWater ->
    responseString = waterDispenser.handleWaterRequest(requestedWater)
}

Then(~/^The Cost is calculated as (.*)$/) { int expectedCost ->
    assert expectedCost == getCostFromJson(responseString)
}

Then(~/^An error (.*) is returned$/) { String expectedError ->
    assert expectedError == responseString
}

int getCostFromJson(String jsonString) {
    ObjectMapper mapper = new ObjectMapper()
    WaterResponse response = mapper.readValue(jsonString, WaterResponse.class)
    response.getTotalCost()
}