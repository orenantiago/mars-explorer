package br.com.elo7.marsexplorer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MarsExplorerApplicationTest extends Specification {

    @Autowired
    Environment environment

    def "application should start up"() {
        expect:
        environment != null
    }
}
