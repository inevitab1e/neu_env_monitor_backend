package com.neu.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.edu.common.service.impl.CrudServiceImpl;
import com.neu.edu.common.utils.Result;
import com.neu.edu.vo.*;
import com.neu.edu.dao.StatisticsDao;
import com.neu.edu.dto.StatisticsDTO;
import com.neu.edu.entity.StatisticsEntity;
import com.neu.edu.service.StatisticsService;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@Service
public class StatisticsServiceImpl extends CrudServiceImpl<StatisticsDao, StatisticsEntity, StatisticsDTO> implements StatisticsService {

    @Autowired
    private StatisticsDao statisticsDao;

    @Override
    public QueryWrapper<StatisticsEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");
        String provinceId = (String) params.get("provinceId");
        String cityId = (String) params.get("cityId");
        String confirmDate = (String) params.get("confirmDate");

        QueryWrapper<StatisticsEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id)
                .eq(StrUtil.isNotBlank(provinceId), "province_id", provinceId)
                .eq(StrUtil.isNotBlank(cityId), "city_id", cityId)
                .eq(StrUtil.isNotBlank(confirmDate), "confirm_date", confirmDate);

        return wrapper;
    }

    @Override
    public Result<List<ProvinceAqiIndexVO>> getProvinceAqiIndexExceededInfo() {
        List<ProvinceAqiIndexVO> resultList = statisticsDao.getProvinceAqiIndexExceededInfo();
        if (CollectionUtils.isEmpty(resultList)) {
            return new Result<List<ProvinceAqiIndexVO>>().error(204,"No data.");
        }

        return new Result<List<ProvinceAqiIndexVO>>().ok(resultList);
    }

    @Override
    public Result<List<AqiCountVO>> getAqiCountInfo() {
        List<AqiCountVO> resultList = statisticsDao.getAqiCountInfo();

        if (CollectionUtils.isEmpty(resultList)) {
            return new Result<List<AqiCountVO>>().error(204,"No data.");
        }

        return new Result<List<AqiCountVO>>().ok(resultList);
    }

    @Override
    public Result<List<AqiMonthCountVO>> getAqiMonthCountInfo() {
        List<AqiMonthCountVO> resultList = statisticsDao.getAqiMonthCountInfo();

        if (CollectionUtils.isEmpty(resultList)) {
            return new Result<List<AqiMonthCountVO>>().error(204,"No data.");
        }

        return new Result<List<AqiMonthCountVO>>().ok(resultList);
    }

    @Override
    public Result<CoverageVO> getCoverageInfo() {
        CoverageVO result = statisticsDao.getCoverageInfo();

        if (result == null) {
            return new Result<CoverageVO>().error(204,"No data.");
        }

        return new Result<CoverageVO>().ok(result);
    }

    @Override
    public Result<SummaryDataVO> getSummaryDataInfo() {
        SummaryDataVO result = statisticsDao.getSummaryDataInfo();

        if (result == null) {
            return new Result<SummaryDataVO>().error(204,"No data.");
        }

        return new Result<SummaryDataVO>().ok(result);
    }
}