package bug

import spock.lang.Specification 
/**
 * Created by mtumilowicz on 2018-11-21.
 */
class FactorialTrampolineGroovyBugTest extends Specification {
    def "5! -> 120"() {
        when:
        FactorialTrampolineGroovyBug.factorial(5) == 120
        
        then:
        thrown(MissingMethodException)
    }
    
    def "5! -> 120 from inside"() {
        when:
        FactorialTrampolineGroovyBug.testFactorial()
        
        then:
        noExceptionThrown()
    }
}
