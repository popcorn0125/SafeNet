package com.example.Safenet.entity;
//작성일:24.11.12
//        작성자:안현준
//        내용:로그인,회원가입 구현(세션방식)
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class City {
    @Id
    private Integer cityId;
    private String cityName;
    private Integer provinceId;
}

