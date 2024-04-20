package org.university.diplom.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.university.diplom.constants.FunctionType;
import org.university.diplom.service.CalculationService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor

public class StrategyConfiguration {

    private final List<CalculationService> calculationServices;

    @Bean
    public Map<FunctionType, CalculationService> store() {
        return  calculationServices
                .stream()
                .collect(Collectors.toMap(CalculationService::getType, Function.identity()));
    }

}
