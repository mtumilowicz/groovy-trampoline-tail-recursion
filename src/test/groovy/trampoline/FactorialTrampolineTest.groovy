package trampoline

import spock.lang.Specification

/**
 * Created by mtumilowicz on 2018-11-21.
 */
class FactorialTrampolineTest extends Specification {
    def "5! -> 120"() {
        expect:
        FactorialTrampoline.factorial(5)() == 120
    }

    def "11! -> 39_916_800"() {
        expect:
        FactorialTrampoline.factorial(11)() == 39_916_800

    }

    def "13! -> 6_227_020_800"() {
        expect:
        FactorialTrampoline.factorial(13)() == 6_227_020_800
    }

    def "14! -> 87_178_291_200"() {
        expect:
        FactorialTrampoline.factorial(14)() == 87_178_291_200
    }
}
