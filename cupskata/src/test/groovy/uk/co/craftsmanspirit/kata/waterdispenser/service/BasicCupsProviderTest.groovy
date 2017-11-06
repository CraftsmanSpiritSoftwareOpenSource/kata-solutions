package uk.co.craftsmanspirit.kata.waterdispenser.service

import spock.lang.Unroll
import uk.co.craftsmanspirit.kata.waterdispenser.dto.Cup
import uk.co.craftsmanspirit.kata.waterdispenser.exception.ArgumentOutOfRange
import uk.co.craftsmanspirit.kata.waterdispenser.request.RequestValidator

class BasicCupsProviderTest extends CupsProviderTest {
    def basicCupsProvider
    def mockRequestValidator

    def setup(){
        mockRequestValidator = Mock(RequestValidator)
        basicCupsProvider = new BasicCupsProvider(mockRequestValidator)
    }

    def "on getCups for invalid request scenario"(){
        given: "request validator will throw an exception"
        1*mockRequestValidator.validate(0) >> {throw new ArgumentOutOfRange("test error message")}

        when: "getCups is called"
        basicCupsProvider.getCups(0)

        then:"an Argument out of bound exception is thrown"
        thrown ArgumentOutOfRange
    }

    @Unroll
    def "on getCups for ranges scenario"(){
        when:"getCups is called"
        List<Cup> requiredCups = basicCupsProvider.getCups(requestedLitres)

        then:
        def expectedCups = getExpectedCups(requestedLitres,2, [])
        requiredCups.sort() == expectedCups.sort()
        
        where:
        requestedLitres << (1..14)

    }
}
