package org.university.diplom.service.impl;

import lombok.RequiredArgsConstructor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final MinioService minioService;

    @Value("${spring.minio.bucket.image}")
    private String bucketName;

    public void generateImage(XYSeriesCollection dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Mechanical Wave",
                "X Axis",
                "Y Axis",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        BufferedImage bufferedImage = chart.createBufferedImage(500, 500);
        minioService.upload(convertToByteArray(bufferedImage), bucketName);
    }

    private static byte[] convertToByteArray(BufferedImage image) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ChartUtils.writeBufferedImageAsPNG(byteArrayOutputStream, image);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
