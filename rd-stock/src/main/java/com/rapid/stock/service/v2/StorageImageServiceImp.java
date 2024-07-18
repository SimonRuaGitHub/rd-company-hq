package com.rapid.stock.service.v2;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.rapid.stock.exception.SdkClientS3Exception;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@CommonsLog
public class StorageImageServiceImp implements StorageImageService {

    private final AmazonS3 s3Client;

    public void deleteImage(String bucketName, String key) {
        try {
            s3Client.deleteObject(new DeleteObjectRequest(bucketName, key));
        } catch (AmazonServiceException e) {
            throw new AmazonS3Exception(e.getMessage());
        }  catch (SdkClientException e) {
            throw new SdkClientS3Exception(e.getMessage());
        }
    }

    public void uploadImage(String bucketName,  String key, MultipartFile multipartFile) {
       File file = fromMultipartfileToFile(multipartFile);

       try {
          s3Client.putObject(new PutObjectRequest(bucketName, key, file));
       } catch (AmazonServiceException e) {
           throw new AmazonS3Exception(e.getMessage());
       } catch (SdkClientException e) {
           throw new SdkClientS3Exception(e.getMessage());
       }
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
