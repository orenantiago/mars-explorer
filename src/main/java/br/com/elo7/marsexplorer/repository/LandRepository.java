package br.com.elo7.marsexplorer.repository;

import br.com.elo7.marsexplorer.model.Land;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandRepository extends CrudRepository<Land, Long> {
}
