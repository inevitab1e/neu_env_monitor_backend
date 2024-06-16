package com.neu.edu.client;

import com.neu.edu.common.utils.Result;
import com.neu.edu.dto.GridCityDTO;
import com.neu.edu.dto.GridProvinceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "location-service")
public interface LocationClient {
    @GetMapping("nep/gridcity/{cityId}")
    Result<GridCityDTO> getCityByid(@PathVariable("cityId") Integer cityId);

    @GetMapping("nep/gridprovince/{provinceId}")
    Result<GridProvinceDTO> getProvinceById(@PathVariable("provinceId") Integer provinceId);
}
