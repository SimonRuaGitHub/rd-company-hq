package com.rapid.stock.config;

import com.amazonaws.auth.*;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Configuration
@Profile({"local","local-docker"})
public class AWSConfigLocal {

    @Value("${cloud.aws.localstack.mock-credentials.access-key}")
    private String accessKey;
    @Value("${cloud.aws.localstack.mock-credentials.secret-key}")
    private String secretKey;
    @Value("${cloud.aws.region.static}")
    private String region;
    @Value("${cloud.aws.s3.url}")
    private String s3Url;

    @Bean
    public AmazonS3 localS3Client() {

        return AmazonS3ClientBuilder.standard()
                .withCredentials(getCredentialsProvider())
                .withEndpointConfiguration(getLocalEndpointConfiguration())
                .withPathStyleAccessEnabled(true)
                .build();
    }

    private AWSCredentialsProvider getCredentialsProvider() {

        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        return new AWSStaticCredentialsProvider(credentials);
    }

    private AwsClientBuilder.EndpointConfiguration getLocalEndpointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(s3Url, region);
    }
}
