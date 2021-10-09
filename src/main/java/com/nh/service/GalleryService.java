package com.nh.service;

import com.nh.dto.GalleryDto;

import java.util.List;

public interface GalleryService {

    public int galleryInsert(GalleryDto galleryDto);

    public List<GalleryDto> galleryList();

}
