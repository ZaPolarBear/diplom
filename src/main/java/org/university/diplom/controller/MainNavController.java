package org.university.diplom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.university.diplom.dto.CommonDto;

@Controller
public class MainNavController {
    @GetMapping("main")
    public String openPage(){
        return "main";
    }

    @GetMapping("/mechanical")
    public String displayMechanicalPage(Model model) {
        CommonDto commonDto = new CommonDto();
        model.addAttribute("commonDto", commonDto);
        return "mechanical";
    }

    @GetMapping("/addition")
    public String displayAdditionPage(Model model) {
        CommonDto commonDto = new CommonDto();
        model.addAttribute("commonDto", commonDto);
        return "addition";
    }

    @GetMapping("/beating")
    public String displayBeatingPage(Model model) {
        CommonDto commonDto = new CommonDto();
        model.addAttribute("commonDto", commonDto);
        return "beating";
    }

    @GetMapping("/interference")
    public String displayInterferencePage(Model model) {
        CommonDto commonDto = new CommonDto();
        model.addAttribute("commonDto", commonDto);
        return "interference";
    }
}
