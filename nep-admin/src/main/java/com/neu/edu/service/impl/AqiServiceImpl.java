package com.neu.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.edu.dao.AqiDao;
import com.neu.edu.dto.AqiDTO;
import com.neu.edu.common.service.impl.CrudServiceImpl;
import com.neu.edu.entity.AqiEntity;
import com.neu.edu.service.AqiService;
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
public class AqiServiceImpl extends CrudServiceImpl<AqiDao, AqiEntity, AqiDTO> implements AqiService {

    @Override
    public QueryWrapper<AqiEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<AqiEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}