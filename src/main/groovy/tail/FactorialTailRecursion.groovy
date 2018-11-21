package tail

import groovy.transform.TailRecursive

/**
 * Created by mtumilowicz on 2018-11-21.
 */
class FactorialTailRecursion {

    @TailRecursive
    static def factorial(n, accu = 1G) {
        n < 2 ? accu : factorial(n - 1, n * accu)
    }
}
