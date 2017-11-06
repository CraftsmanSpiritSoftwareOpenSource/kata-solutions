package uk.co.craftsmanspirit.kata.waterdispenser.handler

import spock.lang.Specification
import uk.co.craftsmanspirit.kata.waterdispenser.dto.Cup
import uk.co.craftsmanspirit.kata.waterdispenser.exception.ArgumentOutOfRange
import uk.co.craftsmanspirit.kata.waterdispenser.request.RequestValidator
import uk.co.craftsmanspirit.kata.waterdispenser.service.CupsProvider

class WaterDispenserTest extends Specification {

    def waterDispenser
    def mockRequestValidator
    def mockCupsProvider

    def setup() {
        mockRequestValidator = Mock(RequestValidator)
        mockCupsProvider = Mock(CupsProvider)
        //create water dispenser with mock injection
        waterDispenser = new WaterDispenser(
                requestValidator: mockRequestValidator,
                cupsProvider: mockCupsProvider
        )
    }

    def "on handle water request with input validation error scenario"(){
        given:"the input validation fails"
        1*mockRequestValidator.validate(0) >> {throw new ArgumentOutOfRange("test error message")}

        when:"an invalid ammount of water is requested"
        def response = waterDispenser.handleWaterRequest(0)

        then:"no exception is thrown"
        noExceptionThrown()

        and:"error message returned"
        response == "test error message"
    }

    def "on handle water request with valid input scenario"(){
        given:"the input is valid"
        1*mockRequestValidator.validate(7)

        and:"the cups are calculated"
        def cupThreeLtr = new Cup(3, 3, 2)
        def cupFourLitre = new Cup(4, 4, 4)
        1*mockCupsProvider.getCups(7) >> [cupThreeLtr, cupFourLitre]

        when:"water is requested"
        def response = waterDispenser.handleWaterRequest(7)

        then:"the expected json string is returned"
        response == "{\"totalCost\":6,\"cupsCollection\":[{\"capacity\":3,\"amount\":3,\"cost\":2},{\"capacity\":4,\"amount\":4,\"cost\":4}]}"
    }
}
