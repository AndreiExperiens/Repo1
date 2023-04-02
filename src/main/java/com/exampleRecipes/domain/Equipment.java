package com.exampleRecipes.domain;



import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"instructions"})
@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uam;
    @ManyToOne
    private Instructions instructions;

    public Equipment() {

    }

    public Equipment(String description, BigDecimal amount, UnitOfMeasure uam) {
        this.description = description;
        this.amount = amount;
        this.uam = uam;
    }
    public Equipment(String description, BigDecimal amount, UnitOfMeasure uam, Instructions instructions) {
        this.description = description;
        this.amount = amount;
        this.uam = uam;
        this.instructions = instructions;
    }


}
