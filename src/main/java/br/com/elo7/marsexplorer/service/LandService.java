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

import java.util.HashMap;

@Service
public class LandService {
    @Autowired
    private LandRepository repository;

    @Autowired
    private MarsExplorerValidator validator;

    @Autowired
    private ProbeService probeService;

    public Land create(Land land) {
        validator.throwableValidate(land);
        checkReferences(land);
        return repository.save(land);
    }

    public Land findById(Long id) {
        if (id == null)
            throw new BadRequestException().withErrors(Errors.ID_REQUIRED);
        return repository.findById(id).orElseThrow(() -> new NotFoundException().withErrors(Errors.ID_REQUIRED));
    }

    public Land update(Long id, Land land) {
        findById(id);
        land.setId(id);
        validator.throwableValidate(land);
        checkReferences(land);
        return repository.save(land);
    }

    public void validate(Land land) {
        validator.throwableValidate(land);
        checkReferences(land);
    }

    private void checkReferences(Land land) {
        if (land.hasProbes()) {
            land.getPositionProbeMap().forEach((position, probe) -> probe = probeService.findOrCreate(probe));
        }
    }

    public void deleteById(Long id) {
        Land found = findById(id);
        repository.delete(found);
    }

}
