package com.springboot.khtml.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {
    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private int port;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.auth}")
    private boolean auth;

    @Value("${mail.starttls.enable}")
    private boolean starttlsEnable;


    @Value("${mail.timeout}")
    private int timeout;


    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setJavaMailProperties(getMailProperties());

        return mailSender;
    }

    private Properties getMailProperties(){
        Properties properties = new Properties();
        //"mail.smtp.auth": 이메일 서버가 사용자 인증을 필요로 하는지 여부를 설정 변수 auth의 값에 따라 true 또는 false가 될 수 있음.
        properties.put("mail.smtp.auth", auth);
        properties.put("mail.host",host);
        properties.put("mail.username",username);
        properties.put("mail.password",password);

        //"mail.smtp.starttls.enable": SMTP 서버가 STARTTLS 명령을 사용하여 보안 연결을 시작할 수 있는지 여부를 설정.. 변수 starttlsEnable에 의해 결정.
        properties.put("mail.enable", starttlsEnable);
        //"mail.smtp.timeout": 메일 서버로부터 응답을 기다리는 타임아웃 시간(밀리초 단위)을 설정. 변수 timeout에 지정된 값으로 설정.
        properties.put("mail.timeout", timeout);


        return properties;
    }

}


