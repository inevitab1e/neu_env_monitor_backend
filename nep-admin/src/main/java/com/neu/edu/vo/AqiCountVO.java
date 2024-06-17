package com.neu.edu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AqiCountVO {
    private Integer aqiId;
    private String aqiExplain;
    private Integer count;
}
