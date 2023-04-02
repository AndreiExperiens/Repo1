package com.exampleRecipes.converters;

import com.exampleRecipes.commands.NotesCommand;
import com.exampleRecipes.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {
    public static final Long ID_VALUE = new Long(1L);
    public static final String INSTRUCT_NOTES = "Notes";
    NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();

    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID_VALUE);
        notesCommand.setInstructNotes(INSTRUCT_NOTES);

        //when
        Notes notes = converter.convert(notesCommand);

        //then
        assertNotNull(notes);
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(INSTRUCT_NOTES, notes.getInstructNotes());
    }
}