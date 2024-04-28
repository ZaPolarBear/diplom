package org.university.diplom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainNavController {
    @GetMapping("main")
    public String openPage(Model model){
        String nextPage = "";
        model.addAttribute("nextPage", nextPage);
        return "main";
    }

    @PostMapping("main")
    public String redirectToMechanicalPage(){
        return "redirect:mechanical";
    }

}
