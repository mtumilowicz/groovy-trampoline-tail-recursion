package bug
/**
 * Created by mtumilowicz on 2018-11-21.
 */
class FactorialTrampolineGroovyBug {
    public static final def factorial = { n, accu = 1G -> 
        n < 2 ? accu : factorial.trampoline(n - 1, n * accu) }.trampoline()
    
    static testFactorialSuccess() {
        assert factorial(5) == 120
    }

    static testFactorialFail() {
        assert factorial(6) == 120
    }
}
