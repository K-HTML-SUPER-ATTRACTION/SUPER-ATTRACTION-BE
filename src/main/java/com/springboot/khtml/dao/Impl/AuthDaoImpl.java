package com.springboot.khtml.dao.Impl;

import com.springboot.khtml.dao.AuthDao;
import com.springboot.khtml.entity.User;
import com.springboot.khtml.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDaoImpl implements AuthDao {
    private final UserRepository userRepository;

    public void save(User user){
        userRepository.save(user);
    }

    @Override
    public User KakaoUserSave(User user) {
        return userRepository.save(user);
    }

    @Override
    public User kakaoUserFind(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public User findByEmailAndConfirmationCode(String email, String confirmationCode) {
        return userRepository.findByEmailAndConfirmationCode(email, confirmationCode);
    }
    @Override
    public User findByEmailAndVerifiedTrue(String email) {
        return userRepository.findByEmailAndVerifiedTrue(email);
    }

}
