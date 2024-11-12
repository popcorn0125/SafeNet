package com.example.Safenet.dto;
//작성일:24.11.12
//        작성자:안현준
//        내용:로그인,회원가입 구현(세션방식)
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
