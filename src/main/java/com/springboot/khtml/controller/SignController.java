package com.springboot.khtml.controller;

import com.springboot.khtml.dto.signDto.ResultDto;
import com.springboot.khtml.dto.signDto.SignInResultDto;
import com.springboot.khtml.dto.signDto.SignUpFirstDto;
import com.springboot.khtml.service.AuthService;
import com.springboot.khtml.service.SignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/sign-api")
@Slf4j
@RequiredArgsConstructor
public class SignController {
    private final AuthService authService;
    private final SignService signService;

    @GetMapping("/kakao/callback")
    public ResponseEntity<?> getKakaoAuthorizeCode(@RequestParam("code") String authorizeCode){
        log.info("[kakao-login] authorizeCode {}", authorizeCode);
        return authService.getKakaoUserInfo(authorizeCode);
    }

    @PostMapping("/kakao/signin")
    public SignInResultDto kakao_SignIn(@RequestParam String accessToken){
        log.info("[kakao-login] accessToken {}", accessToken);
        return authService.kakao_SignIn(accessToken);
    }
    @PostMapping("/send-email")
    public ResponseEntity<Map<String,String>> sendSimpleMessage(String email, HttpServletRequest request) throws Exception{
        Map<String,String> response = signService.sendSimpleMessage(email,request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/verified")
    public ResponseEntity<Boolean> verifyEmail(String confirmationCode, HttpServletRequest request){
        boolean isVerified = signService.verifyEmail(confirmationCode,request);
        return ResponseEntity.status(HttpStatus.OK).body(isVerified);
    }
    @PostMapping("/signUp-first")
    public ResponseEntity<ResultDto> SignUpFirst(SignUpFirstDto signUpFirstDto, HttpServletRequest request){
        ResultDto resultDto = signService.SignUpFirst(signUpFirstDto,request);
        return ResponseEntity.status(HttpStatus.OK).body(resultDto);
    }

    @PostMapping("/signUp-second")
    public ResponseEntity<ResultDto> SignUpSecond(String userName, String gender, String address, String nickName, MultipartFile profile_image, HttpServletRequest request)
            throws IOException{
        ResultDto resultDto = signService.SignUpSecond(userName,gender,address,nickName,profile_image,request);
        return ResponseEntity.status(HttpStatus.OK).body(resultDto);
    }

    @PostMapping("/normal/signIn")
    public ResponseEntity<ResultDto> SignIn(String email, String password){
        ResultDto resultDto = signService.SignIn(email,password);
        return ResponseEntity.status(HttpStatus.OK).body(resultDto);
    }


}
