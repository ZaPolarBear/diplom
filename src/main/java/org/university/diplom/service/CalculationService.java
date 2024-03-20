package org.university.diplom.service;

import org.jfree.data.xy.XYSeriesCollection;
import org.university.diplom.constants.FunctionType;

//y = amplitude * sin((2* pi / waveLength) * x)

public interface CalculationService {
    XYSeriesCollection calculate (FunctionType functionType, double amplitude, double waveLength, double step);
}
