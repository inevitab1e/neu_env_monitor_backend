package com.neu.edu.dto;

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
public class AdminDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "System administrator ID")
	private Integer adminId;

	@ApiModelProperty(value = "System administrator login code")
	private String adminCode;

	@ApiModelProperty(value = "System administrator login password")
	private String password;

	@ApiModelProperty(value = "Remarks")
	private String remarks;


}