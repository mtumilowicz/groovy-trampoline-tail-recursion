package trampoline
/**
 * Created by mtumilowicz on 2018-11-21.
 */
class FactorialTrampoline {
    public static def factorial = { n, accu = 1G -> n < 2 ? accu : factorial.trampoline(n - 1, n * accu) }
}
