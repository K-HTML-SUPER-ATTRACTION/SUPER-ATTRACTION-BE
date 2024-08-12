package com.springboot.khtml.controller;

import com.springboot.khtml.dto.centerDto.ResponseCenterDto;
import com.springboot.khtml.dto.centerDto.ResponseCenterListDto;
import com.springboot.khtml.service.CenterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class CenterController {

    private final CenterService centerService;

    @GetMapping("/getCenter/{id}")
    public ResponseEntity<ResponseCenterDto> getCompetition(@PathVariable Long id) throws Exception{
        ResponseCenterDto selectedCenterDto = centerService.getCenter(id);
        return ResponseEntity.status(HttpStatus.OK).body(selectedCenterDto);
    }


    @GetMapping("/getCenterList")
    public ResponseEntity<ResponseCenterListDto> getCompetitionList(@RequestParam(value = "page", required = true) int page, HttpServletRequest servletRequest) throws Exception {
        ResponseCenterListDto centerList = centerService.getCenterList(page - 1, servletRequest);
        return ResponseEntity.status(HttpStatus.OK).body(centerList);
    }
}
