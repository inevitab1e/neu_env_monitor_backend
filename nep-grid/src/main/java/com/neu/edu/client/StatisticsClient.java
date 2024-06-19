package com.neu.edu.client;

import com.neu.edu.common.utils.Result;
import com.neu.edu.dto.StatisticsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "statistics-service")
public interface StatisticsClient {
    @PostMapping("nep/statistics/save")
    Result save(@RequestBody StatisticsDTO dto);
}
