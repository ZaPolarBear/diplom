package org.university.diplom.service.impl;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class XmlFileService {

    private final MinioClient minioClient;

    @Value("${spring.minio.bucket.file}")
    private String bucketName;

    public byte[] generateXMLFile(XYSeriesCollection dataset) {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            XMLStreamWriter writer = outputFactory.createXMLStreamWriter(outputStream);

            writer.writeStartDocument();
            writer.writeStartElement("data");

            for (int i = 0; i < dataset.getSeriesCount(); i++) {
                XYSeries series = dataset.getSeries(i);
                writer.writeStartElement("series");
                writer.writeAttribute("name", series.getKey().toString());

                for (int j = 0; j < series.getItemCount(); j++) {
                    double x = series.getX(j).doubleValue();
                    double y = series.getY(j).doubleValue();
                    writer.writeStartElement("point");
                    writer.writeAttribute("x", Double.toString(x));
                    writer.writeAttribute("y", Double.toString(y));
                    writer.writeEndElement();
                }

                writer.writeEndElement();
            }

            writer.writeEndElement();
            writer.writeEndDocument();

            writer.flush();
            writer.close();

            return outputStream.toByteArray();
        } catch (IOException | XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }
}
