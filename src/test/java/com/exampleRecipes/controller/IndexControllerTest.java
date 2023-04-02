package com.exampleRecipes.controller;


import com.exampleRecipes.domain.Instructions;
import com.exampleRecipes.service.InstructionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class IndexControllerTest {

    @Mock
    InstructionService instructionService;

    @Mock
    Model model;

    IndexController controller;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller =  new IndexController(instructionService);
    }

    @Test
    public void testMocMVC() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {

        //given
        Set<Instructions> instructionst = new HashSet<>();
        instructionst.add(new Instructions());

        Instructions instructions = new Instructions();
        instructions.setId(1L);


        instructionst.add(instructions);

        when(instructionService.getInstructions()).thenReturn(instructionst);

        ArgumentCaptor<Set<Instructions>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = controller.getIndexPage(model);

        //then
        assertEquals("index", viewName);
        verify(instructionService, times(1)).getInstructions();
        verify(model, times(1)).addAttribute(eq("instructionst"), argumentCaptor.capture());
        Set<Instructions> setInConstroller = argumentCaptor.getValue();
        assertEquals(2,setInConstroller.size());

    }
}