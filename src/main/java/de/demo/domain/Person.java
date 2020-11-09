package de.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Data
public class Person {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @NotEmpty
    @Size(min=2, max=30, message = "Name size must be between 2 and 30")
    private String name;

    public Person(String name) {
        this.name = name;
    }
}
