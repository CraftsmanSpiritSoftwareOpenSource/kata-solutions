package uk.co.craftsmanspirit.kata.waterdispenser.service

import spock.lang.Specification
import uk.co.craftsmanspirit.kata.waterdispenser.dto.Cup


class CupsProviderTest extends Specification{

    def getCup(int requestedLitres, int capacity) {
        def price = [2:1, 3:2, 4:4, 5:8]
        return new Cup(capacity, requestedLitres, price[capacity] )
    }

    def getExpectedCups(int requestedLitres, int nextCapacity, expectedCups) {
        if (requestedLitres > nextCapacity){
            expectedCups = getExpectedCups(requestedLitres - nextCapacity, nextCapacity + 1, expectedCups)
            requestedLitres = nextCapacity
        }
        expectedCups << getCup(requestedLitres, nextCapacity)
        return expectedCups
    }
}
