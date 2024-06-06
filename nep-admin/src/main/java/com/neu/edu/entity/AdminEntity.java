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
@TableName("admins")
public class AdminEntity {

    /**
     * System administrator ID
     */
	private Integer adminId;
    /**
     * System administrator login code
     */
	private String adminCode;
    /**
     * System administrator login password
     */
	private String password;
    /**
     * Remarks
     */
	private String remarks;
}