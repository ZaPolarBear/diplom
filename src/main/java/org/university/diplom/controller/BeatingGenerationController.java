package org.university.diplom.controller;

import io.minio.errors.MinioException;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import java.io.IOException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class BeatingGenerationController {

    private final Processor processor;
    private final MinioService minioService;

    @Value("${spring.minio.bucket.image}")
    private String bucketImageName;

    @Value("${spring.minio.bucket.file}")
    private String bucketXmlName;

    @PostMapping("/beating")
    public String process(CommonDto commonDto, Model model) {
        commonDto.setType(FunctionType.BEATING);
        ResultDto resultDto = processor.process(commonDto);
        model.addAttribute("resultDto", resultDto);
        return "beating";
    }

    @ResponseBody
    @GetMapping("/beating/image/{imageName}")
    public byte[] downloadImage(@PathVariable UUID imageName) {return minioService.find(imageName.toString(), bucketImageName);
    }

    @ResponseBody
    @GetMapping("/beating/table/{tableName}")
    public ResponseEntity<Resource> downloadTable(@PathVariable UUID tableName) throws IOException, MinioException {
        String fileName = tableName.toString() + ".xml";

        byte[] xmlBytes = minioService.find(fileName, bucketXmlName);

        ByteArrayResource resource = new ByteArrayResource(xmlBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_XML)
                .body((Resource) resource);
    }
}
