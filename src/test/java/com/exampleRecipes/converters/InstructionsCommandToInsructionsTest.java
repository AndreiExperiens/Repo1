package com.exampleRecipes.converters;

import com.exampleRecipes.commands.CategoryCommand;
import com.exampleRecipes.commands.EquipmentCommand;
import com.exampleRecipes.commands.InstructionsCommand;
import com.exampleRecipes.commands.NotesCommand;
import com.exampleRecipes.domain.Instructions;
import com.exampleRecipes.domain.Difficulty;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InstructionsCommandToInsructionsTest {
    public static final Long INSTRUCTIONS_ID = 1L;
    public static final Integer INST_TIME = Integer.valueOf("5");
    public static final Integer PREG_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;

    InstructionsCommandToInsructions converter;


    @Before
    public void setUp() throws Exception {
        converter = new InstructionsCommandToInsructions(new CategoryCommandToCategory(),
                new EquipmentCommandToEquipment(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new InstructionsCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        InstructionsCommand instructionsCommand = new InstructionsCommand();
        instructionsCommand.setId(INSTRUCTIONS_ID);
        instructionsCommand.setInstTime(INST_TIME);
        instructionsCommand.setPregTime(PREG_TIME);
        instructionsCommand.setDescription(DESCRIPTION);
        instructionsCommand.setDifficulty(DIFFICULTY);
        instructionsCommand.setDirections(DIRECTIONS);
        instructionsCommand.setServings(SERVINGS);
        instructionsCommand.setSource(SOURCE);
        instructionsCommand.setUrl(URL);

        NotesCommand notes = new NotesCommand();
        notes.setId(NOTES_ID);

        instructionsCommand.setNotes(notes);

        CategoryCommand category = new CategoryCommand();
        category.setId(CAT_ID_1);

        CategoryCommand category2 = new CategoryCommand();
        category2.setId(CAT_ID2);

        instructionsCommand.getCategories().add(category);
        instructionsCommand.getCategories().add(category2);

        EquipmentCommand ingredient = new EquipmentCommand();
        ingredient.setId(INGRED_ID_1);

        EquipmentCommand ingredient2 = new EquipmentCommand();
        ingredient2.setId(INGRED_ID_2);

        instructionsCommand.getEquipaments().add(ingredient);
        instructionsCommand.getEquipaments().add(ingredient2);

        //when
        Instructions instructions = converter.convert(instructionsCommand);

        assertNotNull(instructions);
        assertEquals(INSTRUCTIONS_ID, instructions.getId());
        assertEquals(INST_TIME, instructions.getInstTime());
        assertEquals(PREG_TIME, instructions.getPregTime());
        assertEquals(DESCRIPTION, instructions.getDescription());
        assertEquals(DIFFICULTY, instructions.getDifficulty());
        assertEquals(DIRECTIONS, instructions.getDirections());
        assertEquals(SERVINGS, instructions.getServings());
        assertEquals(SOURCE, instructions.getSource());
        assertEquals(URL, instructions.getUrl());
        assertEquals(NOTES_ID, instructions.getNotes().getId());
        assertEquals(2, instructions.getCategories().size());
        assertEquals(2, instructions.getEquipments().size());
    }
}