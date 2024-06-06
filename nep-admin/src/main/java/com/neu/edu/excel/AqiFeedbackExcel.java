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
public class AqiFeedbackExcel {
    @ExcelProperty(value = "Air Quality Public Supervision Feedback Information ID")
    private Integer afId;
    @ExcelProperty(value = "Public Supervisor ID (i.e., phone number)")
    private String telId;
    @ExcelProperty(value = "Province Area ID of Feedback Information")
    private Integer provinceId;
    @ExcelProperty(value = "City Area ID of Feedback Information")
    private Integer cityId;
    @ExcelProperty(value = "Detailed Address of Feedback Information")
    private String address;
    @ExcelProperty(value = "Description of Feedback Information")
    private String information;
    @ExcelProperty(value = "Estimated Air Quality Index Level by the Feedback Provider")
    private Integer estimatedGrade;
    @ExcelProperty(value = "Feedback Date")
    private String afDate;
    @ExcelProperty(value = "Feedback Time")
    private String afTime;
    @ExcelProperty(value = "Assigned Grid Member ID")
    private Integer gmId;
    @ExcelProperty(value = "Assignment Date")
    private String assignDate;
    @ExcelProperty(value = "Assignment Time")
    private String assignTime;
    @ExcelProperty(value = "Information Status: 0: Not Assigned; 1: Assigned; 2: Confirmed")
    private Integer state;
    @ExcelProperty(value = "Remarks")
    private String remarks;

}