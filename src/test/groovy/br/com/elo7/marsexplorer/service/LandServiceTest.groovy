package br.com.elo7.marsexplorer.service

import br.com.elo7.marsexplorer.Elo7Test
import br.com.elo7.marsexplorer.model.Land
import br.com.elo7.marsexplorer.repository.LandRepository
import br.com.elo7.marsexplorer.validation.exceptions.NotFoundException
import br.com.elo7.marsexplorer.validation.exceptions.UnprocessableEntityException
import br.com.six2six.fixturefactory.Fixture
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LandServiceTest extends Elo7Test {
    @Autowired
    LandService service;

    @Autowired
    LandRepository repository;

    def "given valid Land should create it" () {
        given:
        def land = Fixture.from(Land.class).gimme("valid")

        when:
        def created = service.create(land)
        def found = repository.findById(created.id)

        then:
        created
        found
    }

    def "given invalid Land should validate it when creating" () {
        given:
        def land = Fixture.from(Land.class).gimme("valid")
        land.size = null

        when:
        def created = service.create(land)

        then:
        thrown UnprocessableEntityException
    }

    def "given existing Land should find it" () {
        given:
        def land = Fixture.from(Land.class).gimme("valid")
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
        def land = Fixture.from(Land.class).gimme("valid")
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
        def land = Fixture.from(Land.class).gimme("valid")

        when:
        service.update(unknownId, land)

        then:
        thrown NotFoundException
    }

    def "given existing Land should update it" () {
        given:
        def land = Fixture.from(Land.class).gimme("valid")
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
        def land = Fixture.from(Land.class).gimme("valid")
        def existing = service.create(land)
        land.size = null
        when:
        service.update(existing.id, land)

        then:
        thrown UnprocessableEntityException
    }

}
