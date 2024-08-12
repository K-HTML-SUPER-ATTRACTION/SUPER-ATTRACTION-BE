package com.springboot.khtml.service;

import com.springboot.khtml.dto.signDto.ResultDto;
import com.springboot.khtml.dto.signDto.SignUpFirstDto;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public interface SignService {
    Map<String,String> sendSimpleMessage(String to, HttpServletRequest request) throws Exception;
    boolean verifyEmail(String confirmationCode, HttpServletRequest request);

    ResultDto SignUpFirst(SignUpFirstDto signUpFirstDto, HttpServletRequest request);
    ResultDto SignUpSecond(String userName, String gender, String address, String nickName,MultipartFile profile_image, HttpServletRequest request)throws IOException;

    ResultDto SignIn(String userId, String password);
}
