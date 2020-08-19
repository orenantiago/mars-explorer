package br.com.elo7.marsexplorer

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class MarsExplorerApplicationTest extends Specification {
    def "context loads" () {
        expect:
        true == true
    }
}
