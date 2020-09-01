package br.com.elo7.marsexplorer.controller

import br.com.elo7.marsexplorer.Elo7Test
import br.com.elo7.marsexplorer.model.Land
import br.com.elo7.marsexplorer.service.LandService
import br.com.six2six.fixturefactory.Fixture
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static groovy.json.JsonOutput.toJson

@SpringBootTest
@AutoConfigureMockMvc
class LandControllerTest extends Elo7Test {
    @Autowired
    MockMvc mvc

    @Autowired
    LandService service

    def "when find land by id should return 'ok' status"() {
        given:
        Land land = Fixture.from(Land.class).gimme("valid")
        def existing = service.create(land)

        when:
        def results = mvc.perform(get("/lands/${existing.id}"))

        then:
        results.andExpect(status().isOk())
    }

    def "when creating land should return 'created' status"() {
        given:
        Land land = Fixture.from(Land.class).gimme("valid")

        when:
        def results = mvc.perform(post("/lands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(land)))

        then:
        results.andExpect(status().isCreated())
    }

    def "when moving probes on land should return 'ok' status"() {
        given:
        Land land = Fixture.from(Land.class).gimme("valid")
        def existing = service.create(land)

        when:
        def results = mvc.perform(post("/lands/${existing.id}/move-probes"))

        then:
        results.andExpect(status().isOk())
    }

    def "when updating land should return 'ok' status"() {
        given:
        Land land = Fixture.from(Land.class).gimme("valid")
        def existing = service.create(land)

        when:
        def results = mvc.perform(put("/lands/${existing.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(land)))


        then:
        results.andExpect(status().isOk())
    }

    def "when deleting land should return 'no content' status"() {
        given:
        Land land = Fixture.from(Land.class).gimme("valid")
        def existing = service.create(land)

        when:
        def results = mvc.perform(delete("/lands/${existing.id}"))

        then:
        results.andExpect(status().isNoContent())
    }

}
