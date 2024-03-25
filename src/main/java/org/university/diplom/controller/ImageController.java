package org.university.diplom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.university.diplom.dto.CommonDto;
import org.university.diplom.service.impl.MechanicalWaveCalculationService;
import org.university.diplom.service.impl.MinioService;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private MinioService minioService;

    private MechanicalWaveCalculationService mechanicalWaveCalculationService;

    @PostMapping("/graph")
    public void process(@RequestBody CommonDto commonDto){
        //TODO: Find image in base(get bucketName)
        if (true) {
            String imageName = new String();
            byte[] image = minioService.findImage(commonDto.getType().toString(), imageName);
        } else {
            mechanicalWaveCalculationService.calculate(commonDto);

        }
    }
}
