package org.university.diplom.service.impl;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.minio.bucket.image}")
    private String bucketImageName;

    @Value("${spring.minio.bucket.file}")
    private String bucketFileName;

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

    public byte[] findImage(String imageName) {
        try (InputStream object =
                     minioClient.getObject(
                             GetObjectArgs.builder().bucket(bucketImageName).object(imageName).build())) {
            return IOUtils.toByteArray(object);

        } catch (MinioException | IllegalArgumentException | IOException | InvalidKeyException |
                 NoSuchAlgorithmException e) {
            throw new ImageUploadException(e.getMessage());
        }
    }
}
