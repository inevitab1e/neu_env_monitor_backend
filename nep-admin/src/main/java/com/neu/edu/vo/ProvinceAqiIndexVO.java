package com.neu.edu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceAqiIndexVO {
    private Integer provinceId;
    private String provinceName;
    private String provinceAbbr;
    private Integer so2ExceededCount;
    private Integer coExceededCount;
    private Integer spmExceededCount;
    private Integer aqiExceedCount;
}
