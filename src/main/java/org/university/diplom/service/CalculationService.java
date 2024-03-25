package org.university.diplom.service;

import org.university.diplom.constants.FunctionType;
import org.university.diplom.dto.CommonDto;

//y = amplitude * sin((2* pi / waveLength) * x)

public interface CalculationService {
    void calculate (CommonDto commonDto);

    FunctionType getType();
}
