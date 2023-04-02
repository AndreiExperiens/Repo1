package com.exampleRecipes.domain;


import com.exampleRecipes.commands.UnitOfMeasureCommand;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    //Aici trebuie sa schimb in unitatea de masura pentru tablouri


}
