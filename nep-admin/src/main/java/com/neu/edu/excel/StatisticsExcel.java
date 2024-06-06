package com.neu.edu.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 
 *
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@Data
public class StatisticsExcel {
    @ExcelProperty(value = "Statistics Information ID")
    private Integer id;
    @ExcelProperty(value = "Province Area ID")
    private Integer provinceId;
    @ExcelProperty(value = "City Area ID")
    private Integer cityId;
    @ExcelProperty(value = "Detailed Address of Feedback Information")
    private String address;
    @ExcelProperty(value = "Sulfur Dioxide Concentration (unit: μg/m3)")
    private Integer so2Value;
    @ExcelProperty(value = "Sulfur Dioxide Index Level")
    private Integer so2Level;
    @ExcelProperty(value = "Carbon Monoxide Concentration (unit: μg/m3)")
    private Integer coValue;
    @ExcelProperty(value = "Carbon Monoxide Index Level")
    private Integer coLevel;
    @ExcelProperty(value = "Suspended Particulate Matter Concentration (unit: μg/m3)")
    private Integer spmValue;
    @ExcelProperty(value = "PM2.5 Index Level")
    private Integer spmLevel;
    @ExcelProperty(value = "Air Quality Index Level")
    private Integer aqiId;
    @ExcelProperty(value = "Confirmation Date")
    private String confirmDate;
    @ExcelProperty(value = "Confirmation Time")
    private String confirmTime;
    @ExcelProperty(value = "Grid Member ID")
    private Integer gmId;
    @ExcelProperty(value = "Feedback Provider ID (Public Supervisor Phone Number)")
    private String fdId;
    @ExcelProperty(value = "Description of Feedback Information")
    private String information;
    @ExcelProperty(value = "Remarks")
    private String remarks;

}