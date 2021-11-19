package com.nh.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nh.dto.UserDto;
import com.nh.dto.kakao.KakaoTokenDto;
import com.nh.dto.kakao.KakaoUserDto;
import com.nh.jwt.JwtToken;
import com.nh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.HashMap;

@Controller
public class SocialController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtToken jwtToken;

    @Value("#{social['kakao.key']}") /*servlet-context.xml 파일에 util 옵션으로 properties 파일을 지정해야 읽기가능*/
    private String apiKey;

    private static final String authUrl = "https://kauth.kakao.com";
    private static final String redirectURI = "http://localhost:8080/oauth/result";
    private static final String scope = "profile_nickname , profile_image ,account_email";



    @GetMapping("/loginPage/kakao")
    public String showLoginPage() {
        String loginUrl = authUrl + "/oauth/authorize?client_id=" + apiKey + "&redirect_uri=" + redirectURI + "&response_type=code" +"&scope="+scope;
        return "redirect:"+loginUrl;
    } //showLoginPage

    //로그인 페이지 열기
    @GetMapping(value = "/login/getKakaoAuthUrl")
    @ResponseBody
    public String kakaoLoginPage() {
        String reqUrl = authUrl + "/oauth/authorize?client_id=" + apiKey + "&redirect_uri=" + redirectURI + "&response_type=code" +"&scope="+scope;

        return reqUrl;
    }

    //로그인 하기(인가코드 받아 토큰 추출)
    @GetMapping("/oauth/result") //Redirect Uri에 해당하는 Url
    public String loginKakao(@RequestParam("code") String code, @RequestParam(required = false, name = "error") String error , HttpServletResponse response) {
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
        HashMap<String, String> map = new HashMap<String, String>();

        map.put("userId", userInfo.getKakao_account().getEmail()); //카카오로부터 넘어온 이메일
        //암호화로직처리
        //암호화 로직을 통한 데이터를 map에 넣기
        map.put("userPass", "");

        UserDto findUser = userService.selectUser(map);
        if (findUser == null) {
            //카카오로부터 받아온 이메일로 조회된 회원이 없으면 회원가입
            UserDto userDto = new UserDto();
            userDto.setUserId(userInfo.getKakao_account().getEmail());
            //암호화로직처리
            //암호화 로직을 통한 데이터를 dto에 넣기
            userDto.setUserPass("1111");
            userDto.setUserName(userInfo.getKakao_account().getProfile().getNickname());
            userDto.setGender("남자");
            userDto.setProfileImg(userInfo.getProperties().getProfile_image());
            int result = userService.insertUser(userDto);
            if(result == 1 )
                //토큰쓸지 세션쓸지
                setToken(response, userDto);
        } else {
            //사진 업데이트 할꺼면 처리
            //토큰쓸지 세션쓸지
            setToken(response, findUser);
        }

        return "redirect:/";
    }



    //토큰방식은 세션방식과 달리 로그인관련 인증처리를 클라이언트가 가지고 있음
    //세션 방식은 서버가 가지고 있음
    private void setToken(HttpServletResponse  response, UserDto userDto) {
        //https://great-developer.tistory.com/59
        //헤더 방식 쓸거면 이거 참고하여 redirect 바로 하지말고 forward 를 써야할듯
        String accessToken = jwtToken.makeJwtToken(userDto.getUserId(), 0);
        String refreshToken = jwtToken.makeJwtToken(userDto.getUserId(), 1);
        setCookie(response, userDto.getUserName(),"user_name", 10*6*10 , false);//유저이름
        setCookie(response, userDto.getUserId(),"user_id", 10*6*10 , false);//유저아이디
        setCookie(response, accessToken ,"access_token" , 10*6*10 , true); //10분
        setCookie(response, refreshToken,"refresh_token", 10*6*60*24 , true);//24시간
    }

    private void setCookie(HttpServletResponse response, String value, String cookieName , int time , Boolean httpOnly) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setPath("/");
        cookie.setMaxAge(time);
        cookie.setHttpOnly(httpOnly);
        response.addCookie(cookie);
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
        URI tokenUrl = URI.create(authUrl + reqUrl);

        // 3. 헤더 객체 생성 하여 Content-type 설정하기(카카오 API 요청문서를 보면 이걸 넣고 해야된다고 설명되어 있어!)
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");


        // 4. httpBody부분 생성 및 셋팅하기(보통 Map 형태이다)
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id", apiKey);
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
