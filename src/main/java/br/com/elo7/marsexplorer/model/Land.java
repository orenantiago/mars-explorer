package br.com.elo7.marsexplorer.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "lands")
public class Land extends BaseEntity implements Serializable {
    @NotNull
    @Valid
    private Size size;
}
