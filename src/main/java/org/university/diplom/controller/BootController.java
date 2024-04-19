package org.university.diplom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BootController {

    @GetMapping("/")
    public String appStart(){
        return "redirect:graph";
    }
}
