package com.neu.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.edu.common.service.impl.CrudServiceImpl;
import com.neu.edu.dao.StatisticsDao;
import com.neu.edu.dto.StatisticsDTO;
import com.neu.edu.entity.StatisticsEntity;
import com.neu.edu.service.StatisticsService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 
 *
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@Service
public class StatisticsServiceImpl extends CrudServiceImpl<StatisticsDao, StatisticsEntity, StatisticsDTO> implements StatisticsService {

    @Override
    public QueryWrapper<StatisticsEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<StatisticsEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}