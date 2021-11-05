package com.nh.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nh.dto.kakao.KakaoTokenDto;
import com.nh.dto.kakao.KakaoUserDto;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Controller
public class SocialController {

    private static final String kakoAuthUrl = "https://kauth.kakao.com";
    private static final String kakaoApiKey = "333f28a81c82bbd17009776e9dc42d8b";
    private static final String redirectURI = "http://localhost:8080/oauth/result";

    //로그인 페이지 열기
    @GetMapping(value = "/login/getKakaoAuthUrl")
    @ResponseBody
    public String kakaoLoginPage() {
        String reqUrl = kakoAuthUrl + "/oauth/authorize?client_id=" + kakaoApiKey + "&redirect_uri=" + redirectURI + "&response_type=code" +"&scope=profile,account_email,gender";

        return reqUrl;
    }

    //로그인 하기(인가코드 받아 토큰 추출)
    @GetMapping("/oauth/result") //Redirect Uri에 해당하는 Url
    public String loginKakao(@RequestParam("code") String code, @RequestParam(required = false, name = "error") String error) {
        System.out.println("code = " + code);
        if (error != null) {
            System.out.println(error);
            return "redirect:/";
        }

        KakaoTokenDto tokenDto = getAccessToken(code);
        System.out.println("access_token = " + tokenDto.getAccess_token());
        KakaoUserDto userInfo = getUserInfo(tokenDto.getAccess_token());
        System.out.println("userInfo = " + userInfo);


        //토큰으로 정보 조회후 현재 내 회원테이블에 있으면 넘어가고 없으면 회원가입 시킨 후 로그인 바로 시켜야 할듯

        return "redirect:/";
    }

    //토큰얻기
    private KakaoTokenDto getAccessToken(String code) {

        // 1. RestTemple 객체 생성시 필요한 파라미터로 타임아웃같은 설정을 건들수 있음
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000); //연결 타임아웃 5초
        factory.setReadTimeout(5000);//응답 타임아웃 5초
        RestTemplate restTemplate = new RestTemplate(factory);


        // 2. 토큰 요청 url 만들기   ,https://kauth.kakao.com/oauth/token
        String reqUrl = "/oauth/token";
        URI tokenUrl = URI.create(kakoAuthUrl + reqUrl);

        // 3. 헤더 객체 생성 하여 Content-type 설정하기
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");


        // 4. httpBody부분 생성 및 셋팅하기(보통 Map 형태이다)
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id",kakaoApiKey);
        params.add("redirect_uri",redirectURI);
        params.add("code", code);

        // 5. HttpHeader , HttpBody를 포함하여 HTTP요청을 하기위해 만든 객체
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        // 6. token api  요청하기 ~~~
        ResponseEntity<String> response = restTemplate.exchange(
                tokenUrl,
                HttpMethod.POST,
                entity,
                String.class
        );

        System.out.println(response.getBody());


        // 7. 받아온 JSON 형태 결과를 DTO에 매핑시키기
        ObjectMapper mapper = new ObjectMapper();
        KakaoTokenDto result = null;
        try {
            result = mapper.readValue(response.getBody(), KakaoTokenDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }


    //사용자 정보 얻기
    public KakaoUserDto getUserInfo(String accessToken) {

        // 1. RestTemple 객체 생성시 필요한 파라미터로 타임아웃같은 설정을 건들수 있음
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000); //연결 타임아웃 5초
        factory.setReadTimeout(5000);//응답 타임아웃 5초
        RestTemplate restTemplate = new RestTemplate(factory);

        // 2. 사용자 정보 요청 url 만들기   ,https://kapi.kakao.com/v2/user/me
        String reqUrl = "https://kapi.kakao.com/v2/user/me";


        // 3. 헤더 객체 생성 하여 Content-type 설정 및 헤더에 담아보내야할 파라미터인 Authorization 설정하기
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization" , "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // 5. HttpHeader를 담아 HTTP요청을 하기위해 만든 객체
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

        // 6. 사용자 정보 api 요청하기~~
        ResponseEntity<String> response = restTemplate.exchange(
                reqUrl,
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        System.out.println(response.getBody());

        // 7. 받아온 JSON 형태 결과를 DTO에 매핑시키기
        ObjectMapper mapper = new ObjectMapper();
        KakaoUserDto userInfo = null;
        try {
            userInfo = mapper.readValue(response.getBody(), KakaoUserDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return userInfo;

    }


}
