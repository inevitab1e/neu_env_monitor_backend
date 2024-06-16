package com.neu.edu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 *
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@Data
@TableName("supervisor")
public class SupervisorEntity {

    /**
     * Public Supervisor ID (i.e., phone number)
     */
    @TableId
    private String telId;
    /**
     * Public Supervisor Login Password
     */
	private String password;
    /**
     * Public Supervisor Real Name
     */
	private String realName;
    /**
     * Public Supervisor Birthday
     */
	private String birthday;
    /**
     * Public Supervisor Gender (1: Male; 0: Female)
     */
	private Integer sex;
    /**
     * Remarks
     */
	private String remarks;
}