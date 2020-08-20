package br.com.elo7.marsexplorer.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "probes")
public class Probe extends BaseEntity implements Serializable {
    @NotNull
    private Direction direction;
    private Position position;
}
