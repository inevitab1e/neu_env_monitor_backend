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
public class AqiExcel {
    @ExcelProperty(value = "Air Quality Index Level (total of six levels)")
    private Integer aqiId;
    @ExcelProperty(value = "Chinese representation of Air Quality Index level")
    private String chineseExplain;
    @ExcelProperty(value = "Description of Air Quality Index level")
    private String aqiExplain;
    @ExcelProperty(value = "Color representing Air Quality Index level")
    private String color;
    @ExcelProperty(value = "Health impact")
    private String healthImpact;
    @ExcelProperty(value = "Recommended measures")
    private String takeSteps;
    @ExcelProperty(value = "Minimum concentration of sulfur dioxide at this level")
    private Integer so2Min;
    @ExcelProperty(value = "Maximum concentration of sulfur dioxide at this level")
    private Integer so2Max;
    @ExcelProperty(value = "Minimum concentration of carbon monoxide at this level")
    private Integer coMin;
    @ExcelProperty(value = "Maximum concentration of carbon monoxide at this level")
    private Integer coMax;
    @ExcelProperty(value = "Minimum concentration of suspended particulate matter at this level")
    private Integer spmMin;
    @ExcelProperty(value = "Maximum concentration of suspended particulate matter at this level")
    private Integer spmMax;
    @ExcelProperty(value = "Remarks")
    private String remarks;

}