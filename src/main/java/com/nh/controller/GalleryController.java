package com.nh.controller;

import com.nh.aws.AwsFile;
import com.nh.dto.GalleryDto;
import com.nh.dto.PhotoDto;
import com.nh.service.GalleryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GalleryController {

    private final AwsFile awsFile;

    private final String uploadPath;

    private final GalleryService galleryService;

    @GetMapping("/gallery/list")
    public String galleryList(@RequestParam(name = "currentPage", defaultValue = "0") int currentPage, Model model) {

        int pageData = 6; //한 페이지당 글이 몇개씩 보일 것인지
        int offset = currentPage * pageData; //조회할 글 목록의 시작지점
        int totalBoardCount = galleryService.totalGalleryCount(); //총 게시글 수
        int pageCount = (totalBoardCount/pageData) + ((totalBoardCount%pageData) > 0 ? 1:0); //총 페이지 버튼의 갯수

        //------------------------------------------------------2021-10-02 추가내용
        //페이지 번호가 한번에 몇개부터 몇개씩 보일것인지 !
        int pageMaxNum = 5; // 한번에 페이지 버튼이 몇개씩 보일것인가에 대한 값이다.
        int start = Math.max(1 , currentPage/pageMaxNum*pageMaxNum+1);//페이지 번호를 몇번부터 표시할건지
        int end = Math.min(pageCount , start+pageMaxNum-1);//페이지 번호를 몇번까지 표시할건지
        //------------------------------------------------------//

        List<GalleryDto> galleryList = galleryService.galleryList(offset, pageData);
        model.addAttribute("galleryList", galleryList);
        model.addAttribute("currentPage",currentPage); //현재 페이지(화면에서 페이지버튼 눌린표시랑 안눌린표시 , 이전버튼 , 다음버튼 활성화 비활성화 할때 필요)
        model.addAttribute("pageCount",pageCount); //총 페이지수(화면에서 페이지 버튼 개수 만들때 필요 , 이전버튼 , 다음버튼 활성화 비활성화 할때 필요)
        model.addAttribute("start", start);//화면에서 페이지 번호 시작점을 나타낼 수 있다.
        model.addAttribute("end", end);//화면에서 페이지 번호 끝점을 나타낼 수 있다.

        return "gallery/gallery_list";
    }

    @GetMapping("/gallery/new")
    public String galleryForm() {
        return "gallery/gallery_form";
    }

    @PostMapping("/gallery/new")
    public String galleryInsert(GalleryDto galleryDto , MultipartFile[] files) throws IOException {
        //AWS S3 사용 전 코드

        //파일이 저장될 디렉터리 경로 가 있는지 없는지
//        if (!new File(uploadPath).exists()) {
//            new File(uploadPath).mkdirs();
//        }

//        for (MultipartFile file : files) {
//            PhotoDto photoDto = new PhotoDto();
//            UUID uuid = UUID.randomUUID();//랜덤 값
//            String newName = uuid+"_"+file.getOriginalFilename();
//            photoDto.setOrgName(file.getOriginalFilename());
//            photoDto.setNewName(newName);
//            photoDto.setLocation(uploadPath+newName);
//            galleryDto.getPhotoDtoList().add(photoDto);
//            File target = new File(uploadPath, newName);//생성될 파일의 경로 , 파일이름
//            file.transferTo(target);//파일생성
//        }

        //AWS S3 사용 후 코드
        for (MultipartFile file : files) {
            UUID uuid = UUID.randomUUID();
            String imageUrl = awsFile.upload(file, "image" , uuid);
            log.debug("imageUrl = {} " , imageUrl);
            PhotoDto photoDto = new PhotoDto();
            photoDto.setOrgName(file.getOriginalFilename());
            photoDto.setNewName(uuid + "_" +file.getName());
            photoDto.setLocation(imageUrl);
            galleryDto.getPhotoDtoList().add(photoDto);
        }

        galleryService.galleryInsert(galleryDto);

        return "redirect:/gallery/list";
    }

    @PostMapping("/gallery/delete")
    @ResponseBody
    public String galleryDelete(@RequestParam("valueArr")int[] valueArr , @RequestParam("nameArr")String[] nameArr) {
        System.out.println("파라미터 길이  = "+valueArr.length);
        int returnVal = 0;
        int size = valueArr.length;
        for (int i = 0; i < size; i++){
            returnVal = galleryService.galleryDelete(valueArr[i]);
        }
        if (returnVal == 1) {
            awsFile.deleteFiles(nameArr);
        }
        return Integer.toString(returnVal);
    }



}

