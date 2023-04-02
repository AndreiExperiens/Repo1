package com.exampleRecipes.converters;

import com.exampleRecipes.commands.InstructionsCommand;
import com.exampleRecipes.domain.*;

import org.junit.Before;
import org.junit.Test;

import static com.exampleRecipes.converters.InstructionsCommandToInsructionsTest.*;
import static org.junit.Assert.*;

public class InstructionsToInstructionsCommandTest {
    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.MODERATE;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;
    InstructionsToInstructionsCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new InstructionsToInstructionsCommand(
                new CategoryToCategoryCommand(),
                new EquipmentToEquipmentCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Instructions()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Instructions instructions = new Instructions();
        instructions.setId(RECIPE_ID);
        instructions.setInstTime(INST_TIME);
        instructions.setPregTime(PREG_TIME);
        instructions.setDescription(DESCRIPTION);
        instructions.setDifficulty(DIFFICULTY);
        instructions.setDirections(DIRECTIONS);
        instructions.setServings(SERVINGS);
        instructions.setSource(SOURCE);
        instructions.setUrl(URL);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        instructions.setNotes(notes);

        Category category = new Category();
        category.setId(CAT_ID_1);

        Category category2 = new Category();
        category2.setId(CAT_ID2);

        instructions.getCategories().add(category);
        instructions.getCategories().add(category2);

        Equipment equipment = new Equipment();
        equipment.setId(INGRED_ID_1);

        Equipment equipment2 = new Equipment();
        equipment2.setId(INGRED_ID_2);

        instructions.getEquipments().add(equipment);
        instructions.getEquipments().add(equipment2);

        //when
        InstructionsCommand command = converter.convert(instructions);

        //then
        assertNotNull(command);
        assertEquals(RECIPE_ID, command.getId());
        assertEquals(INST_TIME, command.getInstTime());
        assertEquals(PREP_TIME, command.getPregTime());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(NOTES_ID, command.getNotes().getId());
        assertEquals(2, command.getCategories().size());
        assertEquals(2, command.getEquipaments().size());

    }
}