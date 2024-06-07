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
@TableName("grid_member")
public class GridMemberEntity {

    /**
     * Grid Member ID
     */
	private Integer gmId;
    /**
     * Grid Member Name
     */
	private String gmName;
    /**
     * Grid Member Login Code
     */
	private String gmCode;
    /**
     * Grid Member Login Password
     */
	private String password;
    /**
     * Grid Area: Province ID
     */
	private Integer provinceId;
    /**
     * Grid Area: City ID
     */
	private Integer cityId;
    /**
     * Contact Phone
     */
	private String tel;
    /**
     * Grid Member Status (0: Working; 1: Not Working (managed by attendance system); 2: Other)
     */
	private Integer state;
    /**
     * Remarks
     */
	private String remarks;
}