import be.amedee.adventofcode.App
import spock.lang.Specification
import spock.lang.Unroll

class AppTest extends Specification {

    @Unroll
    def "Hello world"() {
        App app = new App()
        expect:
        app.getGreeting() == "Hello world."
    }

}
