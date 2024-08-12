package com.springboot.khtml.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final Logger logger = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
           throws IOException {
       ObjectMapper objectMapper = new ObjectMapper();

       EntryPointErrorPointMsg entryPointErrorPointMsg = new EntryPointErrorPointMsg();
       entryPointErrorPointMsg.setMsg("인증 실패");

       response.setStatus(401);
       response.setContentType("application/json");
       response.setCharacterEncoding("utf-8");
       response.getWriter().write(objectMapper.writeValueAsString(entryPointErrorPointMsg));

   }

}
