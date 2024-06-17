package com.neu.edu.dao;

import com.neu.edu.common.dao.BaseDao;
import com.neu.edu.entity.StatisticsEntity;
import com.neu.edu.vo.AqiCountVO;
import com.neu.edu.vo.AqiMonthCountVO;
import com.neu.edu.vo.ProvinceAqiIndexVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 *
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@Mapper
public interface StatisticsDao extends BaseDao<StatisticsEntity> {


    List<ProvinceAqiIndexVO> getProvinceAqiIndexExceededInfo();

    List<AqiCountVO> getAqiCountInfo();

    List<AqiMonthCountVO> getAqiMonthCountInfo();
}