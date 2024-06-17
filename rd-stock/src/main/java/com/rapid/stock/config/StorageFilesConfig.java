package com.rapid.stock.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Optional;

@Configuration
public class StorageFilesConfig {

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;
    @Value("${cloud.aws.region.static}")
    private String region;
    @Value("${cloud.aws.url}")
    private String url;

    @Bean
    public AmazonS3 localS3Client() {

        return AmazonS3ClientBuilder.standard()
                .withCredentials(getCredentialsProvider())
                .withEndpointConfiguration(getEndpointConfiguration())
                .withRegion(region)
                .build();
    }

    private AWSStaticCredentialsProvider getCredentialsProvider() {
        AWSCredentials credentials = new BasicAWSCredentials(
                accessKey,
                secretKey
        );

        return new AWSStaticCredentialsProvider(credentials);
    }

    private AwsClientBuilder.EndpointConfiguration getEndpointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(url, region);
    }
}
