package com.neu.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.edu.dao.AdminDao;
import com.neu.edu.dto.AdminDTO;
import com.neu.edu.common.service.impl.CrudServiceImpl;
import com.neu.edu.entity.AdminEntity;
import com.neu.edu.service.AdminsService;
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
public class AdminsServiceImpl extends CrudServiceImpl<AdminDao, AdminEntity, AdminDTO> implements AdminsService {

    @Override
    public QueryWrapper<AdminEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<AdminEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);

        return wrapper;
    }


}