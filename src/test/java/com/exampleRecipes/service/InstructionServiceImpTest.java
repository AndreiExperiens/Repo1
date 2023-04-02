package com.exampleRecipes.service;

import com.exampleRecipes.converters.InstructionsCommandToInsructions;
import com.exampleRecipes.converters.InstructionsToInstructionsCommand;
import com.exampleRecipes.domain.Instructions;
import com.exampleRecipes.repositories.InstructionsRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class InstructionServiceImpTest {

   InstructionServiceImp instructionService;

    @Mock
    InstructionsRepository instructionsRepository;

    @Mock
    InstructionsCommandToInsructions instructionsCommandToIsructions;

    @Mock
    InstructionsToInstructionsCommand instructionsToInstructionsCommand;

    @Before
    public void setUp() throws Exception {
     MockitoAnnotations.initMocks(this);

     instructionService = new InstructionServiceImp(instructionsRepository, instructionsCommandToIsructions, instructionsToInstructionsCommand);

    }

    @Test
    public void getInstructions() {

     Instructions instruction = new Instructions();
     HashSet instructData = new HashSet();
     instructData.add(instruction);

     when(instructionsRepository.findAll()).thenReturn(instructData);

     Set<Instructions> instructions = instructionService.getInstructions();

     assertEquals(instructions.size(), 1);
     verify(instructionsRepository, times(1)).findAll();
    }

 @Test
 public void getInstructTest() throws Exception {

  Instructions instructions = new Instructions();
  HashSet receipesData = new HashSet();
  receipesData.add(instructions);

  when(instructionService.getInstructions()).thenReturn(receipesData);

  Set<Instructions> recipes = instructionService.getInstructions();

  assertEquals(recipes.size(), 1);
  verify(instructionsRepository, times(1)).findAll();
  verify(instructionsRepository, never()).findById(anyLong());
 }

 @Test
 public void testDeleteById() throws Exception {

  //given
  Long idToDelete = Long.valueOf(2L);

  //when
  instructionService.deleteById(idToDelete);

  //no 'when', since method has void return type

  //then
  verify(instructionsRepository, times(1)).deleteById(anyLong());
 }


}