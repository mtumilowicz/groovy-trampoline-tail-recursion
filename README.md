# groovy-trampoline

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