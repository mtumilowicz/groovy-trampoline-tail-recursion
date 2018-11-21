/**
 * Created by mtumilowicz on 2018-11-21.
 */
class Main {

    def even = { i -> i == 0 ? true : odd.trampoline(i - 1) }

    def odd = { i -> i == 0 ? false : even.trampoline(i - 1) }

    static void main(String[] args) {

        println new Main().even(10)
    }
}
