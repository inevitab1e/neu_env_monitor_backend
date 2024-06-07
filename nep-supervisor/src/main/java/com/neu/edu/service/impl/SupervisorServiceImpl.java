package com.neu.edu.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.edu.common.service.impl.CrudServiceImpl;
import com.neu.edu.dao.SupervisorDao;
import com.neu.edu.dto.SupervisorDTO;
import com.neu.edu.entity.SupervisorEntity;
import com.neu.edu.service.SupervisorService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 
 *
 * @author FEI Bo neufeibo@gmail.com
 * @since 1.0.0 2024-06-06
 */
@Service
public class SupervisorServiceImpl extends CrudServiceImpl<SupervisorDao, SupervisorEntity, SupervisorDTO> implements SupervisorService {

    @Override
    public QueryWrapper<SupervisorEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<SupervisorEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}