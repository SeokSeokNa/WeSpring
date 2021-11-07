package com.nh.dto.kakao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(
        {"profile_needs_agreement","profile_nickname_needs_agreement" , "profile_image_needs_agreement"
         ,"email_needs_agreement" , "is_email_valid" , "is_email_verified" , "has_email"
        })
public class KakaoAccount {

    private Profile profile;
    private String gender;
    private String email;
}
