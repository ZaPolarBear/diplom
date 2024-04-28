package org.university.diplom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.university.diplom.dto.CommonDto;
import org.university.diplom.dto.ResultDto;
import org.university.diplom.processor.Processor;
import org.university.diplom.service.impl.MinioService;

import java.util.UUID;

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
    public String process(CommonDto commonDto, Model model) {
        ResultDto resultDto = processor.process(commonDto);
        model.addAttribute("resultDto", resultDto);
        return "graph";
    }

    @ResponseBody
    @GetMapping("/graph/image/{imageName}")
    public byte[] downloadImage(@PathVariable UUID imageName) {
        return minioService.findImage(imageName.toString());
    }
}