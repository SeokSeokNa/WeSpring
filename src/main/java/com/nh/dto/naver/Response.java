package com.nh.dto.naver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties({"birthyear"})
public class Response {
    String id;
    String nickname;
    String name;
    String email;
    String gender;
    String age;
    String profile_image;

}
