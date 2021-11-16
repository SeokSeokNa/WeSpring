package com.nh.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
@PropertySource("classpath:AwsCredentials.properties")
public class AwsFile {

    @Autowired
    private AmazonS3 createConn2;
    @Value("${bucket}")
    private String bucket;

    //멀티파트 유형으로 넘어온 파일들을 File 객체로 만들고 , 업로드 시키는 로직
    public String upload(MultipartFile multipartFile, String dirName , UUID uuid) throws IOException {
        /*File file = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("Multipart 파일을 File 객체로 변환실패!"));*/
        return fileUpload(multipartFile, dirName , uuid);
    }

    //MultiPart 타입 파일을 File 타입으로 변경
    private Optional<File> convert(MultipartFile file) throws IOException {
        System.out.println("파일명 = " +file.getOriginalFilename());
        File convertFile = new File(file.getOriginalFilename());
        if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
            try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함 , try with resource 문법
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }


    // S3로 파일 업로드하기
    private String fileUpload(MultipartFile uploadFile, String dirName , UUID uuid) throws IOException {
        System.out.println("버켓 = " +bucket);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(MediaType.IMAGE_PNG_VALUE);
        metadata.setContentLength(uploadFile.getSize());
        String fileName = dirName + "/" + uuid + "_" +uploadFile.getName();   // 저장될 경로와 파일명
        createConn2.putObject(new PutObjectRequest(bucket, fileName, uploadFile.getInputStream(), metadata));
        return createConn2.getUrl(bucket , fileName).toString();
    }


    //파일 삭제시
    public void deleteFiles(String[] files) {
        DeleteObjectsRequest deleteFiles = new DeleteObjectsRequest(bucket).withKeys(files);
        createConn2.deleteObjects(deleteFiles);
    }



}
