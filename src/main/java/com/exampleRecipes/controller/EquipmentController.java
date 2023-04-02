package com.exampleRecipes.controller;

import com.exampleRecipes.service.InstructionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class EquipmentController {

    private final InstructionService instructionService; // interfata


    public EquipmentController(InstructionService instructionService) {
        this.instructionService = instructionService;
    }

    @GetMapping
    @RequestMapping("/instructions/{recipeId}/equipments")
    public String listIngredients(@PathVariable String instructionsId, Model model){
        log.debug("Getting ingredient list for recipe id: " + instructionsId);

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("instructions", instructionService.findCommandById(Long.valueOf(instructionsId)));

        return "instructions/equipment/list";
    }

}
