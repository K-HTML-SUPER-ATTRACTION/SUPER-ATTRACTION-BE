package com.springboot.khtml.dto.centerDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCenterListDto {
    private List<ResponseCenterDto> items;
}
