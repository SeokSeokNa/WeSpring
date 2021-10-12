package com.nh.mapper;

import com.nh.dto.BoardDto;
import com.nh.dto.GalleryDto;
import com.nh.dto.PhotoDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GalleryMapper {

    public int galleryInsert(GalleryDto galleryDto);

    public int photoInsert(PhotoDto photoDto);

    public List<GalleryDto> getGallery(@Param("offset") int offset, @Param("limit") int limit);

    public int galleryDelete(int galleryNo);

    public int galleryPhotoDelete(int galleryNo);

    public int totalGalleryCount();
}
