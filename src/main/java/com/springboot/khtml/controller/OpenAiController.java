package com.springboot.khtml.controller;

import com.springboot.khtml.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class OpenAiController {
        private final OpenAIService openAIService;

        @PostMapping("/openai")
        public ResponseEntity<String> chat(@RequestBody Map<String, String> request) {
            String userMessage = request.get("message");
            String response = openAIService.getChatResponse(userMessage);
            return ResponseEntity.ok(response);
        }
    }

