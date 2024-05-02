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
public class BeatingWaveCalculationService implements CalculationService {
    @Override
    public XYSeriesCollection calculate(CommonDto commonDto) {
        XYSeries wave1 = new XYSeries("Wave 1");
        for (double x = 0.0; x <= 10.0; x += 0.01) {
            double y1 = commonDto.getAmplitude() * Math.sin(2 * Math.PI * commonDto.getFrequency() * x);
            wave1.add(x, y1);
        }

        XYSeries wave2 = new XYSeries("Wave 2");
        for (double x = 0.0; x <= 10.0; x += 0.01) {
            double y2 = commonDto.getAmplitudeSecond() * Math.sin(2 * Math.PI * commonDto.getFrequencySecond() * x);
            wave2.add(x, y2);
        }

        XYSeries beatingWave = new XYSeries("Beating Wave");
        for (int i = 0; i < wave1.getItemCount(); i++) {
            double x = wave1.getX(i).doubleValue();
            double y1 = wave1.getY(i).doubleValue();
            double y2 = wave2.getY(i).doubleValue();
            double beating = y1 + y2;
            beatingWave.add(x, beating);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(beatingWave);

        return dataset;
    }

    @Override
    public FunctionType getType() {
        return FunctionType.BEATING;
    }

    @Override
    public String toFunction(CommonDto commonDto) {
        return commonDto.getAmplitude() + " * sin(2 * " + Math.PI + " * " + commonDto.getFrequency()  + ")";
    }
}
