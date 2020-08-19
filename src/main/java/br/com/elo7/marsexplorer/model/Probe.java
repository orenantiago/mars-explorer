package br.com.elo7.marsexplorer.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "probes")
public class Probe extends Reusable implements Serializable {
    @NotNull
    private Direction direction;
}
