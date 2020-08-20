package br.com.elo7.marsexplorer.service;

import br.com.elo7.marsexplorer.model.Land;
import br.com.elo7.marsexplorer.model.Probe;
import br.com.elo7.marsexplorer.repository.LandRepository;
import br.com.elo7.marsexplorer.validation.Errors;
import br.com.elo7.marsexplorer.validation.MarsExplorerValidator;
import br.com.elo7.marsexplorer.validation.exceptions.BadRequestException;
import br.com.elo7.marsexplorer.validation.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LandService {
    @Autowired
    private LandRepository repository;

    @Autowired
    private MarsExplorerValidator validator;

    public Land create(Land land) {
        validator.throwableValidate(land);
        return repository.save(land);
    }

    public Land findById(Long id) {
        if (id == null)
            throw new BadRequestException().withErrors(Errors.ID_REQUIRED);
        return repository.findById(id).orElseThrow(() -> new NotFoundException().withErrors(Errors.ID_REQUIRED));
    }

    public Land update(Long id, Land land) {
        findById(id);
        validator.throwableValidate(land);
        land.setId(id);
        return repository.save(land);
    }

    public void deleteById(Long id) {
        Land found = findById(id);
        repository.delete(found);
    }

}
