package com.nh.dto.kakao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties({"is_default_image"})
public class Profile {
    private String nickname;
    private String thumbnail_image_url;
    private String profile_image_url;
}
