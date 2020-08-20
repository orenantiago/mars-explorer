package br.com.elo7.marsexplorer.service

import br.com.elo7.marsexplorer.Elo7Test
import br.com.elo7.marsexplorer.model.Probe
import br.com.elo7.marsexplorer.repository.ProbeRepository
import br.com.elo7.marsexplorer.validation.exceptions.NotFoundException
import br.com.elo7.marsexplorer.validation.exceptions.UnprocessableEntityException
import br.com.six2six.fixturefactory.Fixture
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProbeServiceTest extends Elo7Test {
    @Autowired
    ProbeService service;

    @Autowired
    ProbeRepository repository;

    def "given valid Probe should create it" () {
        given:
        def probe = Fixture.from(Probe.class).gimme("valid")

        when:
        def created = service.create(probe)
        def found = repository.findById(created.id)

        then:
        created
        found
    }

    def "given invalid Probe should validate it when creating" () {
        given:
        def probe = Fixture.from(Probe.class).gimme("valid")
        probe.direction = null

        when:
        def created = service.create(probe)

        then:
        thrown UnprocessableEntityException
    }

    def "given existing Probe should find it" () {
        given:
        def probe = Fixture.from(Probe.class).gimme("valid")
        def existing = service.create(probe)

        when:
        def found = service.findById(existing.id)

        then:
        found != null
    }

    def "given non existing Probe should not find it" () {
        given:
        def unknownId = 123

        when:
        def found = service.findById(unknownId)

        then:
        thrown NotFoundException
    }

    def "given non existing Probe should not delete it" () {
        given:
        def unknownId = 123

        when:
        service.deleteById(unknownId)

        then:
        thrown NotFoundException
    }

    def "given existing Probe should delete it" () {
        given:
        def probe = Fixture.from(Probe.class).gimme("valid")
        def existing = service.create(probe)

        when:
        service.deleteById(existing.id)
        def found = repository.findById(existing.id).orElse(null)
        then:
        found == null
    }

    def "given non existing Probe should not update it" () {
        given:
        def unknownId = 123
        def probe = Fixture.from(Probe.class).gimme("valid")

        when:
        service.update(unknownId, probe)

        then:
        thrown NotFoundException
    }

    def "given existing Probe should update it" () {
        given:
        def probe = Fixture.from(Probe.class).gimme("valid")
        def existing = service.create(probe)
        def newProbe = Fixture.from(Probe.class).gimme("valid")

        when:
        service.update(existing.id, newProbe)
        def found = repository.findById(existing.id).orElse(null)

        then:
        found
        found.direction == newProbe.direction
    }

    def "given invalid Probe should validate it when updating" () {
        given:
        def probe = Fixture.from(Probe.class).gimme("valid")
        def existing = service.create(probe)
        probe.direction = null
        when:
        service.update(existing.id, probe)

        then:
        thrown UnprocessableEntityException
    }

}
