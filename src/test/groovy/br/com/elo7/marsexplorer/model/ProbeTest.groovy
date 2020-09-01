package br.com.elo7.marsexplorer.model

import br.com.elo7.marsexplorer.Elo7Test
import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule

class ProbeTest extends Elo7Test {
    def "given valid Probe should not throw errors" () {
        given:
        Probe probe = Fixture.from(Probe.class).gimme("valid")

        when:
        def errors = validator.validate(probe)

        then:
        errors.isEmpty()
    }

    def "given Probe without direction should throw error" () {
        given:
        Probe probe = Fixture.from(Probe.class).gimme("valid")
        probe.direction = null

        when:
        def errors = validator.validate(probe)

        then:
        errors.size() == 1
        errors.getAt(0).message == "direction must not be null"
    }

    def "given Probe with North direction when turn left should face West" () {
        given:
        Probe probe = Fixture.from(Probe.class).gimme("valid", new Rule () {{
            add("direction", Direction.N)
            add("movements", Arrays.asList(Movement.L))
        }})

        when:
        probe.moveFrom(new Point(0,0))

        then:
        probe.direction == Direction.W
    }

    def "given Probe facing West when turn right should face North" () {
        given:
        Probe probe = Fixture.from(Probe.class).gimme("valid", new Rule () {{
            add("direction", Direction.W)
            add("movements", Arrays.asList(Movement.R))
        }})

        when:
        probe.moveFrom(new Point(0,0))

        then:
        probe.direction == Direction.N
    }

    def "given Probe facing East when turn right should face South" () {
        given:
        Probe probe = Fixture.from(Probe.class).gimme("valid", new Rule () {{
            add("direction", Direction.E)
            add("movements", Arrays.asList(Movement.R))
        }})

        when:
        probe.moveFrom(new Point(0,0))

        then:
        probe.direction == Direction.S
    }

    def "given Probe facing North should move accordingly" () {
        given:
        Probe probe = Fixture.from(Probe.class).gimme("valid", new Rule () {{
            add("direction", Direction.N)
            add("movements", Arrays.asList(Movement.M))
        }})
        Point initialPosition = Fixture.from(Point.class).gimme("valid")

        when:
        List<Point> positions = probe.moveFrom(initialPosition)

        then:
        positions.get(1) == Point.at(initialPosition.x, initialPosition.y + 1)
        probe.direction == Direction.N
    }

    def "given Probe facing East should move accordingly" () {
        given:
        Probe probe = Fixture.from(Probe.class).gimme("valid", new Rule () {{
            add("direction", Direction.E)
            add("movements", Arrays.asList(Movement.M))
        }})
        Point initialPosition = Fixture.from(Point.class).gimme("valid")

        when:
        List<Point> positions = probe.moveFrom(initialPosition)

        then:
        positions.get(1) == Point.at(initialPosition.x + 1, initialPosition.y)
        probe.direction == Direction.E
    }

    def "given Probe facing South should move accordingly" () {
        given:
        Probe probe = Fixture.from(Probe.class).gimme("valid", new Rule () {{
            add("direction", Direction.S)
            add("movements", Arrays.asList(Movement.M))
        }})
        Point initialPosition = Fixture.from(Point.class).gimme("valid")

        when:
        List<Point> positions = probe.moveFrom(initialPosition)

        then:
        positions.get(1) == Point.at(initialPosition.x, initialPosition.y - 1)
        probe.direction == Direction.S
    }

    def "given Probe facing West should move accordingly" () {
        given:
        Probe probe = Fixture.from(Probe.class).gimme("valid", new Rule () {{
            add("direction", Direction.W)
            add("movements", Arrays.asList(Movement.M))
        }})
        Point initialPosition = Fixture.from(Point.class).gimme("valid")

        when:
        List<Point> positions = probe.moveFrom(initialPosition)

        then:
        positions.get(1) == Point.at(initialPosition.x-1, initialPosition.y)
        probe.direction == Direction.W
    }

    def "given Probe with movements that don't change position should not move" () {
        given:
        Probe probe = Fixture.from(Probe.class).gimme("valid", new Rule () {{
            add("direction", Direction.W)
            add("movements", Arrays.asList(Movement.R, Movement.L))
        }})
        Point initialPosition = Fixture.from(Point.class).gimme("valid")

        when:
        List<Point> positions = probe.moveFrom(initialPosition)

        then:
        positions.get(0) == initialPosition
        positions.size() == 1
    }

}
