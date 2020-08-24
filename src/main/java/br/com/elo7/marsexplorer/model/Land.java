package br.com.elo7.marsexplorer.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lands")
public class Land extends BaseEntity implements Serializable {
    @NotNull
    @Valid
    private Position size;

    @OneToMany
    Map<Position, Probe> probes = new HashMap<>();
}
