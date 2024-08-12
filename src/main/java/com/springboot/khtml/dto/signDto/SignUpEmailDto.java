package com.springboot.khtml.dto.signDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpEmailDto {
    private String email;
    private boolean verified;

}
