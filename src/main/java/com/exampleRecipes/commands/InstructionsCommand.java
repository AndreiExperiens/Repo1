package com.exampleRecipes.commands;

import com.exampleRecipes.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class InstructionsCommand {
    private Long id;
    private String description;
    private Integer pregTime;
    private Integer instTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<EquipmentCommand> equipaments = new HashSet<>();
    private Difficulty difficulty;
    private NotesCommand notes;
    private Set<CategoryCommand> categories = new HashSet<>();
}
