package org.university.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.university.diplom.constants.FunctionType;
import org.university.diplom.service.CalculationService;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CalculationStrategyHandler {

    private final Map<FunctionType, CalculationService> store;

    public CalculationService handle(FunctionType functionType){
       return store.get(functionType);
    }
}