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
public class GridMemberExcel {
    @ExcelProperty(value = "Grid Member ID")
    private Integer gmId;
    @ExcelProperty(value = "Grid Member Name")
    private String gmName;
    @ExcelProperty(value = "Grid Member Login Code")
    private String gmCode;
    @ExcelProperty(value = "Grid Member Login Password")
    private String password;
    @ExcelProperty(value = "Grid Area: Province ID")
    private Integer provinceId;
    @ExcelProperty(value = "Grid Area: City ID")
    private Integer cityId;
    @ExcelProperty(value = "Contact Phone")
    private String tel;
    @ExcelProperty(value = "Grid Member Status (0: Working; 1: Not Working (managed by attendance system); 2: Other)")
    private Integer state;
    @ExcelProperty(value = "Remarks")
    private String remarks;

}