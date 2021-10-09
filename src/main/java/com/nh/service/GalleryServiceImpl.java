package com.nh.service;

import com.nh.dao.GalleryDao;
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
    public List<GalleryDto> galleryList() {
        return galleryDao.galleryList();
    }
}
