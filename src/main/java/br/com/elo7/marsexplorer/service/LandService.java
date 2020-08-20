package br.com.elo7.marsexplorer.service;

import br.com.elo7.marsexplorer.model.Land;
import br.com.elo7.marsexplorer.model.Probe;
import br.com.elo7.marsexplorer.repository.LandRepository;
import br.com.elo7.marsexplorer.validation.Errors;
import br.com.elo7.marsexplorer.validation.MarsExplorerError;
import br.com.elo7.marsexplorer.validation.MarsExplorerValidator;
import br.com.elo7.marsexplorer.validation.exceptions.BadRequestException;
import br.com.elo7.marsexplorer.validation.exceptions.NotFoundException;
import br.com.elo7.marsexplorer.validation.exceptions.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class LandService {
    @Autowired
    private LandRepository repository;

    @Autowired
    private MarsExplorerValidator validator;

    @Autowired
    private ProbeService probeService;

    public Land create(Land land) {
        validate(land);
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
        validate(land);
        return repository.save(land);
    }

    public void validate(Land land) {
        validator.throwableValidate(land);
        putProbesOnField(land);
    }

    public void putProbesOnField(Land land) {
        if (land.getProbes() != null) {
            land.getProbes().stream().forEach(probe -> putProbe(land, probe));
        }
    }

    public void putProbe(Land land, Probe probe) {
        Probe probeInPosition = land.probeInPosition(probe.getPosition());
        if (probeInPosition != null) {
            throw new UnprocessableEntityException()
                    .withErrors(new MarsExplorerError(String.format("two probes put on same space")));
        }
        land.getPositionProbeMap().put(probe.getPosition(), probeService.findOrCreate(probe));
    }

    public void deleteById(Long id) {
        Land found = findById(id);
        repository.delete(found);
    }

}
