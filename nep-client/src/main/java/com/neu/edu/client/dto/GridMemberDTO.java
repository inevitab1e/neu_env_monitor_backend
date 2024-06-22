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
public class GridMemberDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Grid Member ID")
	private Integer gmId;

	@ApiModelProperty(value = "Grid Member Name")
	private String gmName;

	@ApiModelProperty(value = "Grid Member Login Code")
	private String gmCode;

	@ApiModelProperty(value = "Grid Member Login Password")
	private String password;

	@ApiModelProperty(value = "Grid Area: Province ID")
	private Integer provinceId;

	@ApiModelProperty(value = "Grid Area: City ID")
	private Integer cityId;

	@ApiModelProperty(value = "Contact Phone")
	private String tel;

	@ApiModelProperty(value = "Grid Member Status (0: Working; 1: Not Working (managed by attendance system); 2: Other)")
	private Integer state;

	@ApiModelProperty(value = "Remarks")
	private String remarks;


}