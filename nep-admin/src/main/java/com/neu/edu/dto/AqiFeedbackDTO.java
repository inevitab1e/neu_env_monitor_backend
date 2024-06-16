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
public class AqiFeedbackDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Air Quality Public Supervision Feedback Information ID")
	private Integer afId;

	@ApiModelProperty(value = "Public Supervisor ID (i.e., phone number)")
	private String telId;

	@ApiModelProperty(value = "Province Area ID of Feedback Information")
	private Integer provinceId;

	@ApiModelProperty(value = "City Area ID of Feedback Information")
	private Integer cityId;

	@ApiModelProperty(value = "Detailed Address of Feedback Information")
	private String address;

	@ApiModelProperty(value = "Description of Feedback Information")
	private String information;

	@ApiModelProperty(value = "Estimated Air Quality Index Level by the Feedback Provider")
	private Integer estimatedGrade;

	@ApiModelProperty(value = "Feedback Date")
	private String afDate;

	@ApiModelProperty(value = "Feedback Time")
	private String afTime;

	@ApiModelProperty(value = "Assigned Grid Member ID")
	private Integer gmId;

	@ApiModelProperty(value = "Assignment Date")
	private String assignDate;

	@ApiModelProperty(value = "Assignment Time")
	private String assignTime;

	@ApiModelProperty(value = "Information Status: 0: Not Assigned; 1: Assigned; 2: Confirmed")
	private Integer state;

	@ApiModelProperty(value = "Remarks")
	private String remarks;


}