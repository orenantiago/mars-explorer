package br.com.elo7.marsexplorer.controller;

import br.com.elo7.marsexplorer.model.Land;
import br.com.elo7.marsexplorer.model.Probe;
import br.com.elo7.marsexplorer.service.LandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LandController {
    @Autowired
    private LandService service;

    @GetMapping("/lands/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Land getLand(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/lands")
    @ResponseStatus(HttpStatus.OK)
    public List<Land> findAll() {
        return service.findAll();
    }

    @PostMapping(value = "/lands")
    @ResponseStatus(HttpStatus.CREATED)
    public Land create(@RequestBody Land land) {
        return service.create(land);
    }

    @PutMapping("/lands/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Land update(@PathVariable Long id, @RequestBody Land land) {
        return service.update(id, land);
    }

    @DeleteMapping("/lands/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PostMapping(value = "/lands/{id}/move-probes")
    @ResponseStatus(HttpStatus.OK)
    public Land moveProbes(@PathVariable Long id) {
        return service.moveProbes(id);
    }
}
