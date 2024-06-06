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
public class GridProvinceExcel {
    @ExcelProperty(value = "System Grid Coverage Province Area ID")
    private Integer provinceId;
    @ExcelProperty(value = "System Grid Coverage Province Area Name")
    private String provinceName;
    @ExcelProperty(value = "System Grid Coverage Province Area Abbreviation")
    private String provinceAbbr;
    @ExcelProperty(value = "Remarks")
    private String remarks;

}