package com.neu.edu.client;

import com.neu.edu.common.utils.Result;
import com.neu.edu.dto.AqiDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "aqi-service")
public interface AqiClient {
    @GetMapping("nep/aqi/")
    Result<List<AqiDTO>> getAqiList();

    @GetMapping("nep/aqi/{aqiId}")
    Result<AqiDTO> get(@PathVariable("aqiId") Long aqiId);
}
