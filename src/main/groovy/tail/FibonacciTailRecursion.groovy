package tail

import groovy.transform.TailRecursive

/**
 * Created by mtumilowicz on 2018-11-21.
 */
class FibonacciTailRecursion {

    @TailRecursive
    static def fibonacci(first = 0G, second = 1G, n) {
        n == 0 ? first : fibonacci(second, first + second, n - 1)
    }
}
