package com.exampleRecipes.service;

import com.exampleRecipes.commands.InstructionsCommand;
import com.exampleRecipes.converters.InstructionsCommandToInsructions;
import com.exampleRecipes.converters.InstructionsToInstructionsCommand;
import com.exampleRecipes.repositories.InstructionsRepository;
import com.exampleRecipes.domain.Instructions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InstructionServiceTest {

    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    InstructionService instructionService;

    @Autowired
    InstructionsRepository instructionsRepository;

    @Autowired
    InstructionsCommandToInsructions instructionsCommandToInsructions;

    @Autowired
    InstructionsToInstructionsCommand instructionsToInstructionsCommand;

    @Transactional
    @Test
    public void testSaveOfDescription() throws Exception {
        //given
        Iterable<Instructions> instructions = instructionsRepository.findAll();
        Instructions testInstructions = instructions.iterator().next();
        InstructionsCommand testInstructCommand = instructionsToInstructionsCommand.convert(testInstructions);

        //when
        testInstructCommand.setDescription(NEW_DESCRIPTION);
        InstructionsCommand savedInstructionsCommand = instructionService.saveInstructionsCommand(testInstructCommand);

        //then
        assertEquals(NEW_DESCRIPTION, savedInstructionsCommand.getDescription());
        assertEquals(testInstructions.getId(), savedInstructionsCommand.getId());
        assertEquals(testInstructions.getCategories().size(), savedInstructionsCommand.getCategories().size());
        assertEquals(testInstructions.getEquipments().size(), savedInstructionsCommand.getEquipaments().size());
    }
}