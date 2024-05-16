package org.university.diplom.service.impl;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.stereotype.Service;
import org.university.diplom.exception.ImageUploadException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;

    public String upload(byte[] file, String bucketName){
        InputStream imageStream = new ByteArrayInputStream(file);
        String imageName = UUID.randomUUID().toString();
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(imageName)
                    .stream(imageStream, imageStream.available(), -1)
                    .build());
            return imageName;
        } catch (MinioException | IllegalArgumentException | IOException | InvalidKeyException |
                 NoSuchAlgorithmException e) {
            throw new ImageUploadException(e.getMessage());
        }
    }

    public byte[] find(String fileName, String bucketName) {
        try (InputStream object =
                     minioClient.getObject(
                             GetObjectArgs.builder().bucket(bucketName).object(fileName).build())) {
            return IOUtils.toByteArray(object);

        } catch (MinioException | IllegalArgumentException | IOException | InvalidKeyException |
                 NoSuchAlgorithmException e) {
            throw new ImageUploadException(e.getMessage());
        }
    }
}
