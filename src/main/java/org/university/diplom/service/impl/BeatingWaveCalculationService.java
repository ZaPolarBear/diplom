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
public class BeatingWaveCalculationService implements CalculationService {

    public static final double GRID_SIZE = 10.0;
    public static final double STEP_SIZE = 0.01;

    @Override
    public XYSeriesCollection calculate(CommonWaveDto commonWaveDto) {
        XYSeries wave1 = new XYSeries("Wave 1");
        for (double x = 0.0; x <= GRID_SIZE; x += STEP_SIZE) {
            double y1 = commonWaveDto.getAmplitude() * Math.sin(2 * Math.PI * commonWaveDto.getFrequency() * x);
            wave1.add(x, y1);
        }

        XYSeries wave2 = new XYSeries("Wave 2");
        for (double x = 0.0; x <= GRID_SIZE; x += STEP_SIZE) {
            double y2 = commonWaveDto.getAmplitudeSecond() * Math.sin(2 * Math.PI * commonWaveDto.getFrequencySecond() * x);
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
    public String toFunction(CommonWaveDto commonWaveDto) {
        return commonWaveDto.getAmplitude() + " * sin(2 * " + Math.PI + " * " + commonWaveDto.getFrequency() + ")";
    }
}
