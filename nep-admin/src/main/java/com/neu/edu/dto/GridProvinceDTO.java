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
public class GridProvinceDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "System Grid Coverage Province Area ID")
	private Integer provinceId;

	@ApiModelProperty(value = "System Grid Coverage Province Area Name")
	private String provinceName;

	@ApiModelProperty(value = "System Grid Coverage Province Area Abbreviation")
	private String provinceAbbr;

	@ApiModelProperty(value = "Remarks")
	private String remarks;


}