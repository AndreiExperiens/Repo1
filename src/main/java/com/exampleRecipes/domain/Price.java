package com.exampleRecipes.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"instructionst"})
@Entity
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer priceRecipes;
 //   private BigDecimal amount;
    private String description;

    @ManyToOne
    private Instructions instructions;

}
