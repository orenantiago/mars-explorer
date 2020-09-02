package br.com.elo7.marsexplorer.controller;

import br.com.elo7.marsexplorer.model.Probe;
import br.com.elo7.marsexplorer.service.ProbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProbeController {
    @Autowired
    private ProbeService service;

    @GetMapping("/probes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Probe getLand(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping(value = "/probes")
    @ResponseStatus(HttpStatus.CREATED)
    public Probe create(@RequestBody Probe probe) {
        return service.create(probe);
    }

    @PutMapping("/probes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Probe update(@PathVariable Long id, @RequestBody Probe probe) {
        return service.update(id, probe);
    }

    @DeleteMapping("/probes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
