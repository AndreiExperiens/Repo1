package com.exampleRecipes.service;

import com.exampleRecipes.commands.InstructionsCommand;
import com.exampleRecipes.converters.InstructionsCommandToInsructions;
import com.exampleRecipes.converters.InstructionsToInstructionsCommand;
import com.exampleRecipes.domain.Instructions;
import com.exampleRecipes.repositories.InstructionsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class InstructionServiceImp implements InstructionService{
        private final InstructionsRepository instructionsRepository;
        private final InstructionsCommandToInsructions instructionsCommandToIsructions;
        private final InstructionsToInstructionsCommand instructionsToInstructionsCommand;


    public InstructionServiceImp(InstructionsRepository instructionsRepository, InstructionsCommandToInsructions instructionsCommandToIsructions, InstructionsToInstructionsCommand instructionsToInstructionsCommand) {
        this.instructionsRepository = instructionsRepository;
        this.instructionsCommandToIsructions = instructionsCommandToIsructions;

        this.instructionsToInstructionsCommand = instructionsToInstructionsCommand;
    }

    @Override
    public Set<Instructions> getInstructions() {
        log.debug("I'm in the service");

        Set<Instructions> instrctionsSet = new HashSet<>();
        instructionsRepository.findAll().iterator().forEachRemaining(instrctionsSet::add);
        return instrctionsSet;
    }

    @Override
    public Instructions findById(Long l) {
        Optional<Instructions> instructionsOptional = instructionsRepository.findById(l);

        if(!instructionsOptional.isPresent()){
            throw new RuntimeException("Instruct is not found!");
        }

        return instructionsOptional.get();
    }

    @Override
    @Transactional
    public InstructionsCommand findCommandById(Long l) {
        return instructionsToInstructionsCommand.convert(findById(l));
    }


    @Override
    @Transactional
    public InstructionsCommand saveInstructionsCommand(InstructionsCommand command) {
        Instructions detachedInstructions =instructionsCommandToIsructions.convert(command);

        Instructions saveInstructions = instructionsRepository.save(detachedInstructions);
        log.debug("Saved RecipeId:" + saveInstructions.getId());
        return instructionsToInstructionsCommand.convert(saveInstructions);

    }

    @Override
    public void deleteById(Long idToDelete) {
        instructionsRepository.deleteById(idToDelete);
    }


}
