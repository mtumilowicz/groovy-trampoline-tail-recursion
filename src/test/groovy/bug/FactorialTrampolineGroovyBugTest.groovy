package bug

import org.codehaus.groovy.runtime.powerassert.PowerAssertionError
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
    
    def "5! -> 120 from inside success"() {
        when:
        FactorialTrampolineGroovyBug.testFactorialSuccess()
        
        then:
        noExceptionThrown()
    }

    def "5! -> 120 from inside failure"() {
        when:
        FactorialTrampolineGroovyBug.testFactorialFail()

        then:
        thrown(PowerAssertionError)
    }
}
