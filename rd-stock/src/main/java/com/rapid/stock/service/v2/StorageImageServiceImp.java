package com.rapid.stock.service.v2;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageImageServiceImp implements StorageImageService{
    @Value("${cloud.aws.s3.bucket.name}")
    private String bucketName;
    private final AmazonS3 s3Client;

    public String uploadImage(MultipartFile multipartFile, String fileName) {
       File file = fromMultipartfileToFile(multipartFile);
       return s3Client
               .putObject(new PutObjectRequest(bucketName, fileName, file))
               .getVersionId();
    }

    private File fromMultipartfileToFile(MultipartFile file) {

        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));

        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (FileNotFoundException e) {
            log.error("File not found");
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("Error converting multipart file to file");
            throw new RuntimeException(e);
        }

        return convertedFile;
    }
}
