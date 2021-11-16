package com.nh.awsTest;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.nh.aws.AwsFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
@TestPropertySource(locations="classpath:AwsCredentials.properties")
@Slf4j
public class AwsTest {

    @Autowired private AmazonS3 createConn2;
    @Autowired private AwsFile awsFile;


    @Value("${bucket}")
    private String bucket;


    //bucket리스트 뽑기
    @Test
    public void bucketList() {
        List<Bucket> buckets = createConn2.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println("버켓 이름 = " + bucket.getName());
        }
    }


    //등록
    @Test
    public void putTest() throws IOException {
        UUID uuid = UUID.randomUUID();
        String fileName = "C://fileupload/mycap.jpg"; //내 컴퓨터에 있는 파일명 (경로까지 모두)
        MultipartFile multipartFile = convertMultiPart(fileName);

        String imageUrl = awsFile.upload(multipartFile, "image" , uuid);
        log.info("image url {} " , imageUrl);

    }


    //삭제
    @Test
    public void deleteTest() {
        //단일 파일 삭제
      /*  DeleteObjectRequest deleteFile = new DeleteObjectRequest(bucket, "image/test.png").withKey("image/mycap.png");
        createConn2.deleteObject(deleteFile);*/


        //여러파일 한번에 삭제
        String files[] = {"image/8f6ee204-23f9-4d4d-a12d-c3e56739bb0a_files" , "image/f0af3913-86a0-4bc8-a555-e94c53c97ce2_files"};
        DeleteObjectsRequest deleteFiles = new DeleteObjectsRequest(bucket).withKeys(files);
        createConn2.deleteObjects(deleteFiles);


    }


    public MultipartFile convertMultiPart(String path) throws IOException {
        File file = new File(path);
        FileItem fileItem = new DiskFileItem("mainFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());

        try {
            InputStream input = new FileInputStream(file);
            OutputStream os = fileItem.getOutputStream();
            IOUtils.copy(input, os);
            // Or faster..
            // IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());
        } catch (IOException ex) {
            // do something.
        }

        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
        return multipartFile;
    }
}
