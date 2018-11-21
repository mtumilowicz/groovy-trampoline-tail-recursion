package trampoline
/**
 * Created by mtumilowicz on 2018-11-21.
 */
class NumberTrampoline {
    private static final def even = { i -> i == 0 ? true : odd.trampoline(i - 1) }
    private static final def odd = { i -> i == 0 ? false : even.trampoline(i - 1) }

    static def isEven(int n) {
        even(n)()
    }

    static def isOdd(int n) {
        odd(n)()
    }
}
