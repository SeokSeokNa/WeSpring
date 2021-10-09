package com.nh.controller;

import com.nh.dto.GalleryDto;
import com.nh.dto.PhotoDto;
import com.nh.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class GalleryController {

    @Autowired
    String uploadPath;

    @Autowired
    GalleryService galleryService;

    @GetMapping("/gallery/list")
    public String galleryList(Model model) {
        List<GalleryDto> galleryList = galleryService.galleryList();
        model.addAttribute("galleryList", galleryList);

        return "gallery/gallery_list";
    }

    @GetMapping("/gallery/new")
    public String galleryForm() {
        return "gallery/gallery_form";
    }

    @PostMapping("/gallery/new")
    public String galleryInsert(GalleryDto galleryDto , MultipartFile[] files) throws IOException {
        //파일이 저장될 디렉터리 경로 가 있는지 없는지
        if (!new File(uploadPath).exists()) {
            new File(uploadPath).mkdirs();
        }

        for (MultipartFile file : files) {
            PhotoDto photoDto = new PhotoDto();
            UUID uuid = UUID.randomUUID();//랜덤 값
            String newName = uuid+"_"+file.getOriginalFilename();
            photoDto.setOrgName(file.getOriginalFilename());
            photoDto.setNewName(newName);
            photoDto.setLocation(uploadPath+newName);
            galleryDto.getPhotoDtoList().add(photoDto);
            File target = new File(uploadPath, newName);//생성될 파일의 경로 , 파일이름
            file.transferTo(target);//파일생성
        }

        galleryService.galleryInsert(galleryDto);
        return "redirect:/";
    }



}

