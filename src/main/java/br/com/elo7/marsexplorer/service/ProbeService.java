package br.com.elo7.marsexplorer.service;

import br.com.elo7.marsexplorer.model.Probe;
import br.com.elo7.marsexplorer.repository.ProbeRepository;
import br.com.elo7.marsexplorer.exception.errors.Errors;
import br.com.elo7.marsexplorer.validation.MarsExplorerValidator;
import br.com.elo7.marsexplorer.exception.exceptions.BadRequestException;
import br.com.elo7.marsexplorer.exception.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProbeService {
    @Autowired
    private ProbeRepository repository;

    @Autowired
    private MarsExplorerValidator validator;

    public Probe create(Probe probe) {
        validator.throwableValidate(probe);
        return repository.save(probe);
    }
    
    public Probe findById(Long id) {
        if (id == null)
            throw new BadRequestException().withErrors(Errors.ID_REQUIRED);
        return repository.findById(id).orElseThrow(() -> new NotFoundException().withErrors(Errors.PROBE_NOT_FOUND(id)));
    }

    public List<Probe> findAll() {
        return repository.findAll();
    }

    public Probe update(Long id, Probe probe) {
        findById(id);
        validator.throwableValidate(probe);
        probe.setId(id);
        return repository.save(probe);
    }
}
