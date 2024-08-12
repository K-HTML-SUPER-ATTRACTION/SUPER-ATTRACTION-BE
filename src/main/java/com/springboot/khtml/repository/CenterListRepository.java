package com.springboot.khtml.repository;

import com.springboot.khtml.entity.Center;
import com.springboot.khtml.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CenterListRepository extends JpaRepository<Center,Long> {
    Page<Center> findAll(Pageable pageable);
}
