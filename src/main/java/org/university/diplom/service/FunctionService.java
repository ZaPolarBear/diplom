package org.university.diplom.service;


import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.stereotype.Service;

@Service
public class FunctionService {

    private double n = 4;
    private double b1 = 0.1 + 0.01 * n;
    private double b2 = 0.005 + 0.0005 * n;
    private double g = 9.8;
    private double h = 0.1;

    public XYDataset createDataset() {
        double t = 0;
        double x;
        double y;
        double a = 30 + n;
        double v0 = 20 + n;

        final XYSeries line1 = new XYSeries("Wave 1");
        do {
            x = v0 * Math.cos(Math.toRadians(a)) * t;
            y = v0 * Math.sin(Math.toRadians(a)) * t - (g * t * t) / 2;
            line1.add(x, y);
            t += 0.01;
        } while (y >= 0);
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(line1);
        return dataset;
    }
}


