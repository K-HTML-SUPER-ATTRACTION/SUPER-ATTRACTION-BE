package com.springboot.khtml.dao.Impl;

import com.springboot.khtml.dao.CenterDao;
import com.springboot.khtml.entity.Center;
import com.springboot.khtml.repository.CenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CenterDaoImpl implements CenterDao {
    private final CenterRepository centerRepository;

    @Override
    public Optional<Center> getCenterById(Long id) {
        return centerRepository.findById(id);
    }
}
