package br.com.elo7.marsexplorer.controller;

import br.com.elo7.marsexplorer.model.Land;
import br.com.elo7.marsexplorer.service.LandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class LandController {
    @Autowired
    private LandService service;

    @GetMapping("/lands/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Land getLand(@PathVariable Long id) {
        return service.findById(id);
    }
}
