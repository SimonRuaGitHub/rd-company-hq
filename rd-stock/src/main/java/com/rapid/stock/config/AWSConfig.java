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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Configuration
public class AWSConfig {

    @Value("${cloud.aws.localstack.mock-credentials.access-key}")
    private String accessKey;
    @Value("${cloud.aws.localstack.mock-credentials.secret-key}")
    private String secretKey;
    @Value("${cloud.aws.region.static}")
    private String region;
    @Value("${cloud.aws.s3.url}")
    private String s3Url;

    @Autowired
    private Environment environment;

    @Bean
    @Profile({"local","local-docker"})
    public AmazonS3 localS3Client() {

        return AmazonS3ClientBuilder.standard()
                .withCredentials(getCredentialsProvider())
                .withEndpointConfiguration(getLocalEndpointConfiguration())
                .withPathStyleAccessEnabled(true)
                .build();
    }

    @Bean
    @Profile("development")
    public AmazonS3 devS3Client() {

        return AmazonS3ClientBuilder.standard()
                .withCredentials(getCredentialsProvider())
                .withRegion(region)
                .build();
    }

    private AWSCredentialsProvider getCredentialsProvider() {

        if (getActiveProfile().equals("local")) {
            AWSCredentials credentials = new BasicAWSCredentials(
                    accessKey,
                    secretKey
            );

            return new AWSStaticCredentialsProvider(credentials);
        }

        return DefaultAWSCredentialsProviderChain.getInstance();
    }

    private String getActiveProfile() {
        return Stream.of(environment.getActiveProfiles())
                .filter(activeProfile -> !activeProfile.isEmpty())
                .findFirst()
                .orElseThrow(() -> new NoActiveProfileException("No Active Profiles"));
    }

    private AwsClientBuilder.EndpointConfiguration getLocalEndpointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(s3Url, region);
    }
}
