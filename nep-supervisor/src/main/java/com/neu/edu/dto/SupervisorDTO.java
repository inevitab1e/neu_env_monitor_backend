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
public class SupervisorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Public Supervisor ID (i.e., phone number)")
	private String telId;

	@ApiModelProperty(value = "Public Supervisor Login Password")
	private String password;

	@ApiModelProperty(value = "Public Supervisor Real Name")
	private String realName;

	@ApiModelProperty(value = "Public Supervisor Birthday")
	private String birthday;

	@ApiModelProperty(value = "Public Supervisor Gender (1: Male; 0: Female)")
	private Integer sex;

	@ApiModelProperty(value = "Remarks")
	private String remarks;

	@ApiModelProperty(value = "Token")
	private String token;

}