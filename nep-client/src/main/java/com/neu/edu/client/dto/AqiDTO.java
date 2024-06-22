package com.neu.edu.client.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 
 *
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@Data
@ApiModel(value = "")
public class AqiDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Air Quality Index Level (total of six levels)")
	private Integer aqiId;

	@ApiModelProperty(value = "Chinese representation of Air Quality Index level")
	private String chineseExplain;

	@ApiModelProperty(value = "Description of Air Quality Index level")
	private String aqiExplain;

	@ApiModelProperty(value = "Color representing Air Quality Index level")
	private String color;

	@ApiModelProperty(value = "Health impact")
	private String healthImpact;

	@ApiModelProperty(value = "Recommended measures")
	private String takeSteps;

	@ApiModelProperty(value = "Minimum concentration of sulfur dioxide at this level")
	private Integer so2Min;

	@ApiModelProperty(value = "Maximum concentration of sulfur dioxide at this level")
	private Integer so2Max;

	@ApiModelProperty(value = "Minimum concentration of carbon monoxide at this level")
	private Integer coMin;

	@ApiModelProperty(value = "Maximum concentration of carbon monoxide at this level")
	private Integer coMax;

	@ApiModelProperty(value = "Minimum concentration of suspended particulate matter at this level")
	private Integer spmMin;

	@ApiModelProperty(value = "Maximum concentration of suspended particulate matter at this level")
	private Integer spmMax;

	@ApiModelProperty(value = "Remarks")
	private String remarks;


}