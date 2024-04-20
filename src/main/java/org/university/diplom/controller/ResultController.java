package org.university.diplom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.university.diplom.service.impl.MinioService;

@Controller
@RequiredArgsConstructor
public class ResultController {

    private final MinioService minioService;

    @GetMapping("graph/result")
    public String openPage(@ModelAttribute("imageName") String imageName, Model model){
        byte[] imageBytes = minioService.findImage(imageName);
        model.addAttribute("imageSrc", imageBytes);
        return "result";
    }

}
