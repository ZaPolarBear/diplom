package org.university.diplom.processor;

import lombok.RequiredArgsConstructor;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.university.diplom.constants.FunctionType;
import org.university.diplom.dto.CommonDto;
import org.university.diplom.dto.ResultDto;
import org.university.diplom.model.FunctionEntity;
import org.university.diplom.repository.FunctionEntityRepository;
import org.university.diplom.service.CalculationService;
import org.university.diplom.service.impl.CalculationStrategyHandler;
import org.university.diplom.service.impl.ImageService;
import org.university.diplom.service.impl.MinioService;
import org.university.diplom.service.impl.XmlFileService;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class Processor {

    @Value("${spring.minio.bucket.image}")
    private String bucketImageName;

    @Value("${spring.minio.bucket.file}")
    private String bucketFileName;

    private final CalculationStrategyHandler calculationStrategyHandler;

    private final FunctionEntityRepository functionEntityRepository;

    private final ImageService imageService;

    private final MinioService minioService;

    private final XmlFileService xmlFileService;

    public ResultDto process(CommonDto commonDto) {
        FunctionType functionType = commonDto.getType();
        CalculationService service = calculationStrategyHandler.handle(functionType);
        String function = service.toFunction(commonDto);
        Optional<FunctionEntity> entity = functionEntityRepository.findByFunctionAndType(function, functionType);
        if (entity.isPresent()) {
            return new ResultDto(
                    entity.get().getImageName().toString(),
                    function,
                    entity.get().getFileName().toString()
            );
        } else {
            XYSeriesCollection dataset = service.calculate(commonDto);
            byte[] imageBytes = imageService.generateImage(dataset);
            byte[] dataBytes = xmlFileService.generateXMLFile(dataset);
            String imageName = minioService.upload(imageBytes, bucketImageName);
            String dataName = minioService.upload(dataBytes, bucketFileName);
            FunctionEntity functionEntity = FunctionEntity.builder()
                    .function(function)
                    .type(functionType)
                    .imageName(UUID.fromString(imageName))
                    .fileName(UUID.fromString(dataName))
                    .build();
            functionEntityRepository.save(functionEntity);
            return new ResultDto(
                    imageName,
                    function,
                    dataName
            );
        }
    }
}
