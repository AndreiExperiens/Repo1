package com.exampleRecipes.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;
@Data
@EqualsAndHashCode(exclude = {"instructionst"})
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;



    @ManyToMany(mappedBy = "categories")
    private Set<Instructions> instructionst;

}
