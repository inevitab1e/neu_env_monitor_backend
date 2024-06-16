package com.neu.edu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 
 *
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@Data
@TableName("grid_province")
public class GridProvinceEntity {

    /**
     * System Grid Coverage Province Area ID
     */
    @TableId
    private Integer provinceId;
    /**
     * System Grid Coverage Province Area Name
     */
	private String provinceName;
    /**
     * System Grid Coverage Province Area Abbreviation
     */
	private String provinceAbbr;
    /**
     * Remarks
     */
	private String remarks;
}