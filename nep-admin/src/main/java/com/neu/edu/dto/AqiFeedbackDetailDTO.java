package com.neu.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AqiFeedbackDetailDTO {
    private Integer afId;
    private String telId;
    private String realName;
    private Integer provinceId;
    private String provinceName;
    private Integer cityId;
    private String cityName;
    private String address;
    private String information;
    private Integer estimatedGrade;
    private String afDate;
    private String afTime;
    private Integer gmId;
    private String assignDate;
    private String assignTime;
    private Integer state;

}
