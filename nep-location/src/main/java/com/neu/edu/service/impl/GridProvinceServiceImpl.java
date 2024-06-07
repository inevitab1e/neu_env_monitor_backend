package com.neu.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.edu.dao.GridProvinceDao;
import com.neu.edu.common.service.impl.CrudServiceImpl;
import com.neu.edu.dto.GridProvinceDTO;
import com.neu.edu.entity.GridProvinceEntity;
import com.neu.edu.service.GridProvinceService;
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
public class GridProvinceServiceImpl extends CrudServiceImpl<GridProvinceDao, GridProvinceEntity, GridProvinceDTO> implements GridProvinceService {

    @Override
    public QueryWrapper<GridProvinceEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<GridProvinceEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}