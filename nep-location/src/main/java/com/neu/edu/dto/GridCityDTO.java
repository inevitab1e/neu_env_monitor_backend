package com.neu.edu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 *
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@Data
@ApiModel(value = "")
public class GridCityDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "System Grid Coverage City Area ID")
	private Integer cityId;

	@ApiModelProperty(value = "System Grid Coverage City Area Name")
	private String cityName;

	@ApiModelProperty(value = "Province Area ID")
	private Integer provinceId;

	@ApiModelProperty(value = "Remarks")
	private String remarks;

	@ApiModelProperty(value = "0 Normal City 1 Big City 2 Provincial Capital City")
	private Integer cityLevel;
}