package trampoline

import spock.lang.Specification

/**
 * Created by mtumilowicz on 2018-11-21.
 */
class NumberTrampolineTest extends Specification {
    
    def "even 2"() {
        expect:
        NumberTrampoline.isEven(2)
    }

    def "even 1000"() {
        expect:
        NumberTrampoline.isEven(1000)
    }

    def "odd 123_001"() {
        expect:
        NumberTrampoline.isOdd(123_001)
    }
}
