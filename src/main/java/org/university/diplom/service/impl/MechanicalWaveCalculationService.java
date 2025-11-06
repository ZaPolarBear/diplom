package org.university.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.stereotype.Service;
import org.university.diplom.constants.FunctionType;
import org.university.diplom.dto.CommonWaveDto;
import org.university.diplom.service.CalculationService;

import static java.lang.Math.sin;

//y = amplitude * sin((2* pi / waveLength) * x) - wave function
//k = w/v - wave value
//w = 2 * pi * f - circle velocity
//A - amplitude
//v - wave speed(const) in liquid or air
//f - line velocity


@Service
@RequiredArgsConstructor
public class MechanicalWaveCalculationService implements CalculationService {

    @Override
    public XYSeriesCollection calculate(CommonWaveDto commonWaveDto) {
        double y;
        final XYSeries waveLine = new XYSeries("MechanicalWave");
        for (double x = 0; x < 100; x += commonWaveDto.getStep()) {
            y = commonWaveDto.getAmplitude() * sin(((2 * Math.PI) / commonWaveDto.getWaveLength()) * x);
            waveLine.add(x, y);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(waveLine);
        return dataset;
    }

    @Override
    public FunctionType getType() {
        return FunctionType.MECHANICAL;
    }

    @Override
    public String toFunction(CommonWaveDto commonWaveDto) {
        return commonWaveDto.getAmplitude() + " " + "* sin(" + (2 * Math.PI) + " / " + commonWaveDto.getWaveLength() + ")" + " step: " + commonWaveDto.getStep();
    }
}
