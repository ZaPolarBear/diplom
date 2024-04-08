package org.university.diplom.processor;

import lombok.RequiredArgsConstructor;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.stereotype.Service;
import org.university.diplom.constants.FunctionType;
import org.university.diplom.dto.CommonDto;
import org.university.diplom.model.FunctionEntity;
import org.university.diplom.repository.FunctionEntityRepository;
import org.university.diplom.service.CalculationService;
import org.university.diplom.service.impl.CalculationStrategyHandler;
import org.university.diplom.service.impl.ImageService;
import org.university.diplom.service.impl.MinioService;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class Processor {

    private CalculationStrategyHandler calculationStrategyHandler;

    private FunctionEntityRepository functionEntityRepository;

    private ImageService imageService;

    private MinioService minioService;

    public byte[] process(CommonDto commonDto) {
        FunctionType functionType = commonDto.getType();
        CalculationService service = calculationStrategyHandler.handle(functionType);
        String function = service.toFunction(commonDto);
        Optional<FunctionEntity> entity = functionEntityRepository.findByFunctionAndFunctionType(function, functionType);
        if (entity.isPresent()) {
            return minioService.findImage(entity.get().getImageName().toString());
        } else {
            XYSeriesCollection dataset = service.calculate(commonDto);
            byte[] image = imageService.generateImage(dataset);
            String imageName = minioService.upload(image);
            FunctionEntity functionEntity = FunctionEntity.builder()
                    .function(function)
                    .functionType(functionType)
                    .imageName(UUID.fromString(imageName))
                    .build();
            functionEntityRepository.save(functionEntity);
            return image;
        }
    }
}
