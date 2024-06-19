package com.neu.edu.service;

import com.neu.edu.common.service.CrudService;
import com.neu.edu.common.utils.Result;
import com.neu.edu.dto.GridCityDTO;
import com.neu.edu.entity.GridCityEntity;

import java.util.List;

/**
 * 
 *
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
public interface GridCityService extends CrudService<GridCityEntity, GridCityDTO> {

    Result<List<GridCityDTO>> getCityListByProvinceId(Integer provinceId);
}