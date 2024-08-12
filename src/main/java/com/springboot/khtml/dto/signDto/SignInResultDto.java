package com.springboot.khtml.dto.signDto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignInResultDto extends ResultDto {

  private String token;

    @Builder
    public SignInResultDto(boolean success, int code, String msg, String token,String detailMessage) {
        super(success, code, msg,detailMessage);
        this.token = token;
    }
}
