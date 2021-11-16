package com.nh.aws;


import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class AwsConfig {

    /*
           .withCrednetials에서 new ClasspathPropertiesFileCredentialsProvider() 하면
            AwsCredentials.properties 파일에 있는 accessKey , secretKey 내용을 알아서 읽어감
     */




    //S3 버켓과 연결할 커넥션 얻어오기(스프링 프로젝트 내에 AwsCredetials.properties 파일을 만들어서 써야함 .. git에 안올리면 좋겠지만 좀 그르넹)
    @Bean
    public AmazonS3 createConn() {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new ClasspathPropertiesFileCredentialsProvider())
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();
    }

    //S3 버켓과 연결할 커넥션 얻어오기(내 컴퓨터 내에 C:/USER/사용자이름/.aws/credentials 파일을 만들어서 자격증명을함 , 보안상 안전할듯)  --- 추천 방식
    @Bean
    public AmazonS3 createConn2() {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();
    }




}
