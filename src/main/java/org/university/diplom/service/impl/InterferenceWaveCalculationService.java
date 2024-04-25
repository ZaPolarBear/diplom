package org.university.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.stereotype.Service;
import org.university.diplom.constants.FunctionType;
import org.university.diplom.dto.CommonDto;
import org.university.diplom.service.CalculationService;

@Service
@RequiredArgsConstructor
public class InterferenceWaveCalculationService implements CalculationService {
    @Override
    public XYSeriesCollection calculate(CommonDto commonDto) {
        XYSeries series = new XYSeries("Interference");
        double k = 2 * Math.PI / commonDto.getWaveLength();
        for (int i = 0; i < 1000; i++) {
            double x = i / 100.0;
            double y = commonDto.getAmplitude() * Math.cos(k * x + commonDto.getPhaseDifference());
            series.add(x, y);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    @Override
    public FunctionType getType() {
        return FunctionType.INTERFERENCE;
    }

    @Override
    public String toFunction(CommonDto commonDto) {
        return commonDto.getAmplitude() + " *  cos(" + 2 * Math.PI / commonDto.getWaveLength() + " + " + commonDto.getPhaseDifference() + ")";
    }
}
