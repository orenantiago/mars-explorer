package br.com.elo7.marsexplorer.model

import br.com.elo7.marsexplorer.Elo7Test
import br.com.six2six.fixturefactory.Fixture

class ProbeTest extends Elo7Test {
    def "given valid Probe should not thow errors" () {
        given:
        def probe = Fixture.from(Probe.class).gimme("valid")

        when:
        def errors = validator.validate(probe)

        then:
        errors.isEmpty()
    }

    def "given valid Probe without direction should thow error" () {
        given:
        def probe = Fixture.from(Probe.class).gimme("valid")
        probe.direction = null

        when:
        def errors = validator.validate(probe)

        then:
        errors.size() == 1
        errors.getAt(0).message == "direction must not be null"
    }
}
