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
public class StatisticsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Statistics Information ID")
	private Integer id;

	@ApiModelProperty(value = "Province Area ID")
	private Integer provinceId;

	@ApiModelProperty(value = "City Area ID")
	private Integer cityId;

	@ApiModelProperty(value = "Detailed Address of Feedback Information")
	private String address;

	@ApiModelProperty(value = "Sulfur Dioxide Concentration (unit: μg/m3)")
	private Integer so2Value;

	@ApiModelProperty(value = "Sulfur Dioxide Index Level")
	private Integer so2Level;

	@ApiModelProperty(value = "Carbon Monoxide Concentration (unit: μg/m3)")
	private Integer coValue;

	@ApiModelProperty(value = "Carbon Monoxide Index Level")
	private Integer coLevel;

	@ApiModelProperty(value = "Suspended Particulate Matter Concentration (unit: μg/m3)")
	private Integer spmValue;

	@ApiModelProperty(value = "PM2.5 Index Level")
	private Integer spmLevel;

	@ApiModelProperty(value = "Air Quality Index Level")
	private Integer aqiId;

	@ApiModelProperty(value = "Confirmation Date")
	private String confirmDate;

	@ApiModelProperty(value = "Confirmation Time")
	private String confirmTime;

	@ApiModelProperty(value = "Grid Member ID")
	private Integer gmId;

	@ApiModelProperty(value = "Feedback Provider ID (Public Supervisor Phone Number)")
	private String fdId;

	@ApiModelProperty(value = "Description of Feedback Information")
	private String information;

	@ApiModelProperty(value = "Remarks")
	private String remarks;


}