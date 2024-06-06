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
public class SupervisorExcel {
    @ExcelProperty(value = "Public Supervisor ID (i.e., phone number)")
    private String telId;
    @ExcelProperty(value = "Public Supervisor Login Password")
    private String password;
    @ExcelProperty(value = "Public Supervisor Real Name")
    private String realName;
    @ExcelProperty(value = "Public Supervisor Birthday")
    private String birthday;
    @ExcelProperty(value = "Public Supervisor Gender (1: Male; 0: Female)")
    private Integer sex;
    @ExcelProperty(value = "Remarks")
    private String remarks;

}