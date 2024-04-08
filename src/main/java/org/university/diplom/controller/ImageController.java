package org.university.diplom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.university.diplom.dto.CommonDto;
import org.university.diplom.processor.Processor;

@Controller
@RequiredArgsConstructor
public class ImageController {

    private Processor processor;

    @PostMapping("/graph")
    public void process(@RequestBody CommonDto commonDto){
        processor.process(commonDto);
    }
}
