package com.neu.edu.client.client;

import com.neu.edu.common.utils.Result;
import com.neu.edu.client.dto.AqiDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

@FeignClient(value = "aqi-service")
// 交给spring管理
@Resource
public interface AqiClient {
    @GetMapping("nep/aqi/")
    Result<List<AqiDTO>> getAqiList();

    @GetMapping("nep/aqi/{aqiId}")
    Result<AqiDTO> get(@PathVariable("aqiId") Long aqiId);
}
