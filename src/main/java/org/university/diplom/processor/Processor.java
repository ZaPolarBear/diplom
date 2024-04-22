package org.university.diplom.processor;

import lombok.RequiredArgsConstructor;
import org.jfree.data.xy.XYSeriesCollection;
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

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class Processor {

    private final CalculationStrategyHandler calculationStrategyHandler;

    private final FunctionEntityRepository functionEntityRepository;

    private final ImageService imageService;

    private final MinioService minioService;

    public ResultDto process(CommonDto commonDto) {
        FunctionType functionType = commonDto.getType();
        CalculationService service = calculationStrategyHandler.handle(functionType);
        String function = service.toFunction(commonDto);
        Optional<FunctionEntity> entity = functionEntityRepository.findByFunctionAndType(function, functionType);
        if (entity.isPresent()) {
            return new ResultDto(
                    entity.get().getImageName().toString(),
                    function,
                    minioService.findImage(entity.get().getImageName().toString()),
                    functionType
            );
        } else {
            XYSeriesCollection dataset = service.calculate(commonDto);
            byte[] imageBytes = imageService.generateImage(dataset);
            String imageName = minioService.upload(imageBytes);
            FunctionEntity functionEntity = FunctionEntity.builder()
                    .function(function)
                    .type(functionType)
                    .imageName(UUID.fromString(imageName))
                    .build();
            functionEntityRepository.save(functionEntity);
            return new ResultDto(
                    imageName,
                    function,
                    imageBytes,
                    functionType
            );
        }
    }
}
