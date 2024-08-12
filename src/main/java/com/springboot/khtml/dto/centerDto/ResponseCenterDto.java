package com.springboot.khtml.dto.centerDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseCenterDto {
    private Long id;

    private String region;
    private String district;
    private String center_name;
    private String designated_beds;
    private String contact_number;
}
