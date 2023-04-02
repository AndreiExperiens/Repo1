package com.exampleRecipes.service;

import com.exampleRecipes.commands.InstructionsCommand;
import com.exampleRecipes.domain.Instructions;

import java.util.Set;

public interface InstructionService {

    Set<Instructions> getInstructions();

    Instructions findById(Long l);

    InstructionsCommand findCommandById(Long l);
    InstructionsCommand saveInstructionsCommand(InstructionsCommand command);

    void deleteById(Long idToDelete);

}
