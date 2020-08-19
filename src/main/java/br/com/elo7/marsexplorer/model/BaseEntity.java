package br.com.elo7.marsexplorer.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;

@Data
public class BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    private String id;

}
