package org.university.diplom.controller;

import lombok.RequiredArgsConstructor;
import org.jfree.data.xy.XYDataset;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.university.diplom.constants.FunctionType;
import org.university.diplom.model.Image;
import org.university.diplom.service.FunctionService;
import org.university.diplom.service.ImageService;

@RestController
@RequiredArgsConstructor
public class FunctionController {

    private final FunctionService functionService;
    private final ImageService imageService;


    @GetMapping("/graph")
    public XYDataset calculateY(@RequestParam String function, @RequestParam FunctionType functionType) {
        XYDataset dataset = functionService.createDataset();
        if (!imageService.checkImageExists(function, functionType)) {
            String imageUrl = generateImageUrl();//TODO: image generation
            imageService.saveImage(imageUrl, functionType);

        }
        return dataset;
    }

    @GetMapping("/graph/image")
    public Image getImageByFunction(@RequestParam String function, @RequestParam FunctionType functionType) {
        return imageService.getImageByFunction(function, functionType);
    }
}
