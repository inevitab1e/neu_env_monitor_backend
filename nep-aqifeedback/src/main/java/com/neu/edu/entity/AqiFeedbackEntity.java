package com.neu.edu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 *
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@Data
@TableName("aqi_feedback")
public class AqiFeedbackEntity {

    /**
     * Air Quality Public Supervision Feedback Information ID
     */
    @TableId
    private Integer afId;
    /**
     * Public Supervisor ID (i.e., phone number)
     */
	private String telId;
    /**
     * Province Area ID of Feedback Information
     */
	private Integer provinceId;
    /**
     * City Area ID of Feedback Information
     */
	private Integer cityId;
    /**
     * Detailed Address of Feedback Information
     */
	private String address;
    /**
     * Description of Feedback Information
     */
	private String information;
    /**
     * Estimated Air Quality Index Level by the Feedback Provider
     */
	private Integer estimatedGrade;
    /**
     * Feedback Date
     */
	private String afDate;
    /**
     * Feedback Time
     */
	private String afTime;
    /**
     * Assigned Grid Member ID
     */
	private Integer gmId;
    /**
     * Assignment Date
     */
	private String assignDate;
    /**
     * Assignment Time
     */
	private String assignTime;
    /**
     * Information Status: 0: Not Assigned; 1: Assigned; 2: Confirmed
     */
	private Integer state;
    /**
     * Remarks
     */
	private String remarks;
}