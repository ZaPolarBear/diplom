package org.university.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.stereotype.Service;
import org.university.diplom.constants.FunctionType;
import org.university.diplom.dto.CommonWaveDto;
import org.university.diplom.service.CalculationService;

@Service
@RequiredArgsConstructor
public class InterferenceWaveCalculationService implements CalculationService {
    @Override
    public XYSeriesCollection calculate(CommonWaveDto commonWaveDto) {
        XYSeries series = new XYSeries("Interference");
        double k = 2 * Math.PI / commonWaveDto.getWaveLength();
        for (int i = 0; i < 1000; i++) {
            double x = i / 100.0;
            double y = commonWaveDto.getAmplitude() * Math.cos(k * x + commonWaveDto.getPhaseDifference());
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
    public String toFunction(CommonWaveDto commonWaveDto) {
        return commonWaveDto.getAmplitude() + " *  cos(" + 2 * Math.PI / commonWaveDto.getWaveLength() + " + " + commonWaveDto.getPhaseDifference() + ")";
    }
}
