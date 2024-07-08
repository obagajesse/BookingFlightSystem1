package com.obagajesse.BookingFlightSystem1.Service.AWS.Impl;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Builder;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.obagajesse.BookingFlightSystem1.Service.AWS.AWSs3Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Date;

@Service
//@AllArgsConstructor
@Slf4j
public class AWSs3ServiceImpl implements AWSs3Service {



private final String accessKeyId;
    private final String secretAccessKey;
    private final String s3Url;

    @Autowired
    public AWSs3ServiceImpl(
            @Value("${aws.access.keyId}") String accessKeyId,
            @Value("${aws.secret.access.key}") String secretAccessKey,
            @Value("${aws.s3.url}") String s3Url) {
        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
        this.s3Url = s3Url;
    }

    @Override
    public AmazonS3 getClient() {

        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId,secretAccessKey);
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withPathStyleAccessEnabled(true)
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(s3Url,"eu2"))
                .build();
    }

    @Override
    public String getPreSignedUrl(String bucketName, String key) {
        AmazonS3 amazonS3 = getClient();
        Date expiration = new Date();
        long expirationTimeMillis = Instant.now().toEpochMilli();
        expirationTimeMillis += 1000 * 60 * 60;
        expiration.setTime(expirationTimeMillis);
        return amazonS3.generatePresignedUrl(bucketName,key,expiration, HttpMethod.GET).toString();
    }

    @Override
    public byte[] getObject(String bucketName, String key) throws IOException {
        AmazonS3 amazonS3 = getClient();
        S3Object object = amazonS3.getObject(bucketName, key);
        S3ObjectInputStream s3ObjectInputStream = object.getObjectContent();
        return IOUtils.toByteArray(s3ObjectInputStream);
    }

    @Override
    public boolean deleteObject(String bucketName, String key) {
        AmazonS3 amazonS3 = getClient();
        amazonS3.deleteObject(new DeleteObjectRequest(bucketName, key));
        return true;
    }

    @Override
    public PutObjectResult uploadObject(String bucketName, String key, InputStream inputStream, ObjectMetadata objectMetadata) {
        AmazonS3 amazons3 = getClient();
        return amazons3.putObject(new PutObjectRequest(bucketName, key, inputStream, objectMetadata));
    }
}
