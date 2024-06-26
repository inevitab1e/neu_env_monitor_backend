package com.neu.edu.client.client.fallback;

import com.neu.edu.client.client.LocationClient;
import com.neu.edu.client.dto.GridCityDTO;
import com.neu.edu.client.dto.GridProvinceDTO;
import com.neu.edu.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.List;

@Slf4j
public class LocationClientFallbackFactory implements FallbackFactory<LocationClient> {
    @Override
    public LocationClient create(Throwable cause) {
        return new LocationClient() {
            @Override
            public Result<GridCityDTO> getCityByid(Integer cityId) {
                return new Result<GridCityDTO>().error(429, "Flow Limiting");
            }

            @Override
            public Result<List<GridCityDTO>> getCityListByProvinceId(Integer provinceId) {
                return new Result<List<GridCityDTO>>().error(429, "Flow Limiting");
            }

            @Override
            public Result<GridProvinceDTO> getProvinceById(Integer provinceId) {
                return new Result<GridProvinceDTO>().error(429, "Flow Limiting");
            }

            @Override
            public Result<List<GridProvinceDTO>> getProvinceList() {
                return new Result<List<GridProvinceDTO>>().error(429, "Flow Limiting");
            }
        };
    }
}
