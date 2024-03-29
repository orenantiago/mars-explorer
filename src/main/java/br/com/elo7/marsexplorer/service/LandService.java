package br.com.elo7.marsexplorer.service;

import br.com.elo7.marsexplorer.model.Land;
import br.com.elo7.marsexplorer.repository.LandRepository;
import br.com.elo7.marsexplorer.exception.errors.Errors;
import br.com.elo7.marsexplorer.exception.errors.MarsExplorerError;
import br.com.elo7.marsexplorer.validation.MarsExplorerValidator;
import br.com.elo7.marsexplorer.exception.exceptions.BadRequestException;
import br.com.elo7.marsexplorer.exception.exceptions.NotFoundException;
import br.com.elo7.marsexplorer.exception.exceptions.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LandService {
    @Autowired
    private LandRepository repository;

    @Autowired
    private MarsExplorerValidator validator;

    @Autowired
    private ProbeService probeService;

    public Land moveProbes(Long id) {
        Land land = findById(id);
        land.moveProbes();
        return repository.save(land);
    }

    public Land create(Land land) {
        validate(land);
        return repository.save(land);
    }

    public Land findById(Long id) {
        if (id == null)
            throw new BadRequestException().withErrors(Errors.ID_REQUIRED);
        return repository.findById(id).orElseThrow(() -> new NotFoundException().withErrors(Errors.LAND_NOT_FOUND(id)));
    }

    public List<Land> findAll() {
        return repository.findAll();
    }

    public Land update(Long id, Land land) {
        findById(id);
        land.setId(id);
        validate(land);
        return repository.save(land);
    }

    public void validate(Land land) {
        validator.throwableValidate(land);
        validateProbes(land);
    }

    public void validateProbes(Land land) {
        validateProbesPositions(land);
        land.getProbes().values().forEach(probe -> probe = probeService.create(probe));
    }

    public void validateProbesPositions(Land land) {
        List<MarsExplorerError> positionErrors = land.positions().stream()
                .filter(position -> !position.isInside(land))
                .map(position -> Errors.POSITION_OUTSIDE_LAND(position))
                .collect(Collectors.toList());
        if (!positionErrors.isEmpty()) {
            throw new UnprocessableEntityException().withErrors(positionErrors);
        }
    }

    public void deleteById(Long id) {
        Land found = findById(id);
        repository.delete(found);
    }

}
