package org.university.diplom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.university.diplom.constants.FunctionType;
import org.university.diplom.dto.CommonDto;
import org.university.diplom.dto.ResultDto;
import org.university.diplom.processor.Processor;
import org.university.diplom.service.impl.MinioService;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class BeatingGenerationController {

    private final Processor processor;
    private final MinioService minioService;

    @PostMapping("/beating")
    public String process(CommonDto commonDto, Model model) {
        commonDto.setType(FunctionType.BEATING);
        ResultDto resultDto = processor.process(commonDto);
        model.addAttribute("resultDto", resultDto);
        return "beating";
    }

    @ResponseBody
    @GetMapping("/beating/image/{imageName}")
    public byte[] downloadImage(@PathVariable UUID imageName) {return minioService.findImage(imageName.toString());
    }
}
