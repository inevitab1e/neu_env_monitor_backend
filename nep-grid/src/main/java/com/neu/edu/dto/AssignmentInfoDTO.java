package com.neu.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentInfoDTO {
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
    private String aqiId;
    private String afDate;
    private String afTime;
    private String assignDate;
    private String assignTime;
    private String confirmDate;
    private String confirmTime;
    private Integer state;
    private Integer gmId;
    private Integer so2Value;
    private Integer so2Level;
    private Integer coValue;
    private Integer coLevel;
    private Integer spmValue;
    private Integer spmLevel;
    private String fdId;
}
