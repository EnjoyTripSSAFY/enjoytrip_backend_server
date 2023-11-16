//package com.ssafy.enjoytrip_springboot.common.util;
//
//import com.amazonaws.AmazonClientException;
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.amazonaws.services.s3.model.CannedAccessControlList;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.File;
//import java.util.function.BiConsumer;
//import java.util.function.BiFunction;
//import java.util.function.Consumer;
//import java.util.function.Function;
//
//@Configuration
//public class S3Config {
//
//    @Value("${cloud.aws.region.static}")
//    private String REGION;
//
//    @Value("${cloud.aws.credentials.access-key}")
//    private String ACCESS_KEY;
//
//    @Value("${cloud.aws.credentials.secret-key}")
//    private String SECRET_KEY;
//
//    @Bean
//    public AmazonS3 amazonS3() {
//        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
//
//        return AmazonS3ClientBuilder
//                .standard()
//                .withCredentials(new AWSStaticCredentialsProvider(credentials))
//                .withRegion(REGION)
//                .build();
//    }
//}
