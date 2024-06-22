package com.neu.edu.client.client;

import com.neu.edu.client.dto.GridCityDTO;
import com.neu.edu.client.dto.GridProvinceDTO;
import com.neu.edu.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "location-service")
public interface LocationClient {
    @GetMapping("nep/gridcity/{cityId}")
    Result<GridCityDTO> getCityByid(@PathVariable("cityId") Integer cityId);

    @GetMapping("nep/gridcity/get_city_list_by_province_id/{provinceId}")
    Result<List<GridCityDTO>> getCityListByProvinceId(@PathVariable("provinceId") Integer provinceId);

    @GetMapping("nep/gridprovince/{provinceId}")
    Result<GridProvinceDTO> getProvinceById(@PathVariable("provinceId") Integer provinceId);

    @GetMapping("nep/gridprovince/list")
    Result<List<GridProvinceDTO>> getProvinceList();
}
