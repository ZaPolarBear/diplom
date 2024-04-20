package org.university.diplom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.university.diplom.dto.CommonDto;
import org.university.diplom.processor.Processor;

@Controller
@RequiredArgsConstructor
public class FunctionGenerationController {

    private final Processor processor;

    @GetMapping("/graph")
    public String displayGraphPage(Model model) {
        CommonDto commonDto = new CommonDto();
        byte[] imageBytes = new byte[0];
        model.addAttribute("commonDto", commonDto);
        model.addAttribute("imageBytes", imageBytes);
        return "graph";
    }

    @PostMapping("/graph")
    public String process(CommonDto commonDto, RedirectAttributes redirectAttributes) {
        String imageName = processor.process(commonDto);
        redirectAttributes.addFlashAttribute("imageName", imageName);
        return "redirect:graph/result";
    }
}