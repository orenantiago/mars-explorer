package br.com.elo7.marsexplorer.model

import br.com.elo7.marsexplorer.Elo7Test
import br.com.six2six.fixturefactory.Fixture

class LandTest extends Elo7Test {
    def "given valid Land should not throw errors" () {
        given:
        def land = Fixture.from(Land.class).gimme("valid")

        when:
        def errors = validator.validate(land)

        then:
        errors.isEmpty()
    }

    def "given Land without size should throw error" () {
        given:
        def land = Fixture.from(Land.class).gimme("valid")
        land.size = null

        when:
        def errors = validator.validate(land)

        then:
        errors.size() == 1
        errors.getAt(0).message == "size must not be null"
    }

    def "given Land with size without x should throw error" () {
        given:
        def land = Fixture.from(Land.class).gimme("valid")
        land.size.x = null

        when:
        def errors = validator.validate(land)

        then:
        errors.size() == 1
        errors.getAt(0).message == "size.x must not be null"
    }

    def "given Land with size with negative x should throw error" () {
        given:
        def land = Fixture.from(Land.class).gimme("valid")
        land.size.x = -1

        when:
        def errors = validator.validate(land)

        then:
        errors.size() == 1
        errors.getAt(0).message == "size.x must be greater than or equal to 1"
    }

    def "given Land with size without y should throw error" () {
        given:
        def land = Fixture.from(Land.class).gimme("valid")
        land.size.y = null

        when:
        def errors = validator.validate(land)

        then:
        errors.size() == 1
        errors.getAt(0).message == "size.y must not be null"
    }

    def "given Land with size with negative y should throw error" () {
        given:
        def land = Fixture.from(Land.class).gimme("valid")
        land.size.y = -1

        when:
        def errors = validator.validate(land)

        then:
        errors.size() == 1
        errors.getAt(0).message == "size.y must be greater than or equal to 1"
    }
}
