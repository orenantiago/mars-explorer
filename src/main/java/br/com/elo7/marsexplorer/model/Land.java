package br.com.elo7.marsexplorer.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lands")
public class Land extends BaseEntity implements Serializable {
    @NotNull
    @Valid
    private Position size;

    @OneToMany
    @MapKey(name = "position")
    Map<Position, Probe> positionProbeMap = new HashMap<>();

    @Transient
    List<Probe> probes;

    public Probe probeInPosition(Position position) {
        return positionProbeMap.get(position);
    }

    public Boolean hasProbes() {
        return positionProbeMap != null && positionProbeMap.size() != 0;
    }
}
