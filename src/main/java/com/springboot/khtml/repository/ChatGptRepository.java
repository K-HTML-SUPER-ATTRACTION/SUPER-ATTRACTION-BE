package com.springboot.khtml.repository;

import com.springboot.khtml.entity.ChatGpt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatGptRepository extends JpaRepository<ChatGpt,Long> {

}
