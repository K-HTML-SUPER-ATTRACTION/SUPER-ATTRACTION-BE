package com.springboot.khtml.dao.Impl;

import com.springboot.khtml.dao.CenterListDao;
import com.springboot.khtml.entity.Center;
import com.springboot.khtml.repository.CenterListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CenterListDaoImpl implements CenterListDao {

    private final CenterListRepository centerListRepository;

    @Override
    public Page<Center> findCenterList(Pageable pageable) {
        return centerListRepository.findAll(pageable);
    }
}
