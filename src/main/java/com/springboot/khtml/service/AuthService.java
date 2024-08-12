package com.springboot.khtml.service;

import com.springboot.khtml.dto.signDto.SignInResultDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> getKakaoUserInfo(String authorizeCode);
    SignInResultDto kakao_SignIn(String authorizeCode);

}
