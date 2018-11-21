package trampoline

import spock.lang.Specification

/**
 * Created by mtumilowicz on 2018-11-21.
 */
class FibonacciTrampolineTest extends Specification {

    def "fibo 10 = 55"() {
        expect:
        FibonacciTrampoline.fibo(10)() == 55
    }

    def "fibo 27 = 196_418"() {
        expect:
        FibonacciTrampoline.fibo(27)() == 196_418
    }

    def "fibo 50 = 12_586_269_025"() {
        expect:
        FibonacciTrampoline.fibo(50)() == 12_586_269_025
    }
}
