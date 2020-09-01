package br.com.elo7.marsexplorer.controller;

import br.com.elo7.marsexplorer.model.Land;
import br.com.elo7.marsexplorer.model.Probe;
import br.com.elo7.marsexplorer.service.LandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class LandController {
    @Autowired
    private LandService service;

    @GetMapping("/lands/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Land getLand(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping(value = "/lands")
    @ResponseStatus(HttpStatus.CREATED)
    public Land create(@RequestBody Land land) {
        return service.create(land);
    }

}
