package com.springboot.khtml.dao;

import com.springboot.khtml.entity.Center;

import java.util.Optional;

public interface CenterDao {
    Optional<Center> getCenterById(Long id);
}
