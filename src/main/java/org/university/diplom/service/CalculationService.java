package org.university.diplom.service;

import org.jfree.data.xy.XYSeriesCollection;
import org.university.diplom.constants.FunctionType;
import org.university.diplom.dto.CommonWaveDto;

//y = amplitude * sin((2* pi / waveLength) * x)

public interface CalculationService {
    XYSeriesCollection calculate(CommonWaveDto commonWaveDto);
    FunctionType getType();

    String toFunction(CommonWaveDto commonWaveDto);
}
