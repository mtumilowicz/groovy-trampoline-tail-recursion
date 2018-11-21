package trampoline
/**
 * Created by mtumilowicz on 2018-11-21.
 */
class FibonacciTrampoline {
    public static def fibo = { first = 0G, second = 1G, n ->
        n == 0 ? first : fibo.trampoline(second, first + second, n - 1)
    }
}
