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
public class AdminsExcel {
    @ExcelProperty(value = "System administrator ID")
    private Integer adminId;
    @ExcelProperty(value = "System administrator login code")
    private String adminCode;
    @ExcelProperty(value = "System administrator login password")
    private String password;
    @ExcelProperty(value = "Remarks")
    private String remarks;

}