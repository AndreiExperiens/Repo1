package com.exampleRecipes.controller;

import com.exampleRecipes.service.InstructionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    private final InstructionService instructionService;

    public IndexController(InstructionService instructionService) {
        this.instructionService = instructionService;
    }


    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){
        log.debug("Getting Index page");

        model.addAttribute("instructionst",instructionService.getInstructions());

        return "index";
    }

}
