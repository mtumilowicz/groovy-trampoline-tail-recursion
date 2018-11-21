# groovy-trampoline-tail-recursion
Trampoline and tail recursion features for groovy closures.

_Reference_: http://groovy-lang.org/closures.html#_trampoline  
_Reference_: http://docs.groovy-lang.org/latest/html/gapi/groovy/transform/TailRecursive.html  
_Reference_: https://stackoverflow.com/questions/33923/what-is-tail-recursion

# trampoline
Recursive algorithms are often restricted by a physical 
limit: the maximum stack height (`StackOverflowException`).

An approach that helps in those situations is by using 
`Closure` and its trampoline capability.

Closures are wrapped in a **TrampolineClosure**. Upon 
calling, a trampolined `Closure` will call the original 
`Closure` waiting for its result. If the outcome of the 
call is another instance of a `TrampolineClosure`, 
created perhaps as a result to a call to the 
`trampoline()` method, the Closure will again be 
invoked. This repetitive invocation of returned 
trampolined Closures instances will continue 
until a value other than a trampolined Closure is 
returned. That value will become the final result of 
the trampoline. **That way, calls are made serially, 
rather than filling the stack**.

## bug
Note that the code in `trampoline` package is quite strange:
```
class FactorialTrampoline {
    private static final def factorial = { n, accu = 1G ->
        n < 2 ? accu : factorial.trampoline(n - 1, n * accu)
    }

    static def getFactorial(int n) {
        factorial(n)()
    }
}
```
because of bug in groovy (example in `bug` package)
```
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
```
and tests:
* when calling outside the class
    ```
    when:
    FactorialTrampolineGroovyBug.factorial(5) == 120
    
    then:
    thrown(MissingMethodException)
    ```
* calling from inside
    ```
    when:
    FactorialTrampolineGroovyBug.testFactorialSuccess()
    
    then:
    noExceptionThrown()
    ```
    ```
    when:
    FactorialTrampolineGroovyBug.testFactorialFail()
    
    then:
    thrown(PowerAssertionError)
    ```
**so in our examples we have to go through additional functions.**

## example
Examples + tests of using trampoline are in `trampoline` package.
* factorial
    ```
    def factorial = { n, accu = 1G ->
            n < 2 ? accu : factorial.trampoline(n - 1, n * accu)
    ```
    ```
    expect:
    FactorialTrampoline.factorial(14)() == 87_178_291_200
    ```
* fibonacci
    ```
    def fibonacci = { first = 0G, second = 1G, n ->
            n == 0 ? first : fibonacci.trampoline(second, first + second, n - 1)
        }
    ```
    ```
    expect:
    FibonacciTrampoline.fibonacci(50)() == 12_586_269_025
    ```
* two functions mutually calling each other
    ```
    def even = { i -> i == 0 ? true : odd.trampoline(i - 1) }
    def odd = { i -> i == 0 ? false : even.trampoline(i - 1) }
    ```
    ```
    expect:
    NumberTrampoline.even(10_000)()
    NumberTrampoline.odd(123_001)()
    ```
    

# tail recursion
A tail call (tail recursion) is a kind of goto dressed 
as a call. A tail call happens when a function calls 
another as its last action, so it has nothing else to do. 
For instance, in the following code, the call to `g` is a 
tail call:
```
function f (x)
  return g(x)
end
```
After `f` calls `g`, it has nothing else to do. In 
such situations, the program does not need to return 
to the calling function when the called function ends. 
**Therefore, after the tail call, the program does not 
need to keep any information about the calling function 
in the stack**.

Note that:
```
class NumberTailRecursion {

    @TailRecursive
    static def isEven(int n) {
        n == 0 ? true : isOdd(n-1)
    }

    @TailRecursive
    static def isOdd(int n) {
        n == 0 ? false : isEven(n-1)
    }
}
```
will produce compile time error:
`Error: Groovyc: No recursive calls detected. You must remove annotation @TailRecursive.`

From documentation (known shortcomings):
* Only non-void methods are currently being handled. 
Void methods will fail compilation.

* **Only direct recursion (calling the exact same method 
again) is supported.**

* Mixing of tail calls and non-tail calls is not possible. 
The compiler will complain if some recursive calls cannot 
be handled.

* Checking if a recursive call is really tail-recursive 
is not very strict. You might run into cases where 
non-tail calls will be considered tail calls.

* In the presence of method overloading and method 
overriding you might run into situations where a call 
is considered recursive although it really is not.

* Catching Throwable around a recursive might lead to problems

* Non trivial continuation passing style examples do not work.

* Probably many unrecognized edge cases.

## example
* factorial
    ```
    @TailRecursive
    static def factorial(n, accu = 1G) {
        n < 2 ? accu : factorial(n - 1, n * accu)
    }
    ```
* fibonacci
    ```
    @TailRecursive
    static def fibonacci(first = 0G, second = 1G, n) {
        n == 0 ? first : fibonacci(second, first + second, n - 1)
    }
    ```