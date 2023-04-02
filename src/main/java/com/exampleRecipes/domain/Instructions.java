package com.exampleRecipes.domain;



import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
public class Instructions {
        // Clasa principala
        // Tabelul principal
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer pregTime;
    private Integer instTime;
    private String url;
    private Integer servings;
    private String source;

    @Lob
    private String directions;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instructions")
    private Set<Price> prices = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instructions")
    private Set<Equipment> equipment = new HashSet<>();

    @Lob
    private Byte[] image;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;
    //Relatie tabel Instructioons cu tabel equipament
    @ManyToMany
    @JoinTable(name = "instructions_category",
            joinColumns = @JoinColumn(name = "instructions_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();


    public Set<Equipment> getEquipments() {
        return equipment;
    }


}
