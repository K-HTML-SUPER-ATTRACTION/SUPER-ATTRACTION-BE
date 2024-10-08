package com.springboot.khtml.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChatGpt {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String userMessage;
        private String assistantMessage;

    }


