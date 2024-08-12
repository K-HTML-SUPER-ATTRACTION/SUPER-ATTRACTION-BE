package com.springboot.khtml.service.Impl;

import com.springboot.khtml.dao.CenterDao;
import com.springboot.khtml.dao.CenterListDao;
import com.springboot.khtml.dto.centerDto.ResponseCenterDto;
import com.springboot.khtml.dto.centerDto.ResponseCenterListDto;
import com.springboot.khtml.entity.Center;
import com.springboot.khtml.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {

    private final CenterListDao centerListDao;
    private final CenterDao centerDao;

    @Override
    public ResponseCenterDto getCenter(Long id) throws Exception {
        Center center = centerDao.getCenterById(id)
                .orElseThrow(() -> new Exception("센터를 찾을 수 없습니다."));

        ResponseCenterDto responseCenterDto = new ResponseCenterDto();
        responseCenterDto.setCenter_name(center.getCenter_name());
        responseCenterDto.setId(center.getId());
        responseCenterDto.setRegion(center.getRegion());
        responseCenterDto.setDistrict(center.getDistrict());
        responseCenterDto.setDesignated_beds(center.getDesignated_beds());
        responseCenterDto.setContact_number(center.getContact_number());
        return responseCenterDto;
    }

    @Override
    public ResponseCenterListDto getCenterList(int page, HttpServletRequest servletRequest) {

        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Center> centerPage = centerListDao.findCenterList(pageRequest);
        List<Center> centerList = centerPage.getContent();

        List<ResponseCenterDto> responseCenterDtoList = new ArrayList<>();
        for (Center center : centerList) {
            ResponseCenterDto responseCenterDto = new ResponseCenterDto();
            responseCenterDto.setId(center.getId());
            responseCenterDto.setCenter_name(center.getCenter_name());
            responseCenterDto.setRegion(center.getRegion());
            responseCenterDto.setDistrict(center.getDistrict());
            responseCenterDto.setDesignated_beds(center.getDesignated_beds());
            responseCenterDto.setContact_number(center.getContact_number());
            responseCenterDtoList.add(responseCenterDto);
        }

        ResponseCenterListDto responseCenterListDto = new ResponseCenterListDto();
        responseCenterListDto.setItems(responseCenterDtoList);
        return responseCenterListDto;

    }
}
