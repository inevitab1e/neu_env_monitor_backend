package com.neu.edu.entity;

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
@TableName("grid_city")
public class GridCityEntity {

    /**
     * System Grid Coverage City Area ID
     */
	private Integer cityId;
    /**
     * System Grid Coverage City Area Name
     */
	private String cityName;
    /**
     * Province Area ID
     */
	private Integer provinceId;
    /**
     * Remarks
     */
	private String remarks;
}