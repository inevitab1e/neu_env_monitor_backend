package com.neu.edu.client.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 反馈记录VO
 *
 * @author FEI Bo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorAqiFeedbackRecordVO {
    private Integer estimatedGrade;
    private String color;
    private String afDate;
    private String afTime;
    private String province;
    private String city;
}
