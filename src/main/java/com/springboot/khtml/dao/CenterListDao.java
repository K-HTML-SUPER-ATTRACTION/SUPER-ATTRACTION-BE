package com.springboot.khtml.dao;

import com.springboot.khtml.entity.Center;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CenterListDao {
    Page<Center> findCenterList(Pageable pageable);
}
