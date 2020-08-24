package br.com.elo7.marsexplorer.model

import br.com.elo7.marsexplorer.Elo7Test
import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule

class ProbeTest extends Elo7Test {
    def "given valid Probe should not throw errors" () {
        given:
        def probe = Fixture.from(Probe.class).gimme("valid")

        when:
        def errors = validator.validate(probe)

        then:
        errors.isEmpty()
    }

    def "given Probe without direction should throw error" () {
        given:
        def probe = Fixture.from(Probe.class).gimme("valid")
        probe.direction = null

        when:
        def errors = validator.validate(probe)

        then:
        errors.size() == 1
        errors.getAt(0).message == "direction must not be null"
    }

    def "given Probe with North direction when turn left should face West" () {
        given:
        def probe = Fixture.from(Probe.class).gimme("valid", new Rule () {{
            add("direction", Direction.N)
            add("movements", Arrays.asList(Movement.L))
        }})

        when:
        probe.move(new Position(0,0))

        then:
        probe.direction == Direction.W
    }

    def "given Probe facing West when turn right should face North" () {
        given:
        def probe = Fixture.from(Probe.class).gimme("valid", new Rule () {{
            add("direction", Direction.W)
            add("movements", Arrays.asList(Movement.R))
        }})

        when:
        probe.move(new Position(0,0))

        then:
        probe.direction == Direction.N
    }

    def "given Probe facing East when turn right should face South" () {
        given:
        def probe = Fixture.from(Probe.class).gimme("valid", new Rule () {{
            add("direction", Direction.E)
            add("movements", Arrays.asList(Movement.R))
        }})

        when:
        probe.move(new Position(0,0))

        then:
        probe.direction == Direction.S
    }

    def "given Probe facing North should move accordingly" () {
        given:
        def probe = Fixture.from(Probe.class).gimme("valid", new Rule () {{
            add("direction", Direction.N)
            add("movements", Arrays.asList(Movement.M))
        }})
        def initialPosition = Fixture.from(Position.class).gimme("valid")

        when:
        def positions = probe.move(initialPosition)

        then:
        positions.get(0) == Position.at(initialPosition.x, initialPosition.y + 1)
        probe.direction == Direction.N
    }

    def "given Probe facing East should move accordingly" () {
        given:
        def probe = Fixture.from(Probe.class).gimme("valid", new Rule () {{
            add("direction", Direction.E)
            add("movements", Arrays.asList(Movement.M))
        }})
        def initialPosition = Fixture.from(Position.class).gimme("valid")

        when:
        def positions = probe.move(initialPosition)

        then:
        positions.get(0) == Position.at(initialPosition.x + 1, initialPosition.y)
        probe.direction == Direction.E
    }

    def "given Probe facing South should move accordingly" () {
        given:
        def probe = Fixture.from(Probe.class).gimme("valid", new Rule () {{
            add("direction", Direction.S)
            add("movements", Arrays.asList(Movement.M))
        }})
        def initialPosition = Fixture.from(Position.class).gimme("valid")

        when:
        def positions = probe.move(initialPosition)

        then:
        positions.get(0) == Position.at(initialPosition.x, initialPosition.y - 1)
        probe.direction == Direction.S
    }

    def "given Probe facing West should move accordingly" () {
        given:
        def probe = Fixture.from(Probe.class).gimme("valid", new Rule () {{
            add("direction", Direction.W)
            add("movements", Arrays.asList(Movement.M))
        }})
        def initialPosition = Fixture.from(Position.class).gimme("valid")

        when:
        def positions = probe.move(initialPosition)

        then:
        positions.get(0) == Position.at(initialPosition.x-1, initialPosition.y)
        probe.direction == Direction.W
    }
}
