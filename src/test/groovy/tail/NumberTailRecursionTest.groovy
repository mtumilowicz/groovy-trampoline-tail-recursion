package tail

import spock.lang.Specification 
/**
 * Created by mtumilowicz on 2018-11-21.
 */
class NumberTailRecursionTest extends Specification {
    
    def "even 2"() {
        expect:
        NumberTailRecursion.isEven(2)
    }

    def "even 10_000"() {
        expect:
        NumberTailRecursion.isEven(10_000)
    }

    def "odd 123_001"() {
        expect:
        NumberTailRecursion.isOdd(123_001)
    }
}
