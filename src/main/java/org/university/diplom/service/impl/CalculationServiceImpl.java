package org.university.diplom.service.impl;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.stereotype.Service;
import org.university.diplom.constants.FunctionType;
import org.university.diplom.service.CalculationService;

import static java.lang.Math.sin;

//y = amplitude * sin((2* pi / waveLength) * x) - wave function
//k = w/v - wave value
//w = 2 * pi * f - circle velocity
//A - amplitude
//v - wave speed(const) in liquid or air
//f - line velocity


@Service
public class CalculationServiceImpl implements CalculationService {
    @Override
    public XYSeriesCollection calculate(FunctionType functionType, double amplitude, double waveLength, double step) {
        double y;
        final XYSeries waveLine = new XYSeries("MechanicalWave");
        for (double x = 0; x < 100; x += step){
            y = amplitude * sin(((2 * Math.PI) / waveLength) * x);
            waveLine.add(x, y);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(waveLine);
        return dataset;
    }
}
