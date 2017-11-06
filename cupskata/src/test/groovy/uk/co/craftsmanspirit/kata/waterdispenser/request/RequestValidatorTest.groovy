package uk.co.craftsmanspirit.kata.waterdispenser.request

import spock.lang.Specification
import uk.co.craftsmanspirit.kata.waterdispenser.exception.ArgumentOutOfRange

class RequestValidatorTest extends Specification {

    def requestValidator

    def setup() {
        requestValidator = new RequestValidator()
    }

    def "on request outside of range scenario"(){
        given: "input outside of range"
        def input = requestInput

        when:"validate is called"
        requestValidator.validate(input)

        then:"an exception is thrown"
        thrown ArgumentOutOfRange

        where:
        requestInput || _
        -1           || _
        0            || _
        15           || _
    }

    def "on request within range scenario"(){
        given: "input outside of range"
        def input = requestInput

        when:"validate is called"
        requestValidator.validate(input)

        then:"no exception is thrown"
        noExceptionThrown()

        where:
        requestInput || _
        1            || _
        14           || _
    }
}
