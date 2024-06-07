package com.neu.edu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 *
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@Data
@TableName("aqi")
public class AqiEntity {

    /**
     * Air Quality Index Level (total of six levels)
     */
	private Integer aqiId;
    /**
     * Chinese representation of Air Quality Index level
     */
	private String chineseExplain;
    /**
     * Description of Air Quality Index level
     */
	private String aqiExplain;
    /**
     * Color representing Air Quality Index level
     */
	private String color;
    /**
     * Health impact
     */
	private String healthImpact;
    /**
     * Recommended measures
     */
	private String takeSteps;
    /**
     * Minimum concentration of sulfur dioxide at this level
     */
	private Integer so2Min;
    /**
     * Maximum concentration of sulfur dioxide at this level
     */
	private Integer so2Max;
    /**
     * Minimum concentration of carbon monoxide at this level
     */
	private Integer coMin;
    /**
     * Maximum concentration of carbon monoxide at this level
     */
	private Integer coMax;
    /**
     * Minimum concentration of suspended particulate matter at this level
     */
	private Integer spmMin;
    /**
     * Maximum concentration of suspended particulate matter at this level
     */
	private Integer spmMax;
    /**
     * Remarks
     */
	private String remarks;
}