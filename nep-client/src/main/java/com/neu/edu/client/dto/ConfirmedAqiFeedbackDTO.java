package com.neu.edu.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmedAqiFeedbackDTO {
    private Integer id;

    private Integer provinceId;
    private String provinceName;
    private Integer cityId;
    private String cityName;
    private String address;

    private Integer aqiId;

    private String confirmDate;
    private String confirmTime;

    private Integer gmId;
    private String gmName;
    private String tel;

    private String fdId;
    private String fdName;

    private String information;
}
