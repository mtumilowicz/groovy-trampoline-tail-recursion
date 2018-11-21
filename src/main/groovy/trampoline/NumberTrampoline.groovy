package trampoline
/**
 * Created by mtumilowicz on 2018-11-21.
 */
class NumberTrampoline {
    public static def even = { i -> i == 0 ? true : odd.trampoline(i - 1) }
    public static def odd = { i -> i == 0 ? false : even.trampoline(i - 1) }
}
