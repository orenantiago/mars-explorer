package br.com.elo7.marsexplorer.controller

import br.com.elo7.marsexplorer.Elo7Test
import br.com.elo7.marsexplorer.model.Land
import br.com.elo7.marsexplorer.model.Probe
import br.com.elo7.marsexplorer.service.LandService
import br.com.elo7.marsexplorer.service.ProbeService
import br.com.six2six.fixturefactory.Fixture
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc

import static groovy.json.JsonOutput.toJson
import static groovy.json.JsonOutput.toJson
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class ProbeControllerTest extends Elo7Test {
    @Autowired
    MockMvc mvc

    @Autowired
    ProbeService service

    def "when find probe by id should return 'ok' status"() {
        given:
        Probe probe = Fixture.from(Probe.class).gimme("valid")
        def existing = service.create(probe)

        when:
        def results = mvc.perform(get("/probes/${existing.id}"))

        then:
        results.andExpect(status().isOk())
    }

    def "when updating probe should return 'ok' status"() {
        given:
        Probe probe = Fixture.from(Probe.class).gimme("valid")
        def existing = service.create(probe)

        when:
        def results = mvc.perform(put("/probes/${existing.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(probe)))


        then:
        results.andExpect(status().isOk())
    }

    def "when find all probes should return 'ok' status"() {
        when:
        def results = mvc.perform(get("/probes"))

        then:
        results.andExpect(status().isOk())
    }

    def "when deleting probe should return 'no content' status"() {
        given:
        Probe probe = Fixture.from(Probe.class).gimme("valid")
        def existing = service.create(probe)

        when:
        def results = mvc.perform(delete("/probes/${existing.id}"))

        then:
        results.andExpect(status().isNoContent())
    }

}
