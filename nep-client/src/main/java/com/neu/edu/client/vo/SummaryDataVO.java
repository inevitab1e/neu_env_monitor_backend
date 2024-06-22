package com.neu.edu.client.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryDataVO {
    private Integer total;
    private Integer goodCount;
    private Integer exceededCount;
}
