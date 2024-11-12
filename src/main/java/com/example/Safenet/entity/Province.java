package com.example.Safenet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Province {
    @Id
    private Integer provinceId;
    private String provinceName;
}

