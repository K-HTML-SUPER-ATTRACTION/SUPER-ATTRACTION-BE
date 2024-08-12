package com.springboot.khtml.service;

import com.springboot.khtml.dto.centerDto.ResponseCenterDto;
import com.springboot.khtml.dto.centerDto.ResponseCenterListDto;

import javax.servlet.http.HttpServletRequest;

public interface CenterService {
    ResponseCenterDto getCenter(Long cid) throws Exception;
    ResponseCenterListDto getCenterList(int page, HttpServletRequest servletRequest);
}
