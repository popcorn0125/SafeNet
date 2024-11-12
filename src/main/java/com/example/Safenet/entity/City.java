package com.example.Safenet.entity;

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

