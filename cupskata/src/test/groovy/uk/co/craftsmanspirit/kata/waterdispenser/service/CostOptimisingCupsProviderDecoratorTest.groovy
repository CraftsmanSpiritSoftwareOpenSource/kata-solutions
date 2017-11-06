package uk.co.craftsmanspirit.kata.waterdispenser.service

import spock.lang.Unroll

class CostOptimisingCupsProviderDecoratorTest extends CupsProviderTest {
    def "on create with null provider scenario"(){
        when:"a cost optimising cup provider decorator is created with a null provider"
        new CostOptimisingCupsProviderDecorator(null)

        then:"a null pointer exception is thrown"
        thrown NullPointerException
    }

    @Unroll
    def "on getCups scenario"(){
        given: "a cups provider"
        def mockCupsProvider = Mock(CupsProvider)
        1*mockCupsProvider.getCups(requestedLitres) >> {getExpectedCups(requestedLitres, 2, [])}

        and: "a cost optimising cup provider decorator"
        def costOptimisingDecorator = new CostOptimisingCupsProviderDecorator(mockCupsProvider)

        when:
        def optimisedCups = costOptimisingDecorator.getCups(requestedLitres)

        then:
        def expectedOptimisedCups = getOptimisedCups(requestedLitres)
        expectedOptimisedCups.sort() == optimisedCups.sort()

        where:
        requestedLitres << (1..14)
    }

    def getOptimisedCups(int requestedLitres) {
        def expectedCups = []
        switch(requestedLitres){
            case 3:
                expectedCups = [getCup(3, 3)]
                break
            case 6:
                expectedCups = [getCup(4, 4), getCup(2, 2)]
                break
            case 7:
                expectedCups = [getCup(4, 4), getCup(3, 3)]
                break
            case 10:
                expectedCups = [getCup(5,5), getCup(3,3), getCup(2,2)]
                break
            case 11:
                expectedCups = [getCup(5,5), getCup(4,4), getCup(2,2)]
                break
            case 12:
                expectedCups = [getCup(5,5), getCup(4,4), getCup(3,3)]
                break
            default:
                expectedCups = getExpectedCups(requestedLitres, 2, [])
                break
        }
        expectedCups
    }
}
