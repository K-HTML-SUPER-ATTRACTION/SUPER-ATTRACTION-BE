package com.springboot.khtml.dao;


import com.springboot.khtml.entity.User;

public interface AuthDao {

    User KakaoUserSave(User user);
    User kakaoUserFind(String email);
    void save(User user);

    User findByEmailAndConfirmationCode(String email, String confirmationCode);

    User findByEmailAndVerifiedTrue(String email);

}
