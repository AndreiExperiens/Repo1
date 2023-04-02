package com.exampleRecipes.converters;

import com.exampleRecipes.commands.NotesCommand;
import com.exampleRecipes.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {
    public static final Long ID_VALUE = new Long(1L);
    public static final String INSTRUCTIONS_NOTES = "Notes";
    NotesToNotesCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void convert() throws Exception {
        //given
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setInstructNotes(INSTRUCTIONS_NOTES);

        //when
        NotesCommand notesCommand = converter.convert(notes);

        //then
        assertEquals(ID_VALUE, notesCommand.getId());
        assertEquals(INSTRUCTIONS_NOTES, notesCommand.getInstructNotes());
    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Notes()));
    }
}