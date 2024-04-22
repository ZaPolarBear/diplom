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

    private final CalculationStrategyHandler calculationStrategyHandler;

    private final FunctionEntityRepository functionEntityRepository;

    private final ImageService imageService;

    private final MinioService minioService;

    public String process(CommonDto commonDto) {
        FunctionType functionType = commonDto.getType();
        CalculationService service = calculationStrategyHandler.handle(functionType);
        String function = service.toFunction(commonDto);
        Optional<FunctionEntity> entity = functionEntityRepository.findByFunctionAndType(function, functionType);
        if (entity.isPresent()) {
            return entity.get().getImageName().toString();
        } else {
            XYSeriesCollection dataset = service.calculate(commonDto);
            byte[] image = imageService.generateImage(dataset);
            String imageName = minioService.upload(image);
            FunctionEntity functionEntity = FunctionEntity.builder()
                    .function(function)
                    .type(functionType)
                    .imageName(UUID.fromString(imageName))
                    .build();
            functionEntityRepository.save(functionEntity);
            return imageName;
        }
    }
}
