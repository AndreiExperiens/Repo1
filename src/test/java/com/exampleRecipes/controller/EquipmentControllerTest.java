package com.exampleRecipes.controller;

import com.exampleRecipes.commands.InstructionsCommand;
import com.exampleRecipes.service.InstructionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class EquipmentControllerTest {
    @Mock
    InstructionService instructionService;

    EquipmentController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new EquipmentController( instructionService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testListIngredients() throws Exception {
        //given
        InstructionsCommand recipeCommand = new InstructionsCommand();
        when(instructionService.findCommandById(anyLong())).thenReturn(recipeCommand);

        //when
        mockMvc.perform(get("/instructions/1/equipment"))
                .andExpect(status().isOk())
                .andExpect(view().name("instructions/equipment/list"))
                .andExpect(model().attributeExists("instructions"));

        //then
        verify(instructionService, times(1)).findCommandById(anyLong());
    }

}