package org.university.diplom.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.university.diplom.exception.DataConvertingException;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.jfree.chart.ChartUtils.writeBufferedImageAsPNG;

@Setter
@Getter
@Service
@RequiredArgsConstructor
public class ImageService {

    public static final int WIDTH = 650;
    public static final int HEIGHT = 650;

    @Value("${spring.minio.bucket.image}")
    private String bucketName;

    public byte[] generateImage(XYSeriesCollection dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "",
                "X Axis",
                "Y Axis",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        BufferedImage bufferedImage = chart.createBufferedImage(WIDTH, HEIGHT);
        return convertToByteArray(bufferedImage);
    }

    private static byte[] convertToByteArray(BufferedImage image) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            writeBufferedImageAsPNG(byteArrayOutputStream, image);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new DataConvertingException("Unexcepted exception while converting to byte array: %s".formatted(e.getMessage()));
        }
    }

}
