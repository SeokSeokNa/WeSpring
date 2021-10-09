package com.nh.mapper;

import com.nh.dto.BoardDto;
import com.nh.dto.GalleryDto;
import com.nh.dto.PhotoDto;

import java.util.List;

public interface GalleryMapper {

    public int galleryInsert(GalleryDto galleryDto);

    public int photoInsert(PhotoDto photoDto);

    public List<GalleryDto> getGallery();
}
