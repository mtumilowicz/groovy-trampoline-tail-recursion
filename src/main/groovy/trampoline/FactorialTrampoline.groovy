package trampoline
/**
 * Created by mtumilowicz on 2018-11-21.
 */
class FactorialTrampoline {
    private static final def factorial = { n, accu = 1G -> 
        n < 2 ? accu : factorial.trampoline(n - 1, n * accu) }

    static def getFactorial(int n) {
        return factorial(n)()
    }
}
