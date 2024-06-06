package com.neu.edu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 
 *
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@Data
@TableName("statistics")
public class StatisticsEntity {

    /**
     * Statistics Information ID
     */
	private Integer id;
    /**
     * Province Area ID
     */
	private Integer provinceId;
    /**
     * City Area ID
     */
	private Integer cityId;
    /**
     * Detailed Address of Feedback Information
     */
	private String address;
    /**
     * Sulfur Dioxide Concentration (unit: μg/m3)
     */
	private Integer so2Value;
    /**
     * Sulfur Dioxide Index Level
     */
	private Integer so2Level;
    /**
     * Carbon Monoxide Concentration (unit: μg/m3)
     */
	private Integer coValue;
    /**
     * Carbon Monoxide Index Level
     */
	private Integer coLevel;
    /**
     * Suspended Particulate Matter Concentration (unit: μg/m3)
     */
	private Integer spmValue;
    /**
     * PM2.5 Index Level
     */
	private Integer spmLevel;
    /**
     * Air Quality Index Level
     */
	private Integer aqiId;
    /**
     * Confirmation Date
     */
	private String confirmDate;
    /**
     * Confirmation Time
     */
	private String confirmTime;
    /**
     * Grid Member ID
     */
	private Integer gmId;
    /**
     * Feedback Provider ID (Public Supervisor Phone Number)
     */
	private String fdId;
    /**
     * Description of Feedback Information
     */
	private String information;
    /**
     * Remarks
     */
	private String remarks;
}