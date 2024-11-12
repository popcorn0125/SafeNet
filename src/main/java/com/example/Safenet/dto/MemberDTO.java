package com.example.Safenet.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDTO {
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private String email;
    private String role;
    private Integer provinceId;
    private Integer cityId;
    private Integer townId;

    // 추가적인 Getter, Setter는 Lombok의 @Data가 생성해줍니다.
}
