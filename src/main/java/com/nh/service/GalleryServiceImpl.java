package com.nh.service;

import com.nh.dao.GalleryDao;
import com.nh.dto.BoardDto;
import com.nh.dto.GalleryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService{

    @Autowired
    GalleryDao galleryDao;

    @Override
    public int galleryInsert(GalleryDto galleryDto) {
        return galleryDao.galleryInsert(galleryDto);
    }

    @Override
    public List<GalleryDto> galleryList(int offset, int limit) {
        return galleryDao.galleryList(offset, limit);
    }
    @Override
    public int galleryDelete(int galleryNo){
        return galleryDao.galleryDelete(galleryNo);
    }

    @Override
    public int totalGalleryCount(){
        return galleryDao.totalGalleryCount();
    }
}
