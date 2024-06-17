package com.neu.edu.service;

import com.neu.edu.common.service.CrudService;
import com.neu.edu.common.utils.Result;
import com.neu.edu.dto.StatisticsDTO;
import com.neu.edu.entity.StatisticsEntity;
import com.neu.edu.vo.AqiCountVO;
import com.neu.edu.vo.AqiMonthCountVO;
import com.neu.edu.vo.ProvinceAqiIndexVO;

import java.util.List;

/**
 * 
 *
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
public interface StatisticsService extends CrudService<StatisticsEntity, StatisticsDTO> {

    Result<List<ProvinceAqiIndexVO>> getProvinceAqiIndexExceededInfo();

    Result<List<AqiCountVO>> getAqiCountInfo();

    Result<List<AqiMonthCountVO>> getAqiMonthCountInfo();
}