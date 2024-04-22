package org.university.diplom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.university.diplom.dto.CommonDto;
import org.university.diplom.dto.ResultDto;
import org.university.diplom.processor.Processor;
import org.university.diplom.service.impl.MinioService;

@Controller
@RequiredArgsConstructor
public class FunctionGenerationController {

    private final Processor processor;
    private final MinioService minioService;

    @GetMapping("/graph")
    public String displayGraphPage(Model model) {
        CommonDto commonDto = new CommonDto();
        model.addAttribute("commonDto", commonDto);
        return "graph";
    }

    @PostMapping("/graph")
    public String process(CommonDto commonDto, RedirectAttributes model) {
        ResultDto resultDto = processor.process(commonDto);
        model.addFlashAttribute("resultDto", resultDto);
        return "redirect:graph/result";
    }
}