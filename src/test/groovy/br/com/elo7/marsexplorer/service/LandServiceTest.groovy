package br.com.elo7.marsexplorer.service

import br.com.elo7.marsexplorer.Elo7Test
import br.com.elo7.marsexplorer.model.Direction
import br.com.elo7.marsexplorer.model.Land
import br.com.elo7.marsexplorer.model.Movement
import br.com.elo7.marsexplorer.model.Point
import br.com.elo7.marsexplorer.model.Probe
import br.com.elo7.marsexplorer.repository.LandRepository
import br.com.elo7.marsexplorer.validation.exceptions.NotFoundException
import br.com.elo7.marsexplorer.validation.exceptions.UnprocessableEntityException
import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LandServiceTest extends Elo7Test {
    @Autowired
    LandService service

    @Autowired
    LandRepository repository

    @Autowired
    ProbeService probeService

    def "given valid Land should create it" () {
        given:
        def land = landToCreate()

        when:
        def created = service.create(land)
        def found = repository.findById(created.id)

        then:
        created
        found
    }

    def "given invalid Land should validate it when creating" () {
        given:
        def land = landToCreate()
        land.size = null

        when:
        def created = service.create(land)

        then:
        thrown UnprocessableEntityException
    }

    def "given Land with unknown Probe should not create it" () {
        given:
        def land = landToCreate()
        def probe = Fixture.from(Probe.class).gimme("valid")
        def position = new Point(0, 0);
        probe.id = 123

        land.probes.put(position, probe)

        when:
        service.create(land)

        then:
        thrown NotFoundException
    }

    def "given Land with Probe without id should create Land and Probe" () {
        given:
        def land = landToCreate()
        def probe = Fixture.from(Probe.class).gimme("valid")
        def position = new Point(0, 0);

        land.probes.put(position, probe)

        when:
        def createdLand = service.create(land)
        def probeFromLand = createdLand.probes.get(position)
        def createdProbe = probeService.findById(probeFromLand.id)

        then:
        createdLand
        createdProbe.id == probeFromLand.id
    }

    def "given Land with Probes that will collide should throw error" () {
        given:
        def land = landToCreate()
        def probe = Fixture.from(Probe.class).gimme("valid", new Rule() {{
            add("direction", Direction.N)
            add("movements", Arrays.asList(Movement.M, Movement.R, Movement.M))
        }})

        def probe2 = Fixture.from(Probe.class).gimme("valid")

        land.probes.put(Point.at(0,0), probe)
        land.probes.put(Point.at(1,1), probe2)


        when:
        service.create(land)

        then:
        thrown UnprocessableEntityException
    }

    def "given Land with Probe with movements should execute them on land creation" () {
        given:
        def land = landToCreate()
        def probe = Fixture.from(Probe.class).gimme("valid", new Rule() {{
            add("direction", Direction.N)
            add("movements", Arrays.asList(Movement.M, Movement.R, Movement.M))
        }})

        land.probes.put(Point.at(0,0), probe)


        when:
        def createdLand = service.create(land)
        def probeFromLand = createdLand.probes.get(Point.at(1,1))
        def createdProbe = probeService.findById(probeFromLand.id)

        then:
        createdLand
        probeFromLand.id == createdProbe.id
        createdProbe.direction == Direction.E
    }

//    def "given Land with Probe  creation" () {
//        given:
//        Land land = landToCreate()
//        Probe probe = Fixture.from(Probe.class).gimme("valid", new Rule() {{
//            add("direction", Direction.N)
//            add("movements", Arrays.asList(Movement.M, Movement.R, Movement.M))
//            add("position", Point.at(0,0))
//        }})
//        Land createdLand = service.create(land)
//        probe.land = createdLand
//        Probe createdProbe = probeService.create(probe)
//
//        when:
//        def foundLand = service.findById(createdLand.id)
//
//        then:
//        foundLand
//        createdProbe
//    }
    def "given Land with Probe put outside land should not create it" () {
        given:
        def land = landToCreate()
        def probe = Fixture.from(Probe.class).gimme("valid")
        def position = new Point(land.size.x + 1, 0)

        land.probes.put(position, probe)

        when:
        def createdLand = service.create(land)

        then:
        thrown UnprocessableEntityException
    }

    def "given existing Land should find it" () {
        given:
        def land = landToCreate()
        def existing = service.create(land)

        when:
        def found = service.findById(existing.id)

        then:
        found != null
    }

    def "given non existing Land should not find it" () {
        given:
        def unknownId = 123

        when:
        def found = service.findById(unknownId)

        then:
        thrown NotFoundException
    }

    def "given non existing Land should not delete it" () {
        given:
        def unknownId = 123

        when:
        service.deleteById(unknownId)

        then:
        thrown NotFoundException
    }

    def "given existing Land should delete it" () {
        given:
        def land = landToCreate()
        def existing = service.create(land)

        when:
        service.deleteById(existing.id)
        def found = repository.findById(existing.id).orElse(null)
        then:
        found == null
    }

    def "given non existing Land should not update it" () {
        given:
        def unknownId = 123
        def land = landToCreate()

        when:
        service.update(unknownId, land)

        then:
        thrown NotFoundException
    }

    def "given existing Land should update it" () {
        given:
        def land = landToCreate()
        def existing = service.create(land)
        def newLand = Fixture.from(Land.class).gimme("valid")

        when:
        service.update(existing.id, newLand)
        def found = repository.findById(existing.id).orElse(null)

        then:
        found
        found.size == newLand.size
    }

    def "given invalid Land should validate it when updating" () {
        given:
        def land = landToCreate()
        def existing = service.create(land)
        land.size = null
        when:
        service.update(existing.id, land)

        then:
        thrown UnprocessableEntityException
    }

    private def landToCreate() {
        def land = Fixture.from(Land.class).gimme("valid")
        return land
    }
}
