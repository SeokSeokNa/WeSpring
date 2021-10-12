package com.nh.service;

import com.nh.dto.BoardDto;
import com.nh.dto.GalleryDto;

import java.util.List;

public interface GalleryService {

    public int galleryInsert(GalleryDto galleryDto);

    public List<GalleryDto> galleryList(int offset, int limit);

    public int galleryDelete(int galleryNo);

    public int totalGalleryCount();

}
