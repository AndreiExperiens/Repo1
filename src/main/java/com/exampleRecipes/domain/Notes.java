package com.exampleRecipes.domain;


import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"instructions"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Instructions instructions;

    @Lob
    private String instructNotes;

}
