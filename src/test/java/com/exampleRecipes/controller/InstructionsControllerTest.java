package com.exampleRecipes.controller;

import com.exampleRecipes.commands.InstructionsCommand;
import com.exampleRecipes.domain.Instructions;
import com.exampleRecipes.service.InstructionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class InstructionsControllerTest {

    @Mock
    InstructionService instructionService;

    InstructionsController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new InstructionsController(instructionService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void showById() throws Exception {
        Instructions instructions = new Instructions();
        instructions.setId(1L);


        when(instructionService.findById(anyLong())).thenReturn(instructions);

        mockMvc.perform(get("/instructions/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructions/show"))
                .andExpect(model().attributeExists("instructions"));
    }
    @Test
    public void testGetNewRecipeForm() throws Exception {
        InstructionsCommand command = new InstructionsCommand();

        mockMvc.perform(get("/instructions/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructions/instructionsform"))
                .andExpect(model().attributeExists("instructions"));
    }

    @Test
    public void testPostNewRecipeForm() throws Exception {
        InstructionsCommand command = new InstructionsCommand();
        command.setId(2L);

        when(instructionService.saveInstructionsCommand(any())).thenReturn(command);

        mockMvc.perform(post("/instructions")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("id", "")
//                .param("description", "some string")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/instructions/show/2"));
    }

    @Test
    public void testGetUpdateView() throws Exception {
        InstructionsCommand command = new InstructionsCommand();
        command.setId(2L);

        when(instructionService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(get("/instructions/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructions/instructionsform"))
                .andExpect(model().attributeExists("instructions"));
    }
    @Test
    public void testDeleteAction() throws Exception {
        mockMvc.perform(get("/instructions/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(instructionService, times(1)).deleteById(anyLong());
    }
}