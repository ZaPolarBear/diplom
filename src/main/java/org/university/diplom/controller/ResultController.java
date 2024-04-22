package org.university.diplom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.university.diplom.dto.ResultDto;
import org.university.diplom.service.impl.MinioService;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ResultController {

    private final MinioService minioService;

    @GetMapping("graph/result")
    public String openPage(@ModelAttribute("resultDto") ResultDto result, Model model){
        model.addAttribute("resultDto", result);
        minioService.findImage(result.getImageName());
        return "result";
    }

    @ResponseBody
    @GetMapping("/graph/result/image/{imageName}")
    public byte[] downloadImage(@PathVariable UUID imageName) {
        return minioService.findImage(imageName.toString());
    }
}
