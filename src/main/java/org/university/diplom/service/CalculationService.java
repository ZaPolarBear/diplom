package org.university.diplom.service;

import org.jfree.data.xy.XYSeriesCollection;
import org.university.diplom.constants.FunctionType;
import org.university.diplom.dto.CommonDto;

//y = amplitude * sin((2* pi / waveLength) * x)

public interface CalculationService {
    XYSeriesCollection calculate (CommonDto commonDto);
    FunctionType getType();
    String toFunction(CommonDto commonDto);
}
