package com.neu.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.edu.dao.GridCityDao;
import com.neu.edu.dto.GridCityDTO;
import com.neu.edu.common.service.impl.CrudServiceImpl;
import com.neu.edu.entity.GridCityEntity;
import com.neu.edu.service.GridCityService;
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
public class GridCityServiceImpl extends CrudServiceImpl<GridCityDao, GridCityEntity, GridCityDTO> implements GridCityService {

    @Override
    public QueryWrapper<GridCityEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<GridCityEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}