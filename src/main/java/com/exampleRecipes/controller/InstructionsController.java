package com.exampleRecipes.controller;

import com.exampleRecipes.commands.InstructionsCommand;
import com.exampleRecipes.service.InstructionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class InstructionsController {

    private final InstructionService instructionService;

    public InstructionsController(InstructionService instructionService) {
        this.instructionService = instructionService;
    }
    @GetMapping
    @RequestMapping("/instructions/show/{id}")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("instructions", instructionService.findById(Long.valueOf(id)));
        return "instructions/show";
    }

    @GetMapping
    @RequestMapping("instructions/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("instructions", instructionService.findCommandById(Long.valueOf(id)));
        return  "instructions/instructionsform";
    }
    @GetMapping
    @RequestMapping("instructions/new")
    public String newRecipe(Model model){
        model.addAttribute("instructions", new InstructionsCommand());

        return "instructions/instructionsform";
    }

    @PostMapping("instructions")
    public String saveOrUpdate(@ModelAttribute InstructionsCommand command){
        InstructionsCommand savedCommand = instructionService.saveInstructionsCommand(command);

        return "redirect:/instructions/show/" + savedCommand.getId();
    }

    @GetMapping
    @RequestMapping("instructions/{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);

        instructionService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

}


